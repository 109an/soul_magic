package soul_magic.soul_magic.spells;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import soul_magic.soul_magic.Soul_magic;
import soul_magic.soul_magic.util.Aoe;
import soul_magic.soul_magic.util.ParticleShapes;
public class SpellObject{
    //TODO make "save or suck" spells that dont effect entities with higher hp
    //model spells after dnd spells (9 levels of spells, each level takes more soul to cast)
    //change level in the enum to minlevel and allow for upcsting like in dnd
    //leaan into the soul theme by  making all spells dark or soul fire
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
      if (Spelltocast.minpower>=Power){
        Caster.playSound(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, 1, 1);
        ParticleShapes.genericSpellCast(world, this);
          switch (Spelltocast) {
            case MAGIC_MISSLE:
            //a projectile composed of a glowing white particle th
              break;
            case LIFESTEAL:
              StatusEffectInstance lifesteal=new StatusEffectInstance(Soul_magic.VAMPIRISM, 120, this.Power-2, false, false, false);
              Caster.addStatusEffect(lifesteal);
              break;
            case MISTY_STEP:
              Caster.setVelocity(Vec3d.fromPolar(Caster.getPitch(), Caster.getYaw()).multiply(Power));
            break;
            case SHOCKWAVE:
              Caster.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1f, 1f);
              for (Entity hit : Aoe.getAll(world, Caster, Caster.getX(), Caster.getY(), Caster.getZ(), Power*3)) {
                if(hit.getY()==Caster.getY() || hit.getY()==Caster.getY()+1 || hit.getY()==Caster.getY()-1){
                hit.damage(hit.getDamageSources().indirectMagic(Caster, Caster), Power*2);
                double x=-((Caster.getX()-hit.getX())+1)/(hit.distanceTo(Caster));
                double z=-((Caster.getZ()-hit.getZ())+1)/(hit.distanceTo(Caster));
                ParticleShapes.circleShape(world, ParticleTypes.SOUL, x, z, z, Power*3 , 250, 0, true);
                ParticleShapes.circleShape(world, ParticleTypes.SCULK_SOUL, x, z, z, Power*3 , 250, 0, true);
                hit.setVelocity(x, 0.2, z);
                }
              }
            default:
              break;
          }
        }
      }
    public enum Spell{
        MAGIC_MISSLE("magic_missle", SpellType.ARCANE, 1),
        FIREBALL("fireball", SpellType.FIRE, 3),
        LIFESTEAL("lifesteal", SpellType.DARK, 3),
        MISTY_STEP("misty_step", SpellType.ARCANE, 2),
        SHOCKWAVE("shockwave", SpellType.EARTH, 3)
        ;
        public final SpellType type;
        public final String name;
        public final int minpower;
        private Spell (String name, SpellType type, int minpower){
          this.type=type;
          this.name=name;
          this.minpower=minpower;
        }
        public SpellType getSpellType(){
          return this.type;
        }
        public String getSpellName(){
          return this.name;
        }
     }
     public enum SpellType{
        FIRE,
        ICE,
        EARTH,
        AIR,
        LIGHTNING,
        DARK,
        LIGHT,
        ARCANE
       }
}