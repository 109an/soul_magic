package ancient_arcana.arcana.mixin;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.llamalad7.mixinextras.sugar.Local;
import ancient_arcana.arcana.Ancient_arcana;
@Mixin(LivingEntity.class)
public abstract class SoulTrapMixin
{
	@Inject(at = @At("HEAD"), method = "onDeath")
	private void init(CallbackInfo info, @Local DamageSource damageSource)
	{
    LivingEntity entity=((LivingEntity) (Object) this);
	 if(entity.hasStatusEffect(Ancient_arcana.SOULTRAP) && entity.getType()!= EntityType.IRON_GOLEM && entity.getType()!= EntityType.SNOW_GOLEM){
		if (damageSource.getAttacker() instanceof PlayerEntity){
			PlayerEntity player=((PlayerEntity)damageSource.getAttacker());
			for(int i=0; i<player.getInventory().main.size(); i++){ 
				if (player.getInventory().main.get(i).getItem()==Ancient_arcana.SOUL_BOTTLE){
					ItemStack soulbottle=(player.getInventory().main.get(i));
					double ammount=(entity.getAttributeBaseValue(EntityAttributes.GENERIC_MAX_HEALTH))*(entity.getAttributeBaseValue(EntityAttributes.GENERIC_MAX_HEALTH)/4);//4 for the lowest level, 25 for the next level, and 100 for the last level
					if (soulbottle.hasNbt() && ammount+soulbottle.getNbt().getDouble("fill")<100){
						NbtCompound nbt=soulbottle.getNbt();
						double fill=nbt.getDouble("fill")+ammount;
						nbt.putDouble("fill", fill);
						soulbottle.setNbt(nbt);
						break;
					}
					if(!soulbottle.hasNbt()){
						NbtCompound nbt=soulbottle.getOrCreateNbt();
						nbt.putDouble("fill", ammount);
						soulbottle.setNbt(nbt);
						break;
					}	
					
				}
			}
		}
     }
	}
}