package soul_magic.soul_magic.Items;

import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;
import soul_magic.soul_magic.Soul_magic;
import soul_magic.soul_magic.geckolib.MagicSwordItemRender;
public class MagicSwordItem extends SwordItem implements GeoItem{
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);
    public MagicSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
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
        tAnimationState.getController().setAnimation(RawAnimation.begin().then("gem", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }
     public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker){
        if (stack.hasNbt()){
            NbtCompound nbt = stack.getNbt();
            int level = nbt.getInt("level");
            StatusEffectInstance soulTrap = new StatusEffectInstance(Soul_magic.SOULTRAP, 30, level/3);
            target.addStatusEffect(soulTrap);
        }
        else{
            StatusEffectInstance soulTrap = new StatusEffectInstance(Soul_magic.SOULTRAP, 30, 1);
            target.addStatusEffect(soulTrap);
        }
       return super.postHit(stack, target, attacker);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected){
        super.inventoryTick(stack, world, entity, slot, selected);
      if(entity instanceof PlayerEntity){
        if(!stack.hasNbt()){
            NbtCompound nbtCompound = new NbtCompound();
            nbtCompound.putInt("time_left", 1200);
            stack.setNbt(nbtCompound);
        }
        else{
            NbtCompound nbtCompound = stack.getNbt();
            nbtCompound.putInt("time_left", nbtCompound.getInt("time_left")-1);
            System.out.print(nbtCompound.getInt("time_left"));
            stack.setNbt(nbtCompound);
        }
        if(stack.getNbt().getInt("time_left") < 0){
            stack.decrement(1);
        }
      }
    }
    @Override
    public boolean hasGlint(ItemStack stack) {//this makes the sword constantly have a glint
        return true;
     }
}
