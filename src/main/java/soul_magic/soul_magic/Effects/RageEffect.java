package soul_magic.soul_magic.Effects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
public class RageEffect extends StatusEffect {
  public RageEffect() {
    super(StatusEffectCategory.BENEFICIAL, 0x8B0000);
  }
  @Override
  public boolean canApplyUpdateEffect(int duration, int amplifier){
    return true;
  }
  @Override
  public void applyUpdateEffect(LivingEntity enitity, int amplifier){
    double health=enitity.getHealth();
    double maxhealth=enitity.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH);
    if(health<=maxhealth/2 && health>maxhealth/6){
      StatusEffectInstance strength=new StatusEffectInstance(StatusEffects.STRENGTH, 20, amplifier-1, false, false,true);
      enitity.addStatusEffect(strength);
    }
    if(health<=maxhealth/6 && amplifier>=2){
      StatusEffectInstance speed=new StatusEffectInstance(StatusEffects.SPEED, 20, amplifier-1, false, false, true);
      enitity.addStatusEffect(speed);

      if(amplifier>=3 && health<=maxhealth/4){
          StatusEffectInstance resistance=new StatusEffectInstance(StatusEffects.RESISTANCE, 20, 1, false, false, true);
          enitity.addStatusEffect(resistance);
      }
    }
  }
}