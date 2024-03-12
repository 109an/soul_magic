package soul_magic.soul_magic.Items;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import soul_magic.soul_magic.spells.SpellObject;
import soul_magic.soul_magic.spells.SpellObject.Spell;

public class wand extends Item{
    
    public wand(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if(playerEntity.getOffHandStack().getItem() == Items.FEATHER)
           { SpellObject spellObject=new SpellObject(world, playerEntity, Spell.MISTY_STEP, 1);
            spellObject.castSpell();}
        if(playerEntity.getOffHandStack().getItem() == Items.SOUL_SAND)
        { SpellObject spellObject=new SpellObject(world, playerEntity, Spell.SLOW, 3);
            spellObject.castSpell();}
        if(playerEntity.getOffHandStack().getItem() == Items.SHIELD)
        { SpellObject spellObject=new SpellObject(world, playerEntity, Spell.SHIELD, 3);
            spellObject.castSpell();}
        if(playerEntity.getOffHandStack().getItem() == Items.IRON_SWORD)
        { SpellObject spellObject=new SpellObject(world, playerEntity, Spell.SUMMON_SWORD, 3);
            spellObject.castSpell();}
        if(playerEntity.getOffHandStack().getItem() == Items.SOUL_TORCH)
        { SpellObject spellObject=new SpellObject(world, playerEntity, Spell.SOUL_TRAP, 3);
            spellObject.castSpell();}
            if(playerEntity.getOffHandStack().getItem() == Items.TNT)
            { SpellObject spellObject=new SpellObject(world, playerEntity, Spell.SHOCKWAVE, 3);
                spellObject.castSpell();}
        return TypedActionResult.success(playerEntity.getStackInHand(hand)); 
    }
}
