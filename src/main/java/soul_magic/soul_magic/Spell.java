package soul_magic.soul_magic;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
public class Spell{
    //TODO make "save or suck" spells that dont effect entities with higher hp
    public int Power;
    public Spells Spell;
    public World world;
    public LivingEntity Caster;
    public Spell(World world, LivingEntity caster, int castime, Spells spell, int power){
     this.world=world;
     this.Caster=caster;
     this.Power=power;
     this.Spell=spell;
     }
    public void castSpell(){
        switch (Spell) {
          case DAMAGE_PROJECTILE:
          case EXPLOSIVE_DAMAGE_PROJECTILE:
          
            
            break;
        
          default:
            break;
        }
    }
    public SpellType getSpellType(){
        return this.Spell.type;
    }
    public enum Spells{
      DAMAGE_PROJECTILE(SpellType.ARCANE),
      EXPLOSIVE_DAMAGE_PROJECTILE(SpellType.ARCANE),
      
      
      ;
      public final SpellType type;
      private Spells (SpellType type){
        this.type=type;
      }
     }
     public enum SpellType{
        FIRE,
        WATER,
        ICE,
        EARTH,
        AIR,
        LIGHTNING,
        DARK,
        LIGHT,
        ARCANE
       }
}
