package ancient_arcana.arcana.mixin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ancient_arcana.arcana.Ancient_arcana;
@Mixin(LivingEntity.class)
public abstract class VampirismMixin
{
	@Inject(at = @At("HEAD"), method = "onAttacking")
	private void init(CallbackInfo info) 
	{
	LivingEntity attacking=((LivingEntity) (Object) this);
    if (attacking !=null && attacking.hasStatusEffect(Ancient_arcana.VAMPIRISM))
    {
	int amplifier=attacking.getStatusEffect(Ancient_arcana.VAMPIRISM).getAmplifier();
	System.out.println(amplifier);
    float attackdam=((float)attacking.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE));
	float healing=(attackdam/8)*amplifier;
    attacking.heal(healing);
    }
	}
}