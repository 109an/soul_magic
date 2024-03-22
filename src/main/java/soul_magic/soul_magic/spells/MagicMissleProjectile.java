package soul_magic.soul_magic.spells;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import soul_magic.soul_magic.Soul_magic;

public class MagicMissleProjectile extends AbstractSpellProjectile {
    LivingEntity target;
    public MagicMissleProjectile(EntityType<? extends ThrownItemEntity> entityType, World world) {
		super(entityType, world);
	}
	public MagicMissleProjectile(World world, LivingEntity owner, int power) {
		super(world, Soul_magic.SOUL_TRAP_PROJECTILE, owner, power); 
		this.power = power;
	}
 
	public MagicMissleProjectile(World world, double x, double y, double z, int power) {
		super(world, Soul_magic.SOUL_TRAP_PROJECTILE, x, y, z, power); 
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
                //TODO remember, minecraft uses degrees, and the Math lib uses radians, you need to convert here
				double relx = target.getX() - this.getX();//0, 0, 0 at this
				double rely = target.getY() - this.getY();
				double relz = target.getZ() - this.getZ();
                Vec3d veloc = this.getVelocity();
                double thetaVeloc = Math.atan(veloc.y/veloc.x);
                double phiVeloc = Math.sqrt(Math.pow(veloc.x, 2) + Math.pow(veloc.y, 2))/veloc.z;
                double thetaBet = Math.atan(rely/relx);
                double phiBet = Math.sqrt(Math.pow(relx, 2) + Math.pow(rely, 2))/relz;
                if(thetaBet > thetaVeloc){
                    thetaVeloc ++;
                }
                else if(thetaBet < thetaVeloc){
                    thetaVeloc --;
                }
                if(phiBet > phiVeloc){
                    phiVeloc ++;
                }
                else if(phiBet < phiVeloc ){
                    phiVeloc --;
                }
                double length = veloc.length();
                double nx = length*Math.sin(phiVeloc)*Math.cos(thetaVeloc);
                double ny = length*Math.sin(phiVeloc)*Math.sin(thetaVeloc);
                double nz = length*Math.cos(phiVeloc);
                Vec3d nveloc = new Vec3d(nx, ny, nz);
                this.setVelocity(nveloc);
			}
        }
    }
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if(entityHitResult.getEntity() instanceof LivingEntity){
            LivingEntity entity = ((LivingEntity)entityHitResult.getEntity());
            entity.damage(entity.getDamageSources().indirectMagic(this.getOwner(), this.getOwner()), this.power*2);
            //entity.takeKnockback(lastRenderX, FALL_FLYING_FLAG_INDEX, DEFAULT_MIN_FREEZE_DAMAGE_TICKS);
        }
    }
}
