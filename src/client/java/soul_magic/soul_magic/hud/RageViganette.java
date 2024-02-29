package soul_magic.soul_magic.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class RageViganette{
    private static final Identifier VIGANETTE = new Identifier("soul_magic", "textures/hud/rage_viganette");
    public void rageViganette(){
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {viganette(matrixStack, tickDelta);});
    }
    private void viganette(MatrixStack matrixStack, float tickDelta){
       MinecraftClient client = MinecraftClient.getInstance();
       client.getTextureManager().bindTexture(VIGANETTE);
        DrawableHelper.drawTexture(matrixStack, 0, 0, 10, 10, client.getWindow().getWidth(), client.getWindow().getHeight());
    }
}
