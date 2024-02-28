package soul_magic.soul_magic.util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import java.util.*;
public class Aoe {
  public static List<Entity> getAll(World world, LivingEntity caster, double x, double y, double z, double size){
    List<Entity> hit= new ArrayList<Entity>();
    Box box=new Box(x-size, y-size, z-size, x+size, y+size, z+size);
    List<Entity> InBoxAoe= world.getOtherEntities(caster, box);
    for(int i=InBoxAoe.size()-1; i>=0; i--){
      Entity inbox=InBoxAoe.get(i);    
          Vec3d pos=inbox.getPos();
          Vec3d center=new Vec3d(x, y, z);
          if(center.distanceTo(pos)<size){
              hit.add(inbox);
            }   
    }
    return hit; 
 }
  public static List<LivingEntity> getLiving(World world, LivingEntity caster, double x, double y, double z, double size){
    List<LivingEntity> hit= new ArrayList<LivingEntity>();
    Box box=new Box(x-size, y-size, z-size, x+size, y+size, z+size);
    List<Entity> InBoxAoe= world.getOtherEntities(caster, box);
    for(int i=InBoxAoe.size()-1; i>=0; i--){
      Entity inbox=InBoxAoe.get(i);    
          Vec3d pos=inbox.getPos();
          Vec3d center=new Vec3d(x, y, z);
          if(center.distanceTo(pos)<size && inbox instanceof LivingEntity){
              hit.add(((LivingEntity)inbox));
            }   
    }
    return hit; 
  }
}

