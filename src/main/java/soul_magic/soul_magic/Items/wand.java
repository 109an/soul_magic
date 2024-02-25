package soul_magic.soul_magic.Items;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import soul_magic.soul_magic.spells.Spell;
import soul_magic.soul_magic.spells.Spell.Spells;

public class wand extends Item{
    
    public wand(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        Spell damage=new Spell(world, playerEntity, 8, Spells.MAGIC_MISSLE, 2);
        damage.castSpell();
         //playerEntity.getOffHandStack().damage(50, playerEntity, player -> player.sendToolBreakStatus(hand));
        return TypedActionResult.success(playerEntity.getStackInHand(hand)); 
    }
    
}
