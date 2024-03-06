package soul_magic.soul_magic.Items;

import java.util.function.Consumer;
import java.util.function.Supplier;

import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;
import soul_magic.soul_magic.Soul_magic;
import soul_magic.soul_magic.geckolib.MagicSwordItemRender;

public class MagicSwordItem extends Item implements GeoItem{
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    protected static final RawAnimation GEM = RawAnimation.begin().thenLoop("magic_sword_item.animation");
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);
    public MagicSwordItem(Settings settings) {
        super(settings);
        
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
         consumer.accept(new RenderProvider() {
            private final MagicSwordItemRender renderer = new MagicSwordItemRender();

            @Override
            public BuiltinModelItemRenderer getCustomRenderer() {
                return this.renderer;
            }
        });
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return renderProvider;
    }
    @Override
    public double getTick(Object itemStack){
        return RenderUtils.getCurrentTick();
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
       return cache;
    }

    @Override
    public void registerControllers(ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GeoAnimatable>(this, 0, this::predicate));
    }
    private<T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState){
        tAnimationState.getController().setAnimation(GEM);
        return PlayState.CONTINUE;
    }
     public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker){
        if (stack.hasNbt()){
            NbtCompound nbt = stack.getNbt();
            int level = nbt.getInt("level");
            StatusEffectInstance soulTrap = new StatusEffectInstance(Soul_magic.SOULTRAP, 30, 2);
            target.addStatusEffect(soulTrap);
            target.damage(target.getDamageSources().indirectMagic(attacker, attacker), 4+level);
        }
        else{
            StatusEffectInstance soulTrap = new StatusEffectInstance(Soul_magic.SOULTRAP, 30, 2);
            target.addStatusEffect(soulTrap);
            target.damage(target.getDamageSources().indirectMagic(attacker, attacker), 5);
        }
       return super.postHit(stack, target, attacker);

    }
}
