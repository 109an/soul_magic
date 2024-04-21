package soul_magic.soul_magic.Items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SpellBook extends Item{
    public SpellBook(Settings settings){
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use (World world, PlayerEntity playerEntity, Hand hand){
        ItemStack stack = playerEntity.getStackInHand(hand);
        return TypedActionResult.success(stack); 
    }
}

