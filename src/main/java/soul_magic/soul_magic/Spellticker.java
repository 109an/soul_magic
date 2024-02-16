package soul_magic.soul_magic;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
public abstract class Spellticker{
 public World world;
 public LivingEntity Caster;
 public int Castime;
 public int Tickslefttocast;
 public boolean isOngoing;
 public boolean isBeingCast;
 public boolean isActive;
 private boolean count=true;
  public Spellticker(World world, LivingEntity caster, int castime){
  this.world=world;
  this.Caster=caster;
  this.Castime=castime;
  this.Tickslefttocast=castime;
  this.isOngoing=true;
  this.isBeingCast=true;
  onSpellCast();
 }
 public void startSpell(){
   ServerTickEvents.END_SERVER_TICK.register(server ->{ whileOngoing(); counter();});
} 
 public boolean isBeingCast(){
   return this.isBeingCast;
 }
 public boolean isActive(){
   return this.isActive;
 }
 public boolean isOngoing(){
   return this.isOngoing;
 }
 public int getCastTime(){
   return this.Castime;
 }
 public int getTicksLeftToCast(){
   return this.Tickslefttocast;
 }
 public void setCastTime(int castime){
   this.Castime=castime;
 }
 public void setTicksLeftToCast(int ticks){
   this.Tickslefttocast=ticks;
 }
 public void setCaster(LivingEntity caster){
   this.Caster=caster;
 }
 public abstract void whileActive();//called every tick after the castime
 public abstract void afterCast();//called once after the castime is up
 public abstract void onSpellCast();//called when the spell is cast
 public abstract void onSpellEnded();//called when the spell is dispelled or ends
 public abstract void whileOngoing();//called every tick while the spell is ongoing
 public abstract void whileCasting();//called while the spell is being cast
 private void counter(){
      if(Tickslefttocast!=0 && this.isOngoing){
         whileCasting();
         Tickslefttocast--;
      }
      if(Tickslefttocast==0 && this.isOngoing){
         if(count){
            afterCast();
            count=false;
         }
         this.isBeingCast=false;
         this.isActive=true;
         this.whileActive();
      }
   }
public void endSpell(){
   onSpellEnded();
   this.isOngoing=false;
   this.isActive=false;
}
}

