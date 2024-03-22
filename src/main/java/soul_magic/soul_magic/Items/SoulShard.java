package soul_magic.soul_magic.Items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SoulShard extends Item{
    public SoulShard(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        StatusEffectInstance wither = new StatusEffectInstance(StatusEffects.WITHER, 10, 2);
        playerEntity.addStatusEffect(wither);
        playerEntity.getItemCooldownManager().set(this, 20);
        return TypedActionResult.success(playerEntity.getStackInHand(hand)); 
    }
}
