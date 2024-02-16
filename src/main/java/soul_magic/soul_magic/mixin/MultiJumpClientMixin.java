package soul_magic.soul_magic.mixin;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import soul_magic.soul_magic.soul_magic;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class MultiJumpClientMixin
{

    @Shadow public Input input;
    int jumps;
    LivingEntity player=((LivingEntity) (Object) this);
	@Inject(at = @At("HEAD"), method = "tickMovement")
	private void init(CallbackInfo info) 
	{
    StatusEffectInstance multijump=this.player.getStatusEffect(soul_magic.MULTIJUMP);
    if(player.isOnGround() && multijump !=null)
    {
     jumps=multijump.getAmplifier()+1;
    }
    if (multijump !=null && input.jumping && jumps>0) 
    {
        jumps--;
        player.setVelocity(player.getVelocity().getX(), 0.35, player.getVelocity().getZ());
        player.setJumping(true);
    }
	}
}