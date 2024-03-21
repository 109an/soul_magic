package soul_magic.soul_magic.spells;
import java.util.List;

import net.minecraft.block.SculkBlock;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import soul_magic.soul_magic.Soul_magic;
import soul_magic.soul_magic.util.Aoe;
import soul_magic.soul_magic.util.ParticleShapes;
public class SpellObject{
    //make "save or suck" spells that dont effect entities with higher hp
    //leaan into the soul theme by  making all spells dark or soul fire
    //TODO fix the soulbottle fill method, fix the rotation of the fireball entity, use a model predicate provider for the spellscrolls, add 
    public int Power;
    public Spell Spelltocast;
    public World world;
    public LivingEntity Caster;

    public SpellObject(World world, LivingEntity caster, Spell spell, int power){
     this.world=world;
     this.Caster=caster;
     this.Power=power;
     this.Spelltocast=spell;
     }
     public SpellObject(World world, LivingEntity caster, String name, int power){
      this.world=world;
      this.Caster=caster;
      this.Power=power;
        for (Spell spell : Spell.values()) {
          if(spell.name==name){
           this.Spelltocast=spell;
          }
        }
        Soul_magic.LOGGER.error("no such Spell found");
      }
    public void castSpell(){
      SpellResult spellResult = new SpellResult();
      if (Spelltocast.minpower<=Power){
        ParticleShapes.genericSpellCast(this);
          switch (Spelltocast) {
            case MAGIC_MISSLE:
            //homing projectile
              break;
            case LIFESTEAL:
              StatusEffectInstance lifesteal=new StatusEffectInstance(Soul_magic.VAMPIRISM, 120, this.Power-2, false, false, false);
              Caster.addStatusEffect(lifesteal);
              break;
            case MISTY_STEP:
              Caster.setVelocity(Vec3d.fromPolar(Caster.getPitch(), Caster.getYaw()).multiply(Power+1.5).normalize().multiply(Power+1.5));
              StatusEffectInstance slowfall = new StatusEffectInstance(StatusEffects.SLOW_FALLING, 120);
              Caster.addStatusEffect(slowfall);
            break;
            case SHOCKWAVE:
              Caster.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1f, 1f);
              if(this.world instanceof ServerWorld){
                ParticleShapes.burstShape(((ServerWorld)world), ParticleTypes.SCULK_SOUL, Caster.getX(), Caster.getY(), Caster.getZ(), 100, 1, false);
                ParticleShapes.burstShape(((ServerWorld)world), ParticleTypes.SOUL, Caster.getX(), Caster.getY(), Caster.getZ(), 100, 1, false);
              }
              for (Entity hit : Aoe.getAll(world, Caster, Caster.getX(), Caster.getY(), Caster.getZ(), Power*3)) {
                if(hit.getY()==Caster.getY() || hit.getY()==Caster.getY()+1 || hit.getY()==Caster.getY()-1){
                hit.damage(hit.getDamageSources().indirectMagic(Caster, Caster), Power*2);
                double x=-((Caster.getX()-hit.getX())+1)/(hit.distanceTo(Caster));
                double z=-((Caster.getZ()-hit.getZ())+1)/(hit.distanceTo(Caster));
                hit.setVelocity(x, 0.2, z);
                }
              }
            break;
            case SHIELD:
            StatusEffectInstance shield = new StatusEffectInstance(StatusEffects.RESISTANCE, 600);
            Caster.addStatusEffect(shield);
            case SLOW:
            SlowAoe slowAoe = new SlowAoe(world, Caster, Caster.getPos(), Power);
            slowAoe.start();
            break;
            case SUMMON_SWORD:
            if(Caster instanceof PlayerEntity){
              ItemStack itemStack = new ItemStack(Soul_magic.MAGIC_SWORD);
              NbtCompound nbt = itemStack.getOrCreateNbt();
              nbt.putInt("time_left", 1200*Power);
              itemStack.setNbt(nbt);
              ((PlayerEntity)Caster).giveItemStack(itemStack);
            }
            break;
            case SOUL_TRAP:
            ArcaneSpellProjectile arcaneSpellProjectile = new ArcaneSpellProjectile(world, Caster, Power);
            arcaneSpellProjectile.setVelocity(Caster, Caster.getPitch(), Caster.getYaw(), Caster.getRoll(), 1f, 0.3f);
            arcaneSpellProjectile.setNoGravity(true);
            world.spawnEntity(arcaneSpellProjectile);
            default:
              break;
          }
        }
      }
    public enum Spell{
        MAGIC_MISSLE("magic_missle", 1, 10),
        FIREBALL("fireball", 1, 10),
        LIFESTEAL("lifesteal", 1, 10),
        MISTY_STEP("misty_step", 1, 0),
        SHOCKWAVE("shockwave", 1, 10),
        SHIELD("shield", 1, 15),
        SLOW("slow", 2, 15),
        SUMMON_SWORD("summon_sword", 1, 5),
        SOUL_TRAP("soul_trap", 1, 10)
        ;
        public final String name;
        public final int minpower;
        public final int cooldown;
        private Spell (String name, int minpower, int cooldown){
          this.name=name;
          this.minpower=minpower;
          this.cooldown = cooldown;
        }
        public String getSpellName(){
          return this.name;
        }
     }
}
