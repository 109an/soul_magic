package ancient_arcana.arcana;
import java.util.Random;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
public class ParticleShapes{
    public static double pi=3.1416;
    public static void CircleShape(ClientWorld world, ParticleEffect particle, double centerx, double centery, double centerz, double radius, double num_per_block){
        double t=0;
        double count=num_per_block*2*pi*radius;
        for(double i=0; i<count; i++){
         t+=2*pi/count;
         world.addParticle(particle, centerx-(radius)*Math.cos(t), centery, centerz-(radius)*Math.sin(t), 0, 0, 0);
        }
    }
    public static void LineShape(ClientWorld world, DefaultParticleType particle, double startx, double starty, double startz, double Pitch,double Yaw, double density, double length){
        System.out.println("pitch "+Pitch);
        System.out.println("yaw "+Yaw);
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
   public static void AroundEntity(World world, DefaultParticleType particle, LivingEntity entity, int count, double energy){
    Vec3d pos=entity.getPos();
    for(int i=0; i<count; i++) {
      double vx=Math.random()*energy*plusminus();
      double vy=Math.random()*energy*plusminus();
      double vz=Math.random()*energy*plusminus();
      double x=(Math.random()*plusminus()*entity.getWidth())+pos.getX();
      double y=(Math.random()*entity.getHeight())+pos.getY();
      double z=(Math.random()*plusminus()*entity.getWidth())+pos.getZ();
      world.addParticle(particle, x, y, z, vx, vy, vz);
   } 
   }
   public static void AroundProjectile(World world, DefaultParticleType particle, ProjectileEntity entity, int count, double energy){
    for(int i=0; i<count; i++) {
      Vec3d pos=entity.getPos();
      double vx=Math.random()*energy*plusminus();
      double vy=Math.random()*energy*plusminus();
      double vz=Math.random()*energy*plusminus();
      double x=(Math.random()*plusminus()*entity.getWidth())+pos.getX();
      double y=(Math.random()*entity.getHeight())+pos.getY();
      double z=(Math.random()*plusminus()*entity.getWidth())+pos.getZ();
      world.addParticle(particle, x, y, z, vx, vy, vz);
   } 
   }
   public static void RandomSquareShape(World world, ParticleEffect particle,double centerx,double centery, double centerz, double size, int count, double energy){
     for(int i=0; i<count; i++) 
    {
      double x=(Math.random()*plusminus()*size/2)+centerx;
      double y=(Math.random()*size/2)+centery;
      double z=(Math.random()*plusminus()*size/2)+centerz;
      double vx=Math.random()*energy*plusminus();
      double vy=Math.random()*energy*plusminus();
      double vz=Math.random()*energy*plusminus();
      world.addParticle(particle, x, y, z, vx, vy, vz);
   } 
  }
  public static void RandomSphereShape(World world, ParticleEffect particle,double centerx,double centery, double centerz, double radius, int count, double energy, boolean filled){
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

