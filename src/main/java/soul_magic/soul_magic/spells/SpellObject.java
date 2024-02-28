package soul_magic.soul_magic.spells;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import soul_magic.soul_magic.Soul_magic;
import soul_magic.soul_magic.util.ParticleShapes;
public class SpellObject{
    //TODO make "save or suck" spells that dont effect entities with higher hp
    //model spells after dnd spells (9 levels of spells, each level takes more soul to cast)
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
      Caster.playSound(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, 1, 1);
      ParticleShapes.genericSpellCast(world, this);
        switch (Spelltocast) {
          case MAGIC_MISSLE:
            break;
          case LIFESTEAL:
          case LIFESTEAL2:
          case LIFESTEAL3:
          StatusEffectInstance lifesteal=new StatusEffectInstance(Soul_magic.VAMPIRISM, 120, this.Power-2, false, false, false);
          Caster.addStatusEffect(lifesteal);
            break;
          case MISTY_STEP:
          HitResult hitResult=Caster.raycast(30, 1.0f, false);
          ParticleShapes.circleShape(world, ParticleTypes.DRAGON_BREATH, Caster.getX(), Caster.getY(), Caster.getZ(), 3 , 100, 0, false);
          Caster.setPos(hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z);
          ParticleShapes.circleShape(world, ParticleTypes.DRAGON_BREATH, hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z, 3 , 100, 0, false);
          break;
          case SHOCKWAVE:
          ShockwaveSpell shockwaveSpell = new ShockwaveSpell();
          default:
            break;
        }
      }
    public enum Spell{
        MAGIC_MISSLE("magic_missle", SpellType.ARCANE, 1),
        FIREBALL("fireball", SpellType.FIRE, 3),
        LIFESTEAL("lifesteal", SpellType.DARK, 3),
        LIFESTEAL2("lifesteal", SpellType.DARK, 6),
        LIFESTEAL3("lifesteal", SpellType.DARK, 8),
        MISTY_STEP("misty_step", SpellType.ARCANE, 2),
        SHOCKWAVE("shockwave", SpellType.EARTH, 3)
        ;
        public final SpellType type;
        public final String name;
        private Spell (String name, SpellType type, int power){
          this.type=type;
          this.name=name;
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
