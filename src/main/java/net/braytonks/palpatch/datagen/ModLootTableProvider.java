//package net.braytonks.palpatch.datagen;
//
//import net.braytonks.palpatch.block.ModBlocks;
//import net.braytonks.palpatch.block.TutorialCauliflowerCropBlock;
//import net.braytonks.palpatch.item.ModItems;
//import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
//import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
//import net.minecraft.loot.LootTable;
//import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
//import net.minecraft.predicate.StatePredicate;
//import net.minecraft.registry.RegistryWrapper;
//
//import java.util.concurrent.CompletableFuture;
//
//public class ModLootTableProvider extends FabricBlockLootTableProvider {
//    protected ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
//        super(dataOutput, registryLookup);
//    }
//
//    @Override
//    public void generate() {
//        addDrop(ModBlocks.CINDERBARK_LOG);
//        addDrop(ModBlocks.CINDERBARK_WOOD);
//        addDrop(ModBlocks.STRIPPED_CINDERBARK_LOG);
//        addDrop(ModBlocks.STRIPPED_CINDERBARK_WOOD);
//        addDrop(ModBlocks.CINDERBARK_PLANKS);
//
//        addDrop(ModBlocks.SPICEWOOD_BLOCK);
//        addDrop(ModBlocks.STRIPPED_SPICEWOOD_BLOCK);
//        addDrop(ModBlocks.SPICEWOOD_PLANKS);
//        addDrop(ModBlocks.STRIPPED_SPICEWOOD_PLANKS);
//        addDrop(ModBlocks.SPICE_BLOCK);
//        addDrop(ModBlocks.PACKED_SPICE);
//        addDrop(ModBlocks.PACKED_SPICE_BRICKS);
//
//        BlockStatePropertyLootCondition.Builder builder2 = BlockStatePropertyLootCondition.builder(ModBlocks.CAULIFLOWER_CROP)
//                .properties(StatePredicate.Builder.create().exactMatch(TutorialCauliflowerCropBlock.AGE, TutorialCauliflowerCropBlock.MAX_AGE));
//        this.addDrop(ModBlocks.CAULIFLOWER_CROP, this.cropDrops(ModBlocks.CAULIFLOWER_CROP, ModItems.CAULIFLOWER, ModItems.CAULIFLOWER_SEEDS, builder2));
//    }
//}
