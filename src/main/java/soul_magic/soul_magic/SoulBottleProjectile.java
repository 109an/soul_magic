package ancient_arcana.arcana;
import net.minecraft.world.World;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
public class SoulBottleProjectile extends  ThrownItemEntity{
	
	public SoulBottleProjectile(EntityType<? extends ThrownItemEntity> entityType, World world) {
		super(entityType, world);
	}
 
	public SoulBottleProjectile(World world, LivingEntity owner) {
		super(Ancient_arcana.SOUL_BOTTLE_PROJECTILE, owner, world); 
	}
 
	public SoulBottleProjectile(World world, double x, double y, double z) {
		super(Ancient_arcana.SOUL_BOTTLE_PROJECTILE, x, y, z, world); 
	}
    @Override
	protected Item getDefaultItem() {
		return Ancient_arcana.SOUL_BOTTLE;
	}
}