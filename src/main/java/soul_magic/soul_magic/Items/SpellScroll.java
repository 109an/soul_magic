package soul_magic.soul_magic.Items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import soul_magic.soul_magic.Soul_magic;
import soul_magic.soul_magic.spells.spellEntity.SpellFireball;

public class SpellScroll extends Item{
    public SpellScroll(Settings settings){
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use (World world, PlayerEntity playerEntity, Hand hand){
        ItemStack stack = playerEntity.getStackInHand(hand);
        if(stack.hasNbt()){
           
        }
       SpellFireball spellFireball = new SpellFireball(Soul_magic.SPELL_FIREBALL, playerEntity, world);
        world.spawnEntity(spellFireball);
        return TypedActionResult.success(stack); 
    }
}
