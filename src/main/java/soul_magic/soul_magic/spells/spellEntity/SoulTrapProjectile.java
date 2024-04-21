package soul_magic.soul_magic.spells.spellEntity;
import net.minecraft.world.World;
import soul_magic.soul_magic.Soul_magic;
import soul_magic.soul_magic.util.ParticleShapes;

import net.minecraft.block.SoulSandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
public class SoulTrapProjectile extends ExplosiveProjectileEntity {
    LivingEntity target;
    int power;
    public SoulTrapProjectile(EntityType<? extends ExplosiveProjectileEntity> entityType, World world) {
		super(entityType, world);
	}
	public SoulTrapProjectile(World world, LivingEntity owner, int x, int y, int z, int dx, int dy, int dz, int power) {
		super(null, x, y, z, dx, dy, dz, world);
		this.power = power;
	}
 
	public SoulTrapProjectile(World world, LivingEntity owner,  double dx, double dy, double dz, int power) {
		super(null, owner , dx, dy, dz, world);
		this.power = power;
	}
	 public SoulTrapProjectile(World world, LivingEntity owner,  double pitch, double yaw, int power) {
		super(null, owner , Vec3d.fromPolar(((float)pitch), ((float)yaw)).x, Vec3d.fromPolar(((float)pitch), ((float)yaw)).y, Vec3d.fromPolar(((float)pitch), ((float)yaw)).z, world);
		this.power = power;
	}
	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {
		super.onEntityHit(entityHitResult);
		if(world instanceof ServerWorld)
		{ParticleShapes.burstShape(((ServerWorld)world),  ParticleTypes.SCULK_SOUL, entityHitResult.getPos().x, entityHitResult.getPos().y, entityHitResult.getPos().z, 150, 0.5, true);
		  ParticleShapes.burstShape(((ServerWorld)world),  ParticleTypes.ASH, entityHitResult.getPos().x, entityHitResult.getPos().y, entityHitResult.getPos().z, 150, 0.5, true);}
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
			ParticleShapes.burstShape(((ServerWorld)world),  ParticleTypes.SCULK_SOUL, hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z, 150, 0.5, true);
		  ParticleShapes.burstShape(((ServerWorld)world),  ParticleTypes.ASH, hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z, 150, 0.5, true);
		  }
		if(hitResult.getType() == HitResult.Type.BLOCK){
			 Vec3i pos = new Vec3i(((int)hitResult.getPos().x), ((int)hitResult.getPos().y), ((int)hitResult.getPos().z));
             BlockPos blockPos = new BlockPos(pos);
             if(!world.isAir(blockPos)){
				world.setBlockState(blockPos, SoulSandBlock.STATE_IDS.get(88));
             }
		}
	}
}