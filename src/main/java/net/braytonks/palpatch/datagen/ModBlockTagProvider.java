package net.braytonks.palpatch.datagen;

import net.braytonks.palpatch.block.ModBlocks;
import net.braytonks.palpatch.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        // Add to existing Vanilla Block Tags

        getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(ModBlocks.CINDERBARK_PLANKS)
                .add(ModBlocks.SPICEWOOD_PLANKS)
                .add(ModBlocks.STRIPPED_SPICEWOOD_PLANKS);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .addTag(ModTags.Blocks.CINDERBARK_LOGS)
                .addTag(ModTags.Blocks.SPICEWOOD_BLOCKS);

        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.CINDERBARK_STAIRS)
                .add(ModBlocks.SPICEWOOD_STAIRS)
                .add(ModBlocks.STRIPPED_SPICEWOOD_STAIRS);

        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.CINDERBARK_SLAB)
                .add(ModBlocks.SPICEWOOD_SLAB)
                .add(ModBlocks.STRIPPED_SPICEWOOD_SLAB);

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.CINDERBARK_FENCE)
                .add(ModBlocks.SPICEWOOD_FENCE)
                .add(ModBlocks.STRIPPED_SPICEWOOD_FENCE);

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.CINDERBARK_FENCE_GATE)
                .add(ModBlocks.SPICEWOOD_FENCE_GATE)
                .add(ModBlocks.STRIPPED_SPICEWOOD_FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.SPICE_BRICK_WALL);

        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.CINDERBARK_DOOR)
                .add(ModBlocks.SPICEWOOD_DOOR)
                .add(ModBlocks.STRIPPED_SPICEWOOD_DOOR);

        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.CINDERBARK_TRAPDOOR)
                .add(ModBlocks.SPICEWOOD_TRAPDOOR)
                .add(ModBlocks.STRIPPED_SPICEWOOD_TRAPDOOR);

        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.CINDERBARK_PRESSURE_PLATE)
                .add(ModBlocks.SPICEWOOD_PRESSURE_PLATE)
                .add(ModBlocks.STRIPPED_SPICEWOOD_PRESSURE_PLATE);

        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.CINDERBARK_BUTTON)
                .add(ModBlocks.SPICEWOOD_BUTTON)
                .add(ModBlocks.STRIPPED_SPICEWOOD_BUTTON);

        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.CINDERBARK_LEAVES)
                .add(ModBlocks.SPICEWOOD_LEAVES);

        getOrCreateTagBuilder(BlockTags.DIRT)
                .add(ModBlocks.FERROUS_GRASS_BLOCK)
                .add(ModBlocks.FERROUS_DIRT)
                .add(ModBlocks.COARSE_FERROUS_DIRT)
                .add(ModBlocks.FERROUS_STONE)
                .add(ModBlocks.FERROUS_COBBLESTONE);

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.FERROUS_GRASS_BLOCK)
                .add(ModBlocks.FERROUS_DIRT)
                .add(ModBlocks.COARSE_FERROUS_DIRT);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.SPICE_BRICKS)
                .add(ModBlocks.SPICE_BRICK_WALL)
                .add(ModBlocks.PACKED_SPICE)
                .add(ModBlocks.FERROUS_STONE)
                .add(ModBlocks.FERROUS_COBBLESTONE)
                .add(ModBlocks.IRON_SCRAP);

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .addTag(ModTags.Blocks.CINDERBARK_LOGS)
                .addTag(ModTags.Blocks.SPICEWOOD_BLOCKS)
                .add(ModBlocks.CINDERBARK_PLANKS).add(ModBlocks.SPICEWOOD_PLANKS).add(ModBlocks.STRIPPED_SPICEWOOD_PLANKS)
                .add(ModBlocks.CINDERBARK_STAIRS).add(ModBlocks.SPICEWOOD_STAIRS).add(ModBlocks.STRIPPED_SPICEWOOD_STAIRS)
                .add(ModBlocks.CINDERBARK_SLAB).add(ModBlocks.SPICEWOOD_SLAB).add(ModBlocks.STRIPPED_SPICEWOOD_SLAB)
                .add(ModBlocks.CINDERBARK_FENCE).add(ModBlocks.SPICEWOOD_FENCE).add(ModBlocks.STRIPPED_SPICEWOOD_FENCE)
                .add(ModBlocks.CINDERBARK_FENCE_GATE).add(ModBlocks.SPICEWOOD_FENCE_GATE).add(ModBlocks.STRIPPED_SPICEWOOD_FENCE_GATE)
                .add(ModBlocks.OAK_BEAM).add(ModBlocks.SPRUCE_BEAM).add(ModBlocks.BIRCH_BEAM)
                .add(ModBlocks.JUNGLE_BEAM).add(ModBlocks.ACACIA_BEAM).add(ModBlocks.DARK_OAK_BEAM)
                .add(ModBlocks.MANGROVE_BEAM).add(ModBlocks.CHERRY_BEAM).add(ModBlocks.BAMBOO_BEAM)
                .add(ModBlocks.CRIMSON_BEAM).add(ModBlocks.WARPED_BEAM)
                .add(ModBlocks.CINDERBARK_BEAM).add(ModBlocks.STRIPPED_CINDERBARK_BEAM)
                .add(ModBlocks.SPICEWOOD_BEAM).add(ModBlocks.STRIPPED_SPICEWOOD_BEAM);


        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                .add(ModBlocks.CINDERBARK_LEAVES)
                .add(ModBlocks.SPICEWOOD_LEAVES);

        getOrCreateTagBuilder(BlockTags.REPLACEABLE)
                .add(ModBlocks.FERROUS_SHORT_GRASS)
                .add(ModBlocks.FERROUS_PETALS);

        getOrCreateTagBuilder(BlockTags.SAPLINGS);

        getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS);

        // Add to Mod Block Tags from util.ModTags

        getOrCreateTagBuilder(ModTags.Blocks.CINDERBARK_LOGS)
                .add(ModBlocks.CINDERBARK_LOG)
                .add(ModBlocks.STRIPPED_CINDERBARK_LOG)
                .add(ModBlocks.CINDERBARK_WOOD)
                .add(ModBlocks.STRIPPED_CINDERBARK_WOOD);

        getOrCreateTagBuilder(ModTags.Blocks.SPICEWOOD_BLOCKS)
                .add(ModBlocks.SPICEWOOD_BLOCK)
                .add(ModBlocks.STRIPPED_SPICEWOOD_BLOCK);
    }
}
