package ancient_arcana.arcana;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.util.Identifier;

public class Ancient_arcanaClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(Ancient_arcana.SPELL_PROJECTILE, FlyingItemEntityRenderer::new);
		EntityRendererRegistry.register(Ancient_arcana.SOUL_BOTTLE_PROJECTILE, FlyingItemEntityRenderer::new);
		ModelPredicateProviderRegistry.register(Ancient_arcana.SOUL_BOTTLE, new Identifier("fill"), (stack, world, entity, seed) -> {
			if(stack.hasNbt()){
				double fill=stack.getNbt().getDouble("fill");
				if(fill<25){
					return 1.0f;
				}
				if(fill>25 && fill<50){
					return 2.0f;
				}
				if(fill>50 && fill<75){
					return 3.0f;
				}
				if(fill>75 && fill<100){
					return 4.0f;
				}
				if(fill==100){
					return 5.0f;
				}
				if(fill>100){
					return 6.0f;
				}
			}
			return 0.0F;
		});
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
}