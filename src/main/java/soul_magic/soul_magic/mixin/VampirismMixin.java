package soul_magic.soul_magic.mixin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import soul_magic.soul_magic.Soul_magic;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(LivingEntity.class)
public abstract class VampirismMixin
{
	@Inject(at = @At("HEAD"), method = "onAttacking")
	private void init(CallbackInfo info) 
	{
	LivingEntity attacking=((LivingEntity) (Object) this);
    if (attacking !=null && attacking.hasStatusEffect(Soul_magic.VAMPIRISM))
    {
	int amplifier=attacking.getStatusEffect(Soul_magic.VAMPIRISM).getAmplifier();
	System.out.println(amplifier);
    float attackdam=((float)attacking.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE));
	float healing=(attackdam/8)*amplifier;
    attacking.heal(healing);
    }
	}
}