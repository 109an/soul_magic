package soul_magic.soul_magic.Effects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class SoulTrapEffect extends StatusEffect{
    public SoulTrapEffect() {
      super(StatusEffectCategory.BENEFICIAL, 0x301934);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier){
      return true;
    }
    @Override
    public void applyUpdateEffect(LivingEntity enitity, int amplifier){
  }
}