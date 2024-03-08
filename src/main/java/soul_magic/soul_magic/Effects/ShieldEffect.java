package soul_magic.soul_magic.Effects;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import soul_magic.soul_magic.util.ParticleShapes;

public class ShieldEffect extends StatusEffect{
       public ShieldEffect() {
      super(StatusEffectCategory.BENEFICIAL, 0x301934);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier){
      return true;
    }
    @Override
    public void applyUpdateEffect(LivingEntity enitity, int amplifier){
        int size = amplifier+1;
         Box box=new Box(enitity.getX()-size, enitity.getY()-size, enitity.getZ()-size, enitity.getX()+size, enitity.getY()+size, enitity.getZ()+size);
         List<Entity> enties = enitity.getWorld().getOtherEntities(enitity, box);
         ParticleShapes.sphereShape(enitity.getWorld(), ParticleTypes.ENCHANT, enitity.getX(), enitity.getY(), enitity.getZ(), size, 100, 0, false);
        for (Entity hit : enties) {
            if(hit instanceof ProjectileEntity && hit.distanceTo(enitity) <= size){
                hit.addVelocity(hit.getVelocity().negate().multiply(0.5));
                enitity.playSound(SoundEvents.ITEM_SHIELD_BLOCK, 1, 1);
                if(hit.getVelocity() != Vec3d.ZERO && hit.getVelocity().length() < 0.05){
                    hit.addVelocity(hit.getVelocity().negate().multiply(2));
                }
                if(hit.getVelocity() == Vec3d.ZERO){
                    hit.kill();
                }  
            }
        }
  }
}
