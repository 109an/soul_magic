package soul_magic.soul_magic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import soul_magic.soul_magic.Aoe;
import soul_magic.soul_magic.SpellProjectile;
import soul_magic.soul_magic.ParticleShapes;
public class Spell extends Spellticker{
    public int SpellPower;
    public SpellShapes Shape;
    public int ShapePower;
    public Spells Type;
    public boolean IsAoe;
    public boolean IsHoming;
    public Spell(World world, LivingEntity caster, int castime,SpellShapes shape, Spells type, int spellpower, int shapepower, boolean isaoe){
     super(world, caster, castime);
     this.ShapePower=shapepower;
     this.SpellPower=spellpower;
     this.Shape=shape;
     this.Type=type;
     this.IsAoe=isaoe;
     }
     public void setHoming(Boolean Homing){
        this.IsHoming=Homing;
     }
     public void setAoe(Boolean Aoe){
        this.IsAoe=Aoe;
     }
    @Override
    public void onSpellCast(){}
    @Override
    public void whileOngoing(){}
    @Override
    public void whileCasting(){ //this works fine
        this.world.addParticle(ParticleTypes.FLASH, Caster.getX(), Caster.getY(), Caster.getZ(), 0, 0, 0);
    }
    @Override
    public void whileActive(){}
    @Override
    public void afterCast(){
        switch (Shape) {
            case PROJECTILE:
            SpellProjectile spellProjectile=new SpellProjectile(this.world, Caster.getX(), Caster.getEyeY(), Caster.getZ()){
                @Override
                public void onCollision(HitResult hitResult){
                    if(IsAoe){
                            Aoe aoe=new Aoe(this.world, Caster, ShapePower, ShapePower, hitResult.getPos().getX(), hitResult.getPos().getY(), hitResult.getPos().getX()){
                                @Override
                                public void whileActive() {
                                    super.whileActive();
                                    switch (Type) {
                                        case DAMAGE:
                                        ParticleShapes.RandomSphereShape(this.world, ParticleTypes.CRIT, CenterX, CenterY, CenterZ, ShapePower, ShapePower*100, 0, false);
                                            break;
                                    
                                        default:
                                            break;
                                    }
                                }
                                @Override
                                public void hit(Entity hit){
                                    switch (Type) {
                                        case DAMAGE:
                                        System.out.println(hit);
                                        hit.damage(hit.getDamageSources().indirectMagic(Caster, Caster), SpellPower);
                                        ParticleShapes.AroundEntity(world, ParticleTypes.ENCHANTED_HIT, Caster, 100, 0.5);
                                            break;
                                    
                                        default:
                                            break;
                                    }
                                }
                            };
                            aoe.start();
                            this.kill();
                        }
                        if(!IsAoe){
                            switch (Type) {
                            case DAMAGE:
                            System.out.print(hitResult.getClass());
                            this.getWorld().addParticle(ParticleTypes.FLAME, hitResult.getPos().getX(), hitResult.getPos().getY(), hitResult.getPos().getZ(), 0, 0, 0);
                            if(hitResult.getType()==HitResult.Type.ENTITY){
                            }
                            this.kill();
                                break;
                            default:
                                break;
                        } 
                    }
                }
                @Override
                public void tick(){
                    super.tick();
                    if(IsHoming){
                        //soon
                    }
                    System.out.println(this.world);//this works
                    this.getWorld().addParticle(ParticleTypes.FLAME, this.getX(), this.getY(), this.getZ(), 0, 0, 0); //I have no idea why particles are not spawning... help
                }
            };
            spellProjectile.setNoGravity(true);
            spellProjectile.setVelocity(Caster, Caster.getPitch(), Caster.getYaw(), Caster.getRoll(), 1, 0.3f);
            this.world.spawnEntity(spellProjectile);
            break;
            case SELF:
             //new selfspell   
                break;
        
            default:
                break;
        }
    }
    public void onSpellEnded(){}
    
    public enum Spells{
       DAMAGE,
       IGNITE,
       HEAL,
       WARD,
       LAUNCH,
       FREEZE,
       FANG,
       POISON,
       WITHER,
       RAGE,
       VAMPIRISM,
       STRENGTH,
       WEAKNESS,
       FIRE_RES,
       WATER_BREATHING,
       UNHINDERED,
       SWIFT_SNEAK,
       ORESENSE,
       MULTI_JUMP,
       SPEED,
       JUMP_BOOST,
       INVIS,
       TELEPORT,
       GRAPPLE,
       SOUL_SUCK,
       LIFESTEAL,
       RAY_OF_LIGHT,
       LIGHT,
       SHIELD,
       SUMMON_BLAZE,
       SUMMON_UNDEAD,
       SUMMON_GUARDIAN,
       SUMMON_VEX,
       BREAK,
       BONEMEAL,
       FILL,
       CREATE_EARTH,
       CREATE_WATER,
       CREATE_FIRE,
       PACIFY,
       PACIFY_UNDEAD,
       PACIFY_ENDERMAN,
       PACIFY_ILLAGER,

     }
     public enum SpellShapes{
        PROJECTILE,
        SELF,
     }
}
