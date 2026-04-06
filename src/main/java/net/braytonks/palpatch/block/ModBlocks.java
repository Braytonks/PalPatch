package net.braytonks.palpatch.block;

import com.mojang.serialization.MapCodec;
import net.braytonks.palpatch.PalPatch;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;


public class ModBlocks {

    // Add Blocks Here + Below to Item Groups

    public static final Block CINDERBARK_LOG = registerBlock("cinderbark_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block CINDERBARK_WOOD = registerBlock("cinderbark_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_CINDERBARK_LOG = registerBlock("stripped_cinderbark_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final Block STRIPPED_CINDERBARK_WOOD = registerBlock("stripped_cinderbark_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block CINDERBARK_PLANKS = registerBlock("cinderbark_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block CINDERBARK_SAPLING = registerBlock("cinderbark_sapling",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
    public static final Block SOOTWEED = registerBlock("sootweed",
            new SootweedBlock(AbstractBlock.Settings.copy(Blocks.VINE)));

    public static final Block SPICEWOOD_BLOCK = registerBlock("spicewood_block",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.BAMBOO_BLOCK)));
    public static final Block STRIPPED_SPICEWOOD_BLOCK = registerBlock("stripped_spicewood_block",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_BAMBOO_BLOCK)));

//    public static final Block SPICEWOOD_SAPLING = registerBlock(
//            "spicewood_sapling",
//            new SpicewoodShootBlock(
//                    AbstractBlock.Settings.create()
//                            .mapColor(MapColor.ORANGE)
//                            .solid()
//                            .ticksRandomly()
//                            .breakInstantly()
//                            .noCollision()
//                            .strength(1.0F)
//                            .sounds(BlockSoundGroup.BAMBOO_SAPLING)
//                            .offset(AbstractBlock.OffsetType.XZ)
//                            .burnable()
//                            .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block SPICEWOOD_PLANKS = registerBlock("spicewood_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.BAMBOO_PLANKS)));
    public static final Block STRIPPED_SPICEWOOD_PLANKS = registerBlock("stripped_spicewood_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.BAMBOO_PLANKS)));

    public static final Block SPICE_BLOCK = registerBlock("spice_block",
            new Block(AbstractBlock.Settings.copy(Blocks.MOSS_BLOCK)));
    public static final Block PACKED_SPICE = registerBlock("packed_spice",
            new Block(AbstractBlock.Settings.copy(Blocks.PACKED_MUD)));
    public static final Block SPICE_BRICKS = registerBlock("spice_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));

    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            new TutorialMagicBlock(AbstractBlock.Settings.create()
                    .strength(1f)
                    .requiresTool()));

    public static final Block CINDERBARK_STAIRS = registerBlock("cinderbark_stairs",
            new StairsBlock(ModBlocks.CINDERBARK_PLANKS.getDefaultState(),
                    AbstractBlock.Settings.create().strength(2f).requiresTool()));

    public static final Block CINDERBARK_SLAB = registerBlock("cinderbark_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(2f).requiresTool()));

    public static final Block CINDERBARK_BUTTON = registerBlock("cinderbark_button",
            new ButtonBlock(BlockSetType.OAK, 2,
                    AbstractBlock.Settings.create().strength(2f).requiresTool().noCollision()));

    public static final Block CINDERBARK_PRESSURE_PLATE = registerBlock("cinderbark_pressure_plate",
            new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.create().strength(2f).requiresTool()));

    public static final Block CINDERBARK_FENCE = registerBlock("cinderbark_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(2f).requiresTool()));

    public static final Block SPICEWOOD_SAPLING = registerSapling("spicewood_sapling",
            new SpicewoodSaplingBlock(AbstractBlock.Settings.copy(Blocks.BAMBOO_SAPLING)));

//    public static final Block CAULIFLOWER_CROP = registerItemlessBlock("cauliflower_crop",
//            new TutorialCauliflowerCropBlock(AbstractBlock.Settings.create()
//                    .mapColor(MapColor.OFF_WHITE)
//                    .noCollision()
//                    .ticksRandomly()
//                    .breakInstantly()
//                    .sounds(BlockSoundGroup.CROP)
//                    .pistonBehavior(PistonBehavior.DESTROY)
//            ));



    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(PalPatch.MOD_ID, name), block);
    }

    private static Block registerItemlessBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(PalPatch.MOD_ID, name), block);
    }

    private static Block registerSapling(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(PalPatch.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(PalPatch.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        PalPatch.LOGGER.info("Registering Mod Blocks for " + PalPatch.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.CINDERBARK_LOG);
            entries.add(ModBlocks.CINDERBARK_WOOD);
            entries.add(ModBlocks.STRIPPED_CINDERBARK_LOG);
            entries.add(ModBlocks.STRIPPED_CINDERBARK_WOOD);
            entries.add(ModBlocks.CINDERBARK_PLANKS);
            entries.add(ModBlocks.SOOTWEED);

            entries.add(ModBlocks.SPICEWOOD_BLOCK);
            entries.add(ModBlocks.STRIPPED_SPICEWOOD_BLOCK);
            entries.add(ModBlocks.SPICEWOOD_PLANKS);
            entries.add(ModBlocks.STRIPPED_SPICEWOOD_PLANKS);
            entries.add(ModBlocks.SPICE_BLOCK);
            entries.add(ModBlocks.PACKED_SPICE);
            entries.add(ModBlocks.SPICE_BRICKS);
        });

         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(ModBlocks.CINDERBARK_SAPLING);
            });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> {
            entries.add(ModBlocks.MAGIC_BLOCK);
            });
    }
}
