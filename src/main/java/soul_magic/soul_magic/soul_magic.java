package ancient_arcana.arcana;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ancient_arcana.arcana.Effects.MultiJumpEffect;
import ancient_arcana.arcana.Effects.RageEffect;
import ancient_arcana.arcana.Effects.SoulTrapEffect;
import ancient_arcana.arcana.Effects.VampirismEffect;
import net.minecraft.registry.Registry;
public class Ancient_arcana implements ModInitializer
 {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final wand WAND = new wand(new FabricItemSettings().maxCount(1));
	public static final Item SOUL_BOTTLE = new SoulBottle(new FabricItemSettings().maxCount(1));
	public static final StatusEffect VAMPIRISM = new VampirismEffect();
	public static final StatusEffect SOULTRAP = new SoulTrapEffect();
	public static final StatusEffect RAGE = new RageEffect();
	public static final StatusEffect MULTIJUMP = new MultiJumpEffect();
	public static final EntityType<SoulBottleProjectile> SOUL_BOTTLE_PROJECTILE = Registry.register (Registries.ENTITY_TYPE, new Identifier("ancient_arcana", "soul_bottle"), FabricEntityTypeBuilder.<SoulBottleProjectile>create(SpawnGroup.MISC, SoulBottleProjectile::new).dimensions(EntityDimensions.fixed(0.25F, 0.25F)).trackRangeBlocks(4).trackedUpdateRate(10).build());
	public static final EntityType<SpellProjectile> SPELL_PROJECTILE = Registry.register (Registries.ENTITY_TYPE, new Identifier("ancient_arcana", "spellprojectile"), FabricEntityTypeBuilder.<SpellProjectile>create(SpawnGroup.MISC, SpellProjectile::new).dimensions(EntityDimensions.fixed(0.25F, 0.25F)).trackRangeBlocks(4).trackedUpdateRate(10).build());
	public static final Logger LOGGER = LoggerFactory.getLogger("ancient_arcana");

	@Override
	public void onInitialize()
	{
		Registry.register(Registries.STATUS_EFFECT, new Identifier("ancient_arcana", "rage"), RAGE);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("ancient_arcana", "soul_trap"), SOULTRAP);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("ancient_arcana", "vampirism"), VAMPIRISM);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("ancient_arcana", "multi_jump"), MULTIJUMP);
		Registry.register(Registries.ITEM, new Identifier("ancient_arcana", "wand"), WAND);
		Registry.register(Registries.ITEM, new Identifier("ancient_arcana", "soul_bottle"), SOUL_BOTTLE);
		//Registry.register(Registries.ITEM, new Identifier("ancient_arcana", "soul_bottle2"), SOULBOTTLE);
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("hi");
	}
}