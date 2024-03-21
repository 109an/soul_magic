package soul_magic.soul_magic.spells;
import net.minecraft.world.World;
import soul_magic.soul_magic.Soul_magic;
import soul_magic.soul_magic.util.ParticleShapes;
import net.minecraft.block.SculkBlock;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
public class ArcaneSpellProjectile extends  ThrownItemEntity{
	public int power;
	public ArcaneSpellProjectile(EntityType<? extends ThrownItemEntity> entityType, World world, int power) {
		super(entityType, world);
		this.power = power;
	}
 
	public ArcaneSpellProjectile(World world, LivingEntity owner, int power) {
		super(Soul_magic.ARCANE_SPELL_PROJECTILE, owner, world); 
		this.power = power;
	}
 
	public ArcaneSpellProjectile(World world, double x, double y, double z, int power) {
		super(Soul_magic.ARCANE_SPELL_PROJECTILE, x, y, z, world); 
		this.power = power;
	}
    @Override
	protected Item getDefaultItem() {
		return null;
	}
	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) { // called on entity hit.
		super.onEntityHit(entityHitResult);
		Entity entity = entityHitResult.getEntity(); 
		if(entity instanceof LivingEntity){
			StatusEffectInstance soultrap = new StatusEffectInstance(Soul_magic.SOULTRAP, 1200, 1);
			StatusEffectInstance wither = new StatusEffectInstance(Soul_magic.SOULTRAP, 600, power);
			((LivingEntity)entity).addStatusEffect(wither);
			((LivingEntity)entity).addStatusEffect(soultrap);
		}
		
	}
	@Override
	protected void onCollision(HitResult hitResult) 
	{ 
		super.onCollision(hitResult);
		if(this.world instanceof ServerWorld){
			ParticleShapes.burstShape(((ServerWorld)world),  ParticleTypes.SCULK_SOUL, hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z, 50, 0.5, true);
		  ParticleShapes.burstShape(((ServerWorld)world),  ParticleTypes.ASH, hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z, 50, 0.5, true);
		  }
		if(hitResult.getType() == HitResult.Type.BLOCK){
			 Vec3i pos = new Vec3i(((int)hitResult.getPos().x), ((int)hitResult.getPos().y), ((int)hitResult.getPos().z));
             BlockPos blockPos = new BlockPos(pos);
             if(!world.isAir(blockPos)){
				world.setBlockState(blockPos, SculkBlock.STATE_IDS.get(0));
             }
		}
	}
	@Override
	public void tick() {
		super.tick();
		if(!this.world.isClient) {
			ParticleShapes.sphereShape(((ServerWorld)world), ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), this.getWidth(), 5, true);
			ParticleShapes.sphereShape(((ServerWorld)world), ParticleTypes.SOUL, this.getX(), this.getY(), this.getZ(), this.getWidth(), 5, true);
		}
		 if (this.world.isChunkLoaded(this.getChunkPos().x, this.getChunkPos().z)) {
			super.tick();;
			Vec3d vec3d = this.getVelocity();
			double x = this.getX() + vec3d.x;
			double y = this.getY() + vec3d.y;
			double z = this.getZ() + vec3d.z;
			this.setVelocity(vec3d.add(1, 1, 1).multiply(0.8));
			this.world.addParticle(ParticleTypes.SOUL, x, y + 0.5, z, 0.0, 0.0, 0.0);
			this.setPosition(x, y, z);
     	 } 
		else {
			this.discard();
		}
	}
}