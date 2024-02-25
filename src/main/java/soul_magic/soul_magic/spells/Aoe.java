package soul_magic.soul_magic.spells;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import java.util.*;
public class Aoe {
 public double CenterX;
 public double CenterY;
 public double CenterZ;
 public int Duration;
 public int Rate;
 public int Size;
 public Targets Target;
 private List<Entity> InBoxAoe;
 private boolean IsActive;
 public World world;
 public LivingEntity Caster;
 private List<Entity> hit= new ArrayList<Entity>();
 public Aoe(World world, LivingEntity caster, int duration, int rate, double centerX, double centerY, double centerZ){
  this.Duration=duration;
  this.Rate=rate;
  this.CenterX=centerX;
  this.CenterY=centerY;
  this.CenterZ=centerZ;
  this.world=world;
 }
 public void start(){
   ServerTickEvents.END_SERVER_TICK.register(server ->whileActive());
   this.IsActive=true;
} 
public void end(){
  this.IsActive=false;
}
  public void whileActive(){
    if(this.IsActive)
    {
      effect();
    if (this.Duration!=0){
      this.Duration--;
    }
    else{
      this.end();
    }
   }
  }
  public void onEnded(){}
  public void onStarted(){}
  private  void effect(){
    List<? extends Entity> inaoe=getAll();
    System.out.println(inaoe);
     for(int i=inaoe.size()-1; i>=0; i--){//nothing is being added to this list
     Entity hit=inaoe.get(i);
    switch (Target) {
      case ALLENTITY:
      hit(hit);
        break;
      case LIVINGENTITY:
      if(hit instanceof LivingEntity){
      hit(((LivingEntity)hit));
      }
      break;
      default:
        break;
    } 
  }
  }
  public void setTarget(Targets target){
    this.Target=target;
  }
  public void hit (Entity hit){}
  private List<Entity> getAll(){
  Box box=new Box(CenterX-Size, CenterY-Size, CenterZ-Size, CenterX+Size, CenterY+Size, CenterZ+Size);
  InBoxAoe=this.world.getOtherEntities(this.Caster, box);
   for(int i=InBoxAoe.size()-1; i>=0; i--){
    Entity inbox=InBoxAoe.get(i);    
        Vec3d pos=inbox.getPos();
        Vec3d center=new Vec3d(CenterX, CenterY, CenterZ);
        if(center.distanceTo(pos)<Size){
            hit.add(inbox);
          }   
   }
  return hit; 
 }
    public static enum Targets{
      ALLENTITY,
      LIVINGENTITY,
    }
}

