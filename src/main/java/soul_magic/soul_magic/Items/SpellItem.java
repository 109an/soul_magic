package soul_magic.soul_magic.Items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import soul_magic.soul_magic.Soul_magic;
import soul_magic.soul_magic.spells.SpellObject;

public class SpellItem extends Item{
    public SpellItem(Settings settings){
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        
        ItemStack stack = playerEntity.getStackInHand(hand);
        if(stack.hasNbt()){
            SpellObject spell = new SpellObject(world, playerEntity, stack.getNbt().getString("name"), stack.getNbt().getInt("power"));
            playerEntity.getItemCooldownManager().set(Soul_magic.SPELL_ITEM, spell.Spelltocast.cooldown);
           spell.castSpell();
        }
        return TypedActionResult.success(stack); 
    }
}
