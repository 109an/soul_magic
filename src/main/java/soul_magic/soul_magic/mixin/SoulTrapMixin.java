package soul_magic.soul_magic.mixin;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import soul_magic.soul_magic.Soul_magic;
import soul_magic.soul_magic.Items.SoulBottle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.llamalad7.mixinextras.sugar.Local;
@Mixin(LivingEntity.class)
public abstract class SoulTrapMixin{
	@Inject(at = @At("HEAD"), method = "onDeath")
	private void init(CallbackInfo info, @Local DamageSource damageSource){
    LivingEntity entity=((LivingEntity) (Object) this);
		if(entity.hasStatusEffect(Soul_magic.SOULTRAP) && entity.getType()!= EntityType.IRON_GOLEM && entity.getType()!= EntityType.SNOW_GOLEM){
			PlayerEntity player = entity.getWorld().getClosestPlayer(entity, 32);
			if(player !=null){
				SoulBottle.fill(entity, player);
				System.out.println("bye");
			}
		}
	}
}