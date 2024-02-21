package soul_magic.soul_magic;
import net.minecraft.world.World;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
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
}