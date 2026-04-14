package net.braytonks.palpatch.datagen;

import net.braytonks.palpatch.block.ModBlocks;
import net.braytonks.palpatch.block.OreScrapBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        addDrop(ModBlocks.CINDERBARK_LOG);
        addDrop(ModBlocks.CINDERBARK_WOOD);
        addDrop(ModBlocks.STRIPPED_CINDERBARK_LOG);
        addDrop(ModBlocks.STRIPPED_CINDERBARK_WOOD);
        addDrop(ModBlocks.CINDERBARK_PLANKS);

        addDrop(ModBlocks.CINDERBARK_LEAVES, leavesDrops(ModBlocks.CINDERBARK_LEAVES, ModBlocks.CINDERBARK_SAPLING,.0625f));

        addDrop(ModBlocks.CINDERBARK_STAIRS);
        addDrop(ModBlocks.CINDERBARK_SLAB, slabDrops(ModBlocks.CINDERBARK_SLAB));

        addDrop(ModBlocks.CINDERBARK_BUTTON);
        addDrop(ModBlocks.CINDERBARK_PRESSURE_PLATE);

        addDrop(ModBlocks.CINDERBARK_FENCE);
        addDrop(ModBlocks.CINDERBARK_FENCE_GATE);

        addDrop(ModBlocks.CINDERBARK_DOOR, doorDrops(ModBlocks.CINDERBARK_DOOR));
        addDrop(ModBlocks.CINDERBARK_TRAPDOOR);

        addDrop(ModBlocks.SPICEWOOD_BLOCK);
        addDrop(ModBlocks.STRIPPED_SPICEWOOD_BLOCK);
        addDrop(ModBlocks.SPICEWOOD_PLANKS);
        addDrop(ModBlocks.SPICEWOOD_STAIRS);
        addDrop(ModBlocks.SPICEWOOD_SLAB, slabDrops(ModBlocks.SPICEWOOD_SLAB));

        addDrop(ModBlocks.SPICEWOOD_BUTTON);
        addDrop(ModBlocks.SPICEWOOD_PRESSURE_PLATE);

        addDrop(ModBlocks.SPICEWOOD_FENCE);
        addDrop(ModBlocks.SPICEWOOD_FENCE_GATE);

        addDrop(ModBlocks.SPICEWOOD_DOOR, doorDrops(ModBlocks.SPICEWOOD_DOOR));
        addDrop(ModBlocks.SPICEWOOD_TRAPDOOR);
        addDrop(ModBlocks.STRIPPED_SPICEWOOD_PLANKS);
        addDrop(ModBlocks.STRIPPED_SPICEWOOD_STAIRS);
        addDrop(ModBlocks.STRIPPED_SPICEWOOD_SLAB, slabDrops(ModBlocks.STRIPPED_SPICEWOOD_SLAB));

        addDrop(ModBlocks.STRIPPED_SPICEWOOD_BUTTON);
        addDrop(ModBlocks.STRIPPED_SPICEWOOD_PRESSURE_PLATE);

        addDrop(ModBlocks.STRIPPED_SPICEWOOD_FENCE);
        addDrop(ModBlocks.STRIPPED_SPICEWOOD_FENCE_GATE);

        addDrop(ModBlocks.STRIPPED_SPICEWOOD_DOOR, doorDrops(ModBlocks.STRIPPED_SPICEWOOD_DOOR));
        addDrop(ModBlocks.STRIPPED_SPICEWOOD_TRAPDOOR);
        addDrop(ModBlocks.SPICE_BLOCK);
        addDrop(ModBlocks.PACKED_SPICE);

        addDrop(ModBlocks.SPICE_BRICKS);
        addDrop(ModBlocks.SPICE_BRICK_WALL);

        addDrop(ModBlocks.FERROUS_DIRT);
        addDrop(ModBlocks.COARSE_FERROUS_DIRT);

        addDrop(ModBlocks.FERROUS_STONE);
        addDrop(ModBlocks.FERROUS_COBBLESTONE);

        this.addDrop(ModBlocks.IRON_SCRAP, block -> dropsWithCount(block, Items.RAW_IRON));;
    }

    public LootTable.Builder dropsWithCount(Block block, Item drop) {
        return LootTable.builder().pool(
                LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(ItemEntry.builder(drop)
                                .apply(List.of(1, 2, 3, 4), count ->
                                        SetCountLootFunction.builder(ConstantLootNumberProvider.create(count))
                                                .conditionally(BlockStatePropertyLootCondition.builder(block)
                                                        .properties(StatePredicate.Builder.create()
                                                                .exactMatch(OreScrapBlock.SCRAP_COUNT, count))))
                        )
        );
    }
}
