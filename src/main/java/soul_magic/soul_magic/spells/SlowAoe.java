package soul_magic.soul_magic.spells;

import java.util.List;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import soul_magic.soul_magic.util.ParticleShapes;

public class SlowAoe {
    public World world;
    public LivingEntity caster;
    public Vec3d pos;
    public int level;
    public int ticksLeft = 1200;
    public SlowAoe(World world, LivingEntity caster, Vec3d pos, int level){
        this.level = level - 4;
        this.caster = caster;
        this.pos = pos;
        this.world = world;
    }
    public void start(){
        ServerTickEvents.START_SERVER_TICK.register(server -> tick());
    }
    public void tick(){
        if(this.ticksLeft > 0){
            this.ticksLeft -= 1;
            int size = 10;
            Box box=new Box(pos.x-size, pos.y-size, pos.z-size, pos.x+size, pos.y+size, pos.z+size);
            List<Entity> enties = world.getOtherEntities(caster, box);
            if(world instanceof ServerWorld){
            ParticleShapes.sphereShape(((ServerWorld)world), ParticleTypes.ASH, pos.x, pos.y, pos.z, size, 10, false);
            ParticleShapes.sphereShape(((ServerWorld)world), ParticleTypes.SCULK_SOUL, pos.x, pos.y, pos.z, size, 10, false);
            ParticleShapes.sphereShape(((ServerWorld)world), ParticleTypes.SOUL, pos.x, pos.y, pos.z, size, 10, false);
            }
            for (Entity hit : enties) { 
                if(hit.getPos().distanceTo(pos) <= size){
                    if(hit instanceof LivingEntity && caster.getUuid() != hit.getUuid()){
                        if(((LivingEntity)hit).getAttributeBaseValue(EntityAttributes.GENERIC_MAX_HEALTH) <= 20){
                            StatusEffectInstance statusEffectInstance3 = new StatusEffectInstance(StatusEffects.SLOWNESS, 1, 3, false, false, false);
                            ((LivingEntity)hit).addStatusEffect(statusEffectInstance3);
                        }
                        if(((LivingEntity)hit).getAttributeBaseValue(EntityAttributes.GENERIC_MAX_HEALTH) <= 50){
                            StatusEffectInstance statusEffectInstance = new StatusEffectInstance(StatusEffects.SLOWNESS, 1, 1, false, false, false);
                            ((LivingEntity)hit).addStatusEffect(statusEffectInstance);
                        }
                    }
                   else if(hit instanceof ProjectileEntity){
                        if(hit.getVelocity().length() > Vec3d.ZERO.normalize().multiply(0.4).length() && hit instanceof ProjectileEntity){
                            hit.setVelocity(hit.getVelocity().normalize().multiply(0.4));
                        }
                    }
                }
                
            }
        }
    }
}
