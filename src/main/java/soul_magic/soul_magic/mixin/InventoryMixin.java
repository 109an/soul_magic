package soul_magic.soul_magic.mixin;
import net.minecraft.entity.player.PlayerInventory;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(PlayerInventory.class)
public abstract class InventoryMixin{
	@Inject(at = @At("HEAD"), method = "")
	private void init(CallbackInfo info) {

	}
}