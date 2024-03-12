package soul_magic.soul_magic.Effects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class SoulTrapEffect extends StatusEffect{
    StatusEffectInstance statuseffect;
    public SoulTrapEffect() {
      super(StatusEffectCategory.BENEFICIAL, 0x301934);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier){
      statuseffect= new StatusEffectInstance(StatusEffects.WITHER, duration, amplifier);
      return true;
    }
    @Override
    public void applyUpdateEffect(LivingEntity enitity, int amplifier){
      if(statuseffect != null){
      enitity.addStatusEffect(statuseffect);
      }
  }
}