package soul_magic.soul_magic.spells;

import java.util.List;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
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
            ParticleShapes.sphereShape(world, ParticleTypes.ASH, pos.x, pos.y, pos.z, size, 10, 0, false);
            ParticleShapes.sphereShape(world, ParticleTypes.SCULK_SOUL, pos.x, pos.y, pos.z, size, 10, 0, false);
            ParticleShapes.sphereShape(world, ParticleTypes.SOUL, pos.x, pos.y, pos.z, size, 10, 0, false);
            for (Entity hit : enties) { 
                if(hit.getPos().distanceTo(pos) <= size){
                    if(hit instanceof LivingEntity){
                        StatusEffectInstance statusEffectInstance = new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 10, false, false, false);
                        ((LivingEntity)hit).addStatusEffect(statusEffectInstance);
                    }
                    else{
                        hit.setVelocity(hit.getVelocity().normalize());
                    }
                }
                
            }
        }
    }
}
