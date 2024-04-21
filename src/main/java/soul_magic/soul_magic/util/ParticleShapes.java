package soul_magic.soul_magic.util;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import soul_magic.soul_magic.spells.Spells;
import soul_magic.soul_magic.spells.Spells.Spell;
public abstract class ParticleShapes{
    private static double pi=MathHelper.PI;
    private static Random random = Random.create();
    public static void genericSpellCast(World world, Spell spell, PlayerEntity caster){
      if(world instanceof ServerWorld){
        ParticleShapes.sphereShape(((ServerWorld) world), ParticleTypes.SMOKE, caster.getX(), caster.getY(), caster.getZ(), 2, 50, true);
        ParticleShapes.sphereShape(((ServerWorld) world), ParticleTypes.SOUL, caster.getX(), caster.getY(), caster.getZ(), 2, 50, true);
      }
    }
    public static void burstShape(ServerWorld world, ParticleEffect particle, double centerx, double centery, double centerz, int count, double energy, boolean height){
      if(height){
        double randangel1 = MathHelper.nextDouble(random, -360, 360);
        double randangel2 = MathHelper.nextDouble(random, -360, 360);
        double randangel3 = MathHelper.nextDouble(random, -360, 360);
        for(int i=0; i<count; i++) {
          spawnForcedParticles(world, particle, centerx, centery, centerz, randangel1, randangel3, randangel2, energy);
        }
      }
      else{
        double randangel1 = MathHelper.nextDouble(random, -360, 360);
        double randangel2 = MathHelper.nextDouble(random, -360, 360);
        for(int i=0; i<count; i++) {
        spawnForcedParticles(world, particle, centerx, centery, centerz, randangel1, 90, randangel2, energy);
        }
      }
    }
    public static void lineShape(ServerWorld world, DefaultParticleType particle, double startx, double starty, double startz, double Pitch,double Yaw, double density, double length){
        double x=startx;
        double y=starty;
        double z=startz;
        Pitch=Pitch*pi/180;
        Yaw=Yaw*pi/180;
        Pitch=Pitch+(pi/2);
        Yaw=Yaw+(pi/2);
       Random.createLocal().nextInt();
        for(double i=0; i<length; i+=density/length){
             x+=(Math.sin(Pitch)*Math.cos(Yaw))*density;
             z+=(Math.sin(Pitch)*Math.sin(Yaw))*density;
             y+=(Math.cos(Pitch))*density;
             spawnForcedParticles(world, particle, x, y, z, 0, 0, 0, 0);
        }
    }
  public static void sphereShape(ServerWorld world, ParticleEffect particle,double centerx,double centery, double centerz, double radius, int count, boolean filled){
    if (filled){
      for(int i=0; i<count; i++) {
        double randdist= random.nextInt() * (radius);
        double theta= random.nextInt()*pi*2;
        double phi=Math.acos(2.0 *  random.nextInt() - 1.0);
        double x=randdist*(Math.sin(phi)*Math.cos(theta));
        double y=randdist*(Math.sin(phi)*Math.sin(theta));
        double z=randdist*(Math.cos(phi));
        spawnForcedParticles(world, particle, x+centerx, y+centery, z+centerz, 0, 0, 0, 0);

      }
    }
    else{
      for(int i=0; i<count; i++) {
      double theta= random.nextInt()*pi*2;
      double phi=Math.acos(2.0 *  random.nextInt() - 1.0);
      double x=radius*(Math.sin(phi)*Math.cos(theta));
      double y=radius*(Math.sin(phi)*Math.sin(theta));
      double z=radius*(Math.cos(phi));
      spawnForcedParticles(world, particle, x+centerx, y+centery, z+centerz, 0, 0, 0, 0);
      }
     
    }
   }
   private static void spawnForcedParticles(ServerWorld world, ParticleEffect particle, double x, double y, double z, double deltaX, double deltaY, double deltaZ, double speed){
      world.getPlayers(player->player.getBlockPos().isWithinDistance(player.getPos(), 32)).forEach(player-> world.spawnParticles(player, particle, true, x, y, z, 1, deltaX, deltaY, deltaZ, speed));
   }
}

