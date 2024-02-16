package ancient_arcana.arcana;
import ancient_arcana.arcana.Spell.SpellShapes;
import ancient_arcana.arcana.Spell.Spells;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class wand extends Item{
    
    public wand(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        Spell damage=new Spell(world, playerEntity, 8, SpellShapes.PROJECTILE, Spells.DAMAGE, 10, 10, true);
        damage.startSpell();
         //playerEntity.getOffHandStack().damage(50, playerEntity, player -> player.sendToolBreakStatus(hand));
        return TypedActionResult.success(playerEntity.getStackInHand(hand)); 
    }
    
}
