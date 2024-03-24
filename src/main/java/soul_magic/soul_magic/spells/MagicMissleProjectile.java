package soul_magic.soul_magic.spells;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
//TODO delete abstractspell proj and make this extend explosive proj entity

public class MagicMissleProjectile extends ExplosiveProjectileEntity {
    LivingEntity target;
    int power;
    public MagicMissleProjectile(EntityType<? extends ExplosiveProjectileEntity> entityType, World world) {
		super(entityType, world);
	}
	public MagicMissleProjectile(World world, LivingEntity owner, int x, int y, int z, int dx, int dy, int dz, int power) {
		super(null, x, y, z, dx, dy, dz, world);
		this.power = power;
	}
 
	public MagicMissleProjectile(World world, LivingEntity owner,  double dx, double dy, double dz, int power) {
		super(null, owner , dx, dy, dz, world);
		this.power = power;
	}
    public MagicMissleProjectile(World world, LivingEntity owner,  double pitch, double yaw, int power) {
		super(null, owner , Vec3d.fromPolar(((float)pitch), ((float)yaw)).x, Vec3d.fromPolar(((float)pitch), ((float)yaw)).y, Vec3d.fromPolar(((float)pitch), ((float)yaw)).z, world);
		this.power = power;
	}
    @Override
    public void tick() {
        super.tick();
        if (this.world.isChunkLoaded(this.getChunkPos().x, this.getChunkPos().z)) {
        if(target == null){
				List<Entity> entities = world.getOtherEntities(this.getOwner(), Box.of(this.getPos(), 32, 32, 32));
				ArrayList<LivingEntity> living = new ArrayList<LivingEntity>();
				for (Entity entity : entities) {
					if(entity instanceof LivingEntity){
						living.add(((LivingEntity)entity));
					}
				}
				target = world.getClosestEntity(living, TargetPredicate.DEFAULT, null, this.getX(), this.getY(), this.getZ());	
			}
			if(target != null){
                if(target.getX() > this.getX()){
                   this.powerX ++;
                }
                if(target.getX() < this.getX()){
                    this.powerX --;
                }
                if(target.getY() > this.getY()){
                   this.powerY++;
                }
                if(target.getY() < this.getY()){
                   this.powerY--;
                }
                if(target.getZ() > this.getZ()){
                   this.powerZ++;
                }
                if(target.getZ() < this.getZ()){
                   this.powerZ--;
                }
			}
        }
    }
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if(entityHitResult.getEntity() instanceof LivingEntity){
            LivingEntity entity = ((LivingEntity)entityHitResult.getEntity());
            entity.damage(entity.getDamageSources().indirectMagic(this.getOwner(), this.getOwner()), this.power*2);
            entity.takeKnockback(power, this.getVelocity().negate().x, this.getVelocity().negate().y);
        }
    }
}
