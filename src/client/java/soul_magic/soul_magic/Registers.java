package soul_magic.soul_magic;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class Registers {
    public static void register(){
		//soul bottle model predicates
        ModelPredicateProviderRegistry.register(Soul_magic.SOUL_BOTTLE_TIER1, new Identifier("fill"), (stack, world, entity, seed) -> {
			if(stack.hasNbt()){
				double fill=stack.getNbt().getDouble("fill");
				if(fill<14.28571429 && fill>0){
					return 0.1f;
				}
				if(fill>14.28571429 && fill<28.57142858){
					return 0.2f;
				}
				if(fill>28.57142858 && fill<42.85714278){
					return 0.3f;
				}
				if(fill>42.85714278 && fill<57.14285698){
					return 0.4f;
				}
				if(fill>57.14285698 && fill<71.42857118){
					return 0.5f;
				}
				if(fill>71.42857118 && fill<85.71428538){
					return 0.6f;
				}
				if(fill>85.71428538 && fill<100){
					return 0.7f;
				}
				if(fill==100){
					return 0.8f;
				}
				if(fill>100){
					return 0.9f;
				}
				if(fill>200){
					return 1.0f;
				}
			}
			return 0.0f;
		});
		ModelPredicateProviderRegistry.register(Soul_magic.SOUL_BOTTLE_TIER2, new Identifier("fill"), (stack, world, entity, seed) -> {
			if(stack.hasNbt()){
				double fill=stack.getNbt().getDouble("fill");
				if(fill<14.28571429 && fill>0){
					return 0.1f;
				}
				if(fill>14.28571429 && fill<28.57142858){
					return 0.2f;
				}
				if(fill>28.57142858 && fill<42.85714278){
					return 0.3f;
				}
				if(fill>42.85714278 && fill<57.14285698){
					return 0.4f;
				}
				if(fill>57.14285698 && fill<71.42857118){
					return 0.5f;
				}
				if(fill>71.42857118 && fill<85.71428538){
					return 0.6f;
				}
				if(fill>85.71428538 && fill<100){
					return 0.7f;
				}
				if(fill==100){
					return 0.8f;
				}
				if(fill>100){
					return 0.9f;
				}
				if(fill>200){
					return 1.0f;
				}
			}
			return 0.0f;
		});
		ModelPredicateProviderRegistry.register(Soul_magic.SOUL_BOTTLE_TIER3, new Identifier("fill"), (stack, world, entity, seed) -> {
			if(stack.hasNbt()){
				double fill=stack.getNbt().getDouble("fill");
				if(fill<14.28571429 && fill>0){
					return 0.1f;
				}
				if(fill>14.28571429 && fill<28.57142858){
					return 0.2f;
				}
				if(fill>28.57142858 && fill<42.85714278){
					return 0.3f;
				}
				if(fill>42.85714278 && fill<57.14285698){
					return 0.4f;
				}
				if(fill>57.14285698 && fill<71.42857118){
					return 0.5f;
				}
				if(fill>71.42857118 && fill<85.71428538){
					return 0.6f;
				}
				if(fill>85.71428538 && fill<100){
					return 0.7f;
				}
				if(fill==100){
					return 0.8f;
				}
				if(fill>100){
					return 0.9f;
				}
				if(fill>200){
					return 1.0f;
				}
			}
			return 0.0f;
		});
    }
}
