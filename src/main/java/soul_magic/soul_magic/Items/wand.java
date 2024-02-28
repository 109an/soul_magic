package soul_magic.soul_magic.Items;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import soul_magic.soul_magic.Soul_magic;
import soul_magic.soul_magic.spells.spellEntity.SpellFireball;

public class wand extends Item{
    
    public wand(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        
         //playerEntity.getOffHandStack().damage(50, playerEntity, player -> player.sendToolBreakStatus(hand));
         SpellFireball fireball=new SpellFireball(Soul_magic.SPELL_FIREBALL, world);
         fireball.setPosition(playerEntity.getPos());
         fireball.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), playerEntity.getRoll(), 3, 0.3f);
         world.spawnEntity(fireball);
        return TypedActionResult.success(playerEntity.getStackInHand(hand)); 
    }
    
}
