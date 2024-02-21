package soul_magic.soul_magic.mixin;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import soul_magic.soul_magic.SoulBottle;
import soul_magic.soul_magic.Soul_magic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.llamalad7.mixinextras.sugar.Local;
@Mixin(LivingEntity.class)
public abstract class SoulTrapMixin
{
	@Inject(at = @At("HEAD"), method = "onDeath")
	private void init(CallbackInfo info, @Local DamageSource damageSource)
	{
    LivingEntity entity=((LivingEntity) (Object) this);
	 if(entity.hasStatusEffect(Soul_magic.SOULTRAP) && entity.getType()!= EntityType.IRON_GOLEM && entity.getType()!= EntityType.SNOW_GOLEM){
		if (damageSource.getAttacker() instanceof PlayerEntity){
			PlayerEntity player=((PlayerEntity)damageSource.getAttacker());
			for(int i=0; i<player.getInventory().main.size(); i++){ 
				Item item=player.getInventory().main.get(i).getItem();
				if (item instanceof SoulBottle){
					ItemStack soulbottle=(player.getInventory().main.get(i));
					int num=1;
					if(((SoulBottle)item).tier==1){
						num=4;
					}
					if(((SoulBottle)item).tier==2){
						num=25;
					}
					if(((SoulBottle)item).tier==3){
						num=100;
					}
					double ammount=(entity.getAttributeBaseValue(EntityAttributes.GENERIC_MAX_HEALTH))*(entity.getAttributeBaseValue(EntityAttributes.GENERIC_MAX_HEALTH)/num);
					if (soulbottle.hasNbt() && ammount+soulbottle.getNbt().getDouble("fill")<=100){
						NbtCompound nbt=soulbottle.getNbt();
						double fill=nbt.getDouble("fill")+ammount;
						nbt.putDouble("fill", fill);
						soulbottle.setNbt(nbt);
						break;
					}
					if(soulbottle.hasNbt() && ammount+soulbottle.getNbt().getDouble("fill")>100 && ammount<100){
						NbtCompound nbt=soulbottle.getNbt();
						nbt.putDouble("fill", 100);
						soulbottle.setNbt(nbt);
						break;
					}
					if(soulbottle.hasNbt() && ammount>100){
						NbtCompound nbt=soulbottle.getNbt();
						nbt.putDouble("fill", ammount);
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