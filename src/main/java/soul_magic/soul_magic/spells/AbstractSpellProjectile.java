package soul_magic.soul_magic.spells;
import net.minecraft.world.World;
import soul_magic.soul_magic.util.ParticleShapes;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
public abstract class AbstractSpellProjectile extends  ThrownItemEntity{
	public int power;
	public boolean noclip = false;
	public AbstractSpellProjectile(EntityType<? extends ThrownItemEntity> entityType, World world) {
		super(entityType, world);
		this.setNoGravity(true);
	}
	public AbstractSpellProjectile(World world, EntityType<? extends ThrownItemEntity> entityType, LivingEntity owner, int power) {
		super(entityType, owner, world); 
		this.setNoGravity(true);
		this.power = power;
	}
	public AbstractSpellProjectile(World world, EntityType<? extends ThrownItemEntity> entityType, double x, double y, double z, int power) {
		super(entityType, x, y, z, world);
		this.setNoGravity(true);
		this.power = power;
	}
    @Override
	protected Item getDefaultItem() {
		return null;
	}
	@Override
	public void tick() {
		super.tick();
		if(this.world instanceof ServerWorld) {
			ParticleShapes.sphereShape(((ServerWorld)world), ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), this.getWidth(), 5, true);
			ParticleShapes.sphereShape(((ServerWorld)world), ParticleTypes.SOUL, this.getX(), this.getY(), this.getZ(), this.getWidth(), 5, true);
		}
		 if (this.world.isChunkLoaded(this.getChunkPos().x, this.getChunkPos().z)) {
			super.tick();
			 ProjectileUtil.setRotationFromVelocity(this, 0.2F);
			Vec3d vec3d = this.getVelocity();
			double x = this.getX() + vec3d.x;
			double y = this.getY() + vec3d.y;
			double z = this.getZ() + vec3d.z;
			this.setVelocity(vec3d.add(vec3d.multiply(1/vec3d.length()*0.1)).multiply(0.8));
			this.world.addParticle(ParticleTypes.SOUL, x, y + 0.5, z, 0.0, 0.0, 0.0);
			this.setPosition(x, y, z);
     	 } 
		else {
			this.discard();
		}
	}
	public void setPower(int power){
		this.power = power;
	}
	public void setNoclip(boolean noclip){
		this.noClip = noclip;
	}
}