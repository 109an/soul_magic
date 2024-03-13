package soul_magic.soul_magic.spells.spellEntity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.HitResult;
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

public class SpellFireball extends ProjectileEntity implements GeoEntity{
    //TODO the uv mappings for this dont seem to be working, also it never moves or rotates
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    protected static final RawAnimation SPIN = RawAnimation.begin().thenLoop("spin");
     public SpellFireball(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }
    protected void initDataTracker() {
    }
    @Override
    public void onCollision(HitResult hitResult) {
     this.world.createExplosion( this.getOwner(), hitResult.getPos().getX(), hitResult.getPos().getY(), hitResult.getPos().getZ(), 0.5f, true, ExplosionSourceType.BLOCK);
     this.kill();
    }
    @Override
    public void tick(){
        super.tick();
        if(this.distanceTraveled>50){
            this.world.createExplosion( this.getOwner(), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 0.5f, true, ExplosionSourceType.BLOCK);
            this.kill(); 
        }
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

}