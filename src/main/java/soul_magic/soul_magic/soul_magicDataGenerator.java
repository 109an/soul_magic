package soul_magic.soul_magic;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class Soul_magicDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModelGenerator::new);
		
	}
	private static class ModelGenerator extends FabricModelProvider {
		private ModelGenerator(FabricDataOutput generator) {
			super(generator);
		}
	 
		@Override
		public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
			// ...
		}
	 
		@Override
		public void generateItemModels(ItemModelGenerator itemModelGenerator) {
			itemModelGenerator.register(Soul_magic.SOUL_BOTTLE_TIER1, Models.GENERATED);
		}
	}
}
