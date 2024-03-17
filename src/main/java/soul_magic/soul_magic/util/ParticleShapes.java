package soul_magic.soul_magic.util;

import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import soul_magic.soul_magic.spells.SpellObject;
public abstract class ParticleShapes{//change world.addparticle to ServerWorld.spawnParticle, why dis not work
    private static double pi=MathHelper.PI;
    private static Random random = Random.create();
    public static void genericSpellCast(SpellObject spell){
      if(spell.world instanceof ServerWorld){
        ParticleShapes.sphereShape(((ServerWorld)spell.world), ParticleTypes.SMOKE, spell.Caster.getX(), spell.Caster.getY(), spell.Caster.getZ(), 2, 50, true);
        ParticleShapes.sphereShape(((ServerWorld)spell.world), ParticleTypes.SOUL, spell.Caster.getX(), spell.Caster.getY(), spell.Caster.getZ(), 2, 50, true);
      }
    }
    public static void burstShape(ServerWorld world, ParticleEffect particle, double centerx, double centery, double centerz, int count, double energy, boolean height){
      if(height){
        double randangel1 = MathHelper.nextDouble(random, -365, 365);
        double randangel2 = MathHelper.nextDouble(random, -365, 365);
        double randangel3 = MathHelper.nextDouble(random, -365, 365);
        spawnForcedParticles(world, particle, centerx, centery, centerz, randangel1, randangel2, randangel3, energy);
      }
      else{
        double randangel1 = MathHelper.nextDouble(random, -365, 365);
        double randangel2 = MathHelper.nextDouble(random, -365, 365);
        for(int i=0; i<count; i++) {
        spawnForcedParticles(world, particle, centerx, centery, centerz, randangel1, 0, randangel2, energy);
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
             world.spawnParticles(particle, x, y, z, 1, 0, 0, 0, 0);
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
        world.spawnParticles(particle, x+centerx, y+centery, z+centerz, 1, 0, 0, 0, 0);

      }
    }
    else{
      for(int i=0; i<count; i++) {
      double theta= random.nextInt()*pi*2;
      double phi=Math.acos(2.0 *  random.nextInt() - 1.0);
      double x=radius*(Math.sin(phi)*Math.cos(theta));
      double y=radius*(Math.sin(phi)*Math.sin(theta));
      double z=radius*(Math.cos(phi));
      world.spawnParticles(particle, x+centerx, y+centery, z+centerz, 1, 0, 0, 0, 0);
      }
     
    }
   }
   private static void spawnForcedParticles(ServerWorld world, ParticleEffect particle, double x, double y, double z, double deltaX, double deltaY, double deltaZ, double speed){
    ParticleS2CPacket particleS2CPacket = new ParticleS2CPacket(particle, false, x, y, z, (float)deltaX, (float)deltaY, (float)deltaZ, (float)speed, 1);

        for(int j = 0; j < world.getPlayers().size(); ++j) {
          ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)world.getPlayers().get(j);
          if (world.sendToPlayerIfNearby(serverPlayerEntity, true, x, y, z, particleS2CPacket)) {
              ++i;
          }
        }
   }
}

