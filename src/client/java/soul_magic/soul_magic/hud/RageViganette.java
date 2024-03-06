package soul_magic.soul_magic.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;
import soul_magic.soul_magic.Soul_magic;

public class RageViganette{
    public boolean ShouldRender;
    private static final Identifier VIGANETTE = new Identifier("soul_magic", "textures/hud/rage_viganette.png");
    private void viganette(MatrixStack matrixStack, float tickDelta){
        if(ShouldRender){
            MinecraftClient client = MinecraftClient.getInstance();
            RenderSystem.setShaderColor(1, 1, 1, 1);
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderTexture(0, VIGANETTE);
            DrawableHelper.drawTexture(matrixStack, 0, 0, 0, 0, client.getWindow().getWidth(), client.getWindow().getHeight());//is this getting drawn offscreen?
        }
    }
    public void setRendering(boolean shouldRender){
        this.ShouldRender=shouldRender;
        if(shouldRender==true && ShouldRender==false){
            HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {viganette(matrixStack, tickDelta);});
        }
    }
    public static void renderCheck(){
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;
        
		if (player != null){
            double health=player.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH);
            if(player.hasStatusEffect(Soul_magic.RAGE) && health<=health/2){
                RageViganette rageViganette = new RageViganette();
                System.out.println("hi");
                rageViganette.setRendering(true);
        	}
        }
    }
}
