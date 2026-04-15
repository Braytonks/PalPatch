package net.braytonks.palpatch;

import net.braytonks.palpatch.block.ModBlocks;
import net.braytonks.palpatch.block.OreScrapBlock;
import net.braytonks.palpatch.item.ModItemGroups;
import net.braytonks.palpatch.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PalPatch implements ModInitializer {
	public static final String MOD_ID = "palpatch";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {

		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();}}



//		// Logic for Scrap Blocks placed by vanilla raw ore items (no mixin)
////		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
////			ItemStack stack = player.getStackInHand(hand);
////
////			// Check held item for raw iron, gold or copper
////			net.minecraft.block.Block targetScrap = null;
////			if (stack.isOf(Items.RAW_IRON)) targetScrap = ModBlocks.IRON_SCRAP;
////			else if (stack.isOf(Items.RAW_GOLD)) targetScrap = ModBlocks.GOLD_SCRAP;
////			else if (stack.isOf(Items.RAW_COPPER)) targetScrap = ModBlocks.COPPER_SCRAP;
////
////			if (targetScrap == null || hitResult.getSide() != Direction.UP) {
////				return ActionResult.PASS;
////			}
////
////			BlockPos clickedPos = hitResult.getBlockPos();
////			BlockState clickedState = world.getBlockState(clickedPos);
////
////			if (clickedState.isOf(targetScrap)) {
////				int currentCount = clickedState.get(OreScrapBlock.SCRAP_COUNT);
////				if (currentCount < 4) {
////					if (!world.isClient) {
////						world.setBlockState(clickedPos, clickedState.with(OreScrapBlock.SCRAP_COUNT, currentCount + 1));
////						if (!player.getAbilities().creativeMode) stack.decrement(1);
////					}
////					return ActionResult.SUCCESS;
////				}
////			}
//
//			BlockPos placePos = clickedPos.up();
//			if (world.getBlockState(placePos).isAir() && clickedState.isSideSolidFullSquare(world, clickedPos, Direction.UP)) {
//				if (!world.isClient) {
//					// Places the specific scrap block identified in step 1
//					world.setBlockState(placePos, targetScrap.getDefaultState());
//					world.playSound(null, clickedPos, net.minecraft.sound.SoundEvents.BLOCK_GRAVEL_PLACE, SoundCategory.BLOCKS, 0.5f, 1.2f);
//					if (!player.getAbilities().creativeMode) stack.decrement(1);
//				}
//				return ActionResult.SUCCESS;
//			}
//
//			return ActionResult.PASS;
//		});
//	}
//}