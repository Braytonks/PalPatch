package net.braytonks.palpatch;

import net.braytonks.palpatch.block.ModBlocks;
import net.braytonks.palpatch.item.ModItemGroups;
import net.braytonks.palpatch.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PalPatch implements ModInitializer {
	public static final String MOD_ID = "palpatch";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}