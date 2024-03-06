package soul_magic.soul_magic.Items;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import soul_magic.soul_magic.Soul_magic;

public class SoulToolMaterial implements ToolMaterial {
    public static final SoulToolMaterial INSTANCE = new SoulToolMaterial();
    @Override
    public float getAttackDamage() {
        return 1.0f;
    }

    @Override
    public int getDurability() {
        return 1500;
    }

    @Override
    public int getEnchantability() {
        return 25;
    }

    @Override
    public int getMiningLevel() {
        return 3;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 8.0f;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Soul_magic.SOUL_SHARD);
    }
    
}
