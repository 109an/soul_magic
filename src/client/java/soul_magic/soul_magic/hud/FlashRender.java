package soul_magic.soul_magic.hud;

import com.mojang.blaze3d.systems.RenderSystem;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
public class FlashRender {
     private static final Identifier WHITE = new Identifier("soul_magic", "textures/hud/white.png");
    public int duration=80;
    private MinecraftClient client = MinecraftClient.getInstance();
    private void effect(MatrixStack matrixStack, float tickDelta){
        if(duration>=0){
           duration--;
           double alpha=3.137255*duration;
           RenderSystem.setShaderTexture(0, WHITE);
           DrawableHelper.drawTexture(matrixStack, 0, 0, 0, 0, client.getWindow().getWidth(), client.getWindow().getHeight(), 1920, 1080);//is this getting drawn offscreen?
           RenderSystem.setShaderColor(1, 1, 1, ((int)alpha));
        }
    }
    public void flash(){
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {effect(matrixStack, tickDelta);});
    }
}
