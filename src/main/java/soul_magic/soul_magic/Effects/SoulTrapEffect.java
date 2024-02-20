package soul_magic.soul_magic.Effects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;
import soul_magic.soul_magic.ParticleShapes;

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
      if(enitity.isDead()){
      ParticleShapes.AroundEntity(enitity.getWorld(), ParticleTypes.SOUL, enitity, 200, 0.2);
      }
    }
  }
