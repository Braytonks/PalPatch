package net.braytonks.palpatch;

import net.braytonks.palpatch.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class PalPatchDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
	FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		ModBlockTagProvider blockTagProvider = pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider((output, registries) -> new ModItemTagProvider(output, registries, blockTagProvider));
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	}
}
