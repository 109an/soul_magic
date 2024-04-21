package soul_magic.soul_magic;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import soul_magic.soul_magic.Effects.RageEffect;
import soul_magic.soul_magic.Effects.SoulTrapEffect;
import soul_magic.soul_magic.Effects.VampirismEffect;
import soul_magic.soul_magic.Items.MagicSwordItem;
import soul_magic.soul_magic.Items.SoulBottleTier1;
import soul_magic.soul_magic.Items.SoulBottleTier2;
import soul_magic.soul_magic.Items.SoulBottleTier3;
import soul_magic.soul_magic.Items.SoulShard;
import soul_magic.soul_magic.Items.SoulToolMaterial;
import soul_magic.soul_magic.Items.SpellBook;
import soul_magic.soul_magic.Items.SpellScroll;
import soul_magic.soul_magic.spells.spellEntity.MagicMissleProjectile;
import soul_magic.soul_magic.spells.spellEntity.SoulTrapProjectile;
import soul_magic.soul_magic.spells.spellEntity.SpellFireball;
import net.minecraft.registry.Registry;
public class Soul_magic implements ModInitializer
 {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Item SOUL_SHARD = new SoulShard(new FabricItemSettings());
	public static final Item MAGIC_SWORD = new MagicSwordItem(SoulToolMaterial.INSTANCE, 7, -1.6f,  new FabricItemSettings().maxCount(1));
	public static final Item SOUL_BOTTLE_TIER1 = new SoulBottleTier1(new FabricItemSettings().maxCount(1));
	public static final Item SOUL_BOTTLE_TIER2 = new SoulBottleTier2(new FabricItemSettings().maxCount(1));
	public static final Item SOUL_BOTTLE_TIER3 = new SoulBottleTier3(new FabricItemSettings().maxCount(1));
	public static final Item SPELL_SCROLL = new SpellScroll(new FabricItemSettings().maxCount(1));
	public static final Item SPELL_BOOK = new SpellBook(new FabricItemSettings().maxCount(1));
	public static final StatusEffect VAMPIRISM = new VampirismEffect();
	public static final StatusEffect SOULTRAP = new SoulTrapEffect();
	public static final StatusEffect RAGE = new RageEffect();
	public static final DefaultParticleType SPELL_CAST = FabricParticleTypes.simple();
	public static final EntityType<SoulTrapProjectile> SOUL_TRAP_PROJECTILE = Registry.register (Registries.ENTITY_TYPE, new Identifier("soul_magic", "spellprojectile"), FabricEntityTypeBuilder.<SoulTrapProjectile>create(SpawnGroup.MISC, SoulTrapProjectile::new).dimensions(EntityDimensions.fixed(0.25F, 0.25F)).build());
	public static final EntityType<SpellFireball> SPELL_FIREBALL = Registry.register (Registries.ENTITY_TYPE, new Identifier("soul_magic", "spell_fireball"), FabricEntityTypeBuilder.<SpellFireball>create(SpawnGroup.MISC, SpellFireball::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).build());
	public static final EntityType<MagicMissleProjectile> MAGIC_MISSLE = Registry.register (Registries.ENTITY_TYPE, new Identifier("soul_magic", "spellprojectile"), FabricEntityTypeBuilder.<MagicMissleProjectile>create(SpawnGroup.MISC, MagicMissleProjectile::new).dimensions(EntityDimensions.fixed(0.25F, 0.25F)).build());
	public static final Logger LOGGER = LoggerFactory.getLogger("soul_magic");

	@Override
	public void onInitialize()
	{
		Registry.register(Registries.STATUS_EFFECT, new Identifier("soul_magic", "rage"), RAGE);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("soul_magic", "soul_trap"), SOULTRAP);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("soul_magic", "vampirism"), VAMPIRISM);
		Registry.register(Registries.ITEM, new Identifier("soul_magic", "soul_shard"), SOUL_SHARD);
		Registry.register(Registries.ITEM, new Identifier("soul_magic", "soul_bottle_tier1"), SOUL_BOTTLE_TIER1);
		Registry.register(Registries.ITEM, new Identifier("soul_magic", "soul_bottle_tier2"), SOUL_BOTTLE_TIER2);
		Registry.register(Registries.ITEM, new Identifier("soul_magic", "soul_bottle_tier3"), SOUL_BOTTLE_TIER3);
		Registry.register(Registries.ITEM, new Identifier("soul_magic", "spell_scroll"), SPELL_SCROLL);
		Registry.register(Registries.ITEM, new Identifier("soul_magic", "magic_sword_item"), MAGIC_SWORD);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier("soul_magic", "spell_cast"), SPELL_CAST);
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("hi");
	}
}
