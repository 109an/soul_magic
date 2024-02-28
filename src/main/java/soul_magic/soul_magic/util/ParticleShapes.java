package soul_magic.soul_magic.util;
import java.util.Random;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import soul_magic.soul_magic.spells.SpellObject;
public class ParticleShapes{
    public static double pi=3.1416;
    public static void genericSpellCast(World world, SpellObject spell){
     switch (spell.Spelltocast.type) {
      case DARK:
      ParticleShapes.sphereShape(world, ParticleTypes.SMOKE, spell.Caster.getX(), spell.Caster.getY(), spell.Caster.getZ(), 2, 50, 0.2, true);
      ParticleShapes.sphereShape(world, ParticleTypes.SOUL, spell.Caster.getX(), spell.Caster.getY(), spell.Caster.getZ(), 2, 50, 0.2, true);
        break;
      case FIRE:
      ParticleShapes.sphereShape(world, ParticleTypes.SMOKE, spell.Caster.getX(), spell.Caster.getY(), spell.Caster.getZ(), 2, 50, 0.2, true);
      ParticleShapes.sphereShape(world, ParticleTypes.LAVA, spell.Caster.getX(), spell.Caster.getY(), spell.Caster.getZ(), 2, 50, 0.2, true);
      ParticleShapes.sphereShape(world, ParticleTypes.FLAME, spell.Caster.getX(), spell.Caster.getY(), spell.Caster.getZ(), 2, 25, 0.5, true);
        break;
      case ARCANE:
      ParticleShapes.sphereShape(world, ParticleTypes.ENCHANT, spell.Caster.getX(), spell.Caster.getY(), spell.Caster.getZ(), 2, 50, 0.2, true);
      ParticleShapes.sphereShape(world, ParticleTypes.DRAGON_BREATH, spell.Caster.getX(), spell.Caster.getY(), spell.Caster.getZ(), 2, 50, 0.2, true);
        break;
      case EARTH:
      ParticleShapes.sphereShape(world, ParticleTypes.SPORE_BLOSSOM_AIR, spell.Caster.getX(), spell.Caster.getY(), spell.Caster.getZ(), 2, 50, 0.2, true);
      ParticleShapes.sphereShape(world, ParticleTypes.ASH, spell.Caster.getX(), spell.Caster.getY(), spell.Caster.getZ(), 2, 50, 0.2, true);
        break;
      case ICE:
      ParticleShapes.sphereShape(world, ParticleTypes.SNOWFLAKE, spell.Caster.getX(), spell.Caster.getY(), spell.Caster.getZ(), 2, 50, 0.2, true);
      ParticleShapes.sphereShape(world, ParticleTypes.ITEM_SNOWBALL, spell.Caster.getX(), spell.Caster.getY(), spell.Caster.getZ(), 2, 50, 0.2, true);
        break;
      case AIR:
      ParticleShapes.sphereShape(world, ParticleTypes.POOF, spell.Caster.getX(), spell.Caster.getY(), spell.Caster.getZ(), 2, 50, 0.2, true);
      ParticleShapes.sphereShape(world, ParticleTypes.CLOUD, spell.Caster.getX(), spell.Caster.getY(), spell.Caster.getZ(), 2, 50, 0.2, true);
      break;
      case LIGHTNING:
      //custom lightning
      break;
      case LIGHT:
      world.addParticle(ParticleTypes.FLASH, spell.Caster.getX(), spell.Caster.getY(), spell.Caster.getZ(), 0, 0, 0);
      ParticleShapes.sphereShape(world, ParticleTypes.ELECTRIC_SPARK, spell.Caster.getX(), spell.Caster.getY(), spell.Caster.getZ(), 2, 50, 0.2, true);
      default:
        break;
     }
    }
    public static void circleShape(World world, ParticleEffect particle, double centerx, double centery, double centerz, double radius, double count, int energy, boolean filled){
      if (filled){
        for(int i=0; i<count; i++) {
          double randdist=Math.random() * (radius);
          double theta=Math.random()*pi*2;
          double x=randdist*(Math.cos(theta));
          double y=randdist*(Math.sin(theta));
          double vx=Math.random()*energy*plusminus();
          double vy=Math.random()*energy*plusminus();
          double vz=Math.random()*energy*plusminus();
          world.addParticle(particle, x+centerx, y+centery, centery, vx, vy, vz);
        }
      }
      else{
        for(int i=0; i<count; i++) {
        double theta=Math.random()*pi*2;
        double phi=Math.acos(2.0 * Math.random() - 1.0);
        double x=radius*(Math.sin(phi)*Math.cos(theta));
        double y=radius*(Math.sin(phi)*Math.sin(theta));
        double z=radius*(Math.cos(phi));
        double vx=Math.random()*energy*plusminus();
        double vy=Math.random()*energy*plusminus();
        double vz=Math.random()*energy*plusminus();
        world.addParticle(particle, x+centerx, y+centery, z+centerz, vx, vy, vz);
        }
      }
    }
    public static void lineShape(World world, DefaultParticleType particle, double startx, double starty, double startz, double Pitch,double Yaw, double density, double length){
        double x=startx;
        double y=starty;
        double z=startz;
        Pitch=Pitch*pi/180;
        Yaw=Yaw*pi/180;
        Pitch=Pitch+(pi/2);
        Yaw=Yaw+(pi/2);
       
        for(double i=0; i<length; i+=density/length){
             x+=(Math.sin(Pitch)*Math.cos(Yaw))*density;
             z+=(Math.sin(Pitch)*Math.sin(Yaw))*density;
             y+=(Math.cos(Pitch))*density;
             world.addParticle(particle,x, y, z, 0 ,0 ,0);
        }
    }
  public static void sphereShape(World world, ParticleEffect particle,double centerx,double centery, double centerz, double radius, int count, double energy, boolean filled){
    if (filled){
      for(int i=0; i<count; i++) {
        double randdist=Math.random() * (radius);
        double theta=Math.random()*pi*2;
        double phi=Math.acos(2.0 * Math.random() - 1.0);
        double x=randdist*(Math.sin(phi)*Math.cos(theta));
        double y=randdist*(Math.sin(phi)*Math.sin(theta));
        double z=randdist*(Math.cos(phi));
        double vx=Math.random()*energy*plusminus();
        double vy=Math.random()*energy*plusminus();
        double vz=Math.random()*energy*plusminus();
        world.addParticle(particle, x+centerx, y+centery, z+centerz, vx, vy, vz);
      }
    }
    else{
      for(int i=0; i<count; i++) {
      double theta=Math.random()*pi*2;
      double phi=Math.acos(2.0 * Math.random() - 1.0);
      double x=radius*(Math.sin(phi)*Math.cos(theta));
      double y=radius*(Math.sin(phi)*Math.sin(theta));
      double z=radius*(Math.cos(phi));
      double vx=Math.random()*energy*plusminus();
      double vy=Math.random()*energy*plusminus();
      double vz=Math.random()*energy*plusminus();
      world.addParticle(particle, x+centerx, y+centery, z+centerz, vx, vy, vz);
      }
    }
   }
    private static int plusminus(){
     Random random=new Random();
     if (random.nextBoolean()){
        return 1;
     }
     else{
        return -1;
     }
    }
}

