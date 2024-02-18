package soul_magic.soul_magic;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.util.Identifier;

public class Soul_magicClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(Soul_magic.SPELL_PROJECTILE, FlyingItemEntityRenderer::new);
		/*ModelPredicateProviderRegistry.register(Soul_magic.SOUL_BOTTLE_TIER3, new Identifier("fill"), (stack, world, entity, seed) -> {
			if(stack.hasNbt()){
				double fill=stack.getNbt().getDouble("fill");
				System.out.println("fill="+fill);
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
		});*/
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
}