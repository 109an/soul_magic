package soul_magic.soul_magic.spells;
import net.minecraft.world.World;
import soul_magic.soul_magic.Soul_magic;
import soul_magic.soul_magic.util.ParticleShapes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
public class ArcaneSpellProjectile extends  ThrownItemEntity{
	
	public ArcaneSpellProjectile(EntityType<? extends ThrownItemEntity> entityType, World world) {
		super(entityType, world);
	}
 
	public ArcaneSpellProjectile(World world, LivingEntity owner) {
		super(Soul_magic.ARCANE_SPELL_PROJECTILE, owner, world); 
	}
 
	public ArcaneSpellProjectile(World world, double x, double y, double z) {
		super(Soul_magic.ARCANE_SPELL_PROJECTILE, x, y, z, world); 
	}
    @Override
	protected Item getDefaultItem() {
		return null;
	}
	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) { // called on entity hit.
		super.onEntityHit(entityHitResult);
	}
	@Override
	protected void onCollision(HitResult hitResult) 
	{ 
		super.onCollision(hitResult);
	}
	@Override
	public void tick() {
		super.tick();
		 ParticleShapes.sphereShape(world, ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), this.getWidth(), 5, 0.2, true);
     	 ParticleShapes.sphereShape(world, ParticleTypes.SOUL, this.getX(), this.getY(), this.getZ(), this.getWidth(), 5, 0.2, true);
		 this.setVelocity(this.getVelocity().multiply(1.02));
	}
}