package soul_magic.soul_magic;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.particle.EnchantGlyphParticle;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.text.Text;
import soul_magic.soul_magic.geckolib.SpellFireballEntityRender;
import soul_magic.soul_magic.hud.RageViganette;

public class Soul_magicClient implements ClientModInitializer {
	private static KeyBinding keyBinding;
	@Override
	public void onInitializeClient() {
	EntityRendererRegistry.register(Soul_magic.SOUL_TRAP_PROJECTILE, InvisEntityRender::new);
	EntityRendererRegistry.register(Soul_magic.MAGIC_MISSLE, InvisEntityRender::new);
		EntityRendererRegistry.register(Soul_magic.SPELL_FIREBALL, SpellFireballEntityRender::new);
		ParticleFactoryRegistry.getInstance().register(Soul_magic.SPELL_CAST, EnchantGlyphParticle.EnchantFactory::new);
		 MinecraftClient minecraftClient = MinecraftClient.getInstance();
        ClientPlayerEntity player = minecraftClient.player;
		RageViganette rageViganette = new RageViganette();
		ClientTickEvents.START_CLIENT_TICK.register(client -> {
			if (player != null){
				double health=player.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH);
				if(player.hasStatusEffect(Soul_magic.RAGE) && health<=health/2){
					System.out.println("hi");
					rageViganette.setRendering(true);
				}
			}
			else{
				rageViganette.setRendering(false);
			}
		});
		keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
    	"key.soul_magic.spellgui",
    	InputUtil.Type.KEYSYM,
    	GLFW.GLFW_KEY_R, // The keycode of the key
   		 "category.soul_magic.gui"));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
  	 	 while (keyBinding.wasPressed()) {
		client.player.sendMessage(Text.literal("Key 1 was pressed!"), false);}});
	Registers.register();
	}
}