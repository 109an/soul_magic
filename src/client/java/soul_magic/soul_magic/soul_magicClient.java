package soul_magic.soul_magic;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import soul_magic.soul_magic.geckolib.SpellFireballEntityRender;
import soul_magic.soul_magic.hud.RageViganette;

public class Soul_magicClient implements ClientModInitializer {
	private static KeyBinding keyBinding;
	@Override
	public void onInitializeClient() {
	EntityRendererRegistry.register(Soul_magic.ARCANE_SPELL_PROJECTILE, FlyingItemEntityRenderer::new);
		EntityRendererRegistry.register(Soul_magic.SPELL_FIREBALL, SpellFireballEntityRender::new);
		ClientTickEvents.START_CLIENT_TICK.register(client -> RageViganette.renderCheck());
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