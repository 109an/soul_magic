package soul_magic.soul_magic.spells.spellEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.World.ExplosionSourceType;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class SpellFireball extends ExplosiveProjectileEntity implements GeoEntity{
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    protected static final RawAnimation SPIN = RawAnimation.begin().thenLoop("spin");
    public SpellFireball(EntityType<? extends ExplosiveProjectileEntity> entityType, World world) {
        super(entityType, world);
    }
    public SpellFireball(EntityType<? extends ExplosiveProjectileEntity> type, double x, double y, double z, double directionX, double directionY, double directionZ, World world) {
        super(type, x, y, z, directionX, directionY, directionZ, world);
    }
    public SpellFireball(EntityType<? extends ExplosiveProjectileEntity> type, LivingEntity owner, double directionX, double directionY, double directionZ, World world) {
        super(type, owner, directionX, directionY, directionZ, world);
        
    }
    public SpellFireball(EntityType<? extends ExplosiveProjectileEntity> type, LivingEntity owner,  World world) {
        super(type, owner, Vec3d.fromPolar(owner.getPitch(), owner.getYaw()).x, Vec3d.fromPolar(owner.getPitch(), owner.getYaw()).y, Vec3d.fromPolar(owner.getPitch(), owner.getYaw()).z, world);
        this.setRotation(owner.getYaw(), owner.getPitch());
    }
    protected void initDataTracker() {
    }
    @Override
    public void onCollision(HitResult hitResult) {
     this.world.createExplosion( this.getOwner(), hitResult.getPos().getX(), hitResult.getPos().getY(), hitResult.getPos().getZ(), 50, true, ExplosionSourceType.BLOCK);
     this.kill();
    }
    @Override
    protected boolean isBurning() {
        return false;
    }
    @Override
    public void tick(){
        super.tick();
    }
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }
    private<T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState){
        tAnimationState.getController().setAnimation(SPIN);
        return PlayState.CONTINUE;
    }
    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
       controllers.add(new AnimationController<>(this, this::predicate));
    }
    public void explode(World world, int power){
        world.createExplosion(getOwner(), this.getX(), this.getY(), this.getZ(), power, ExplosionSourceType.BLOCK);
        HitResult hitResult = this.raycast(power, power, false);
        if(hitResult.getType() == HitResult.Type.BLOCK){
            //world.setBlockState(hitResult.getPos(), SoulFireBlock.getState(world, lastNetherPortalPosition))
        }
    }
}