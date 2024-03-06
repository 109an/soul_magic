package soul_magic.soul_magic.Items;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import soul_magic.soul_magic.hud.RageViganette;
import soul_magic.soul_magic.spells.SpellObject;
import soul_magic.soul_magic.spells.SpellObject.Spell;

public class wand extends Item{
    
    public wand(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
            SpellObject spellObject=new SpellObject(world, playerEntity, Spell.MISTY_STEP, 9);
            spellObject.castSpell();
            RageViganette rageViganette = new RageViganette();
            rageViganette.setRendering(true);
        return TypedActionResult.success(playerEntity.getStackInHand(hand)); 
    }
}
