package net.braytonks.palpatch.block;

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



    // CINDERBARK BLOCKS

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
    public static final Block CINDERBARK_STAIRS = registerBlock("cinderbark_stairs",
            new StairsBlock(ModBlocks.CINDERBARK_PLANKS.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.OAK_STAIRS)));
    public static final Block CINDERBARK_SLAB = registerBlock("cinderbark_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB)));
    public static final Block CINDERBARK_BUTTON = registerBlock("cinderbark_button",
            new ButtonBlock(BlockSetType.OAK, 15,
                    AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)));
    public static final Block CINDERBARK_PRESSURE_PLATE = registerBlock("cinderbark_pressure_plate",
            new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE)));
    public static final Block CINDERBARK_FENCE = registerBlock("cinderbark_fence",
            new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE)));
    public static final Block CINDERBARK_FENCE_GATE = registerBlock("cinderbark_fence_gate",
            new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE)));
    public static final Block CINDERBARK_DOOR = registerBlock("cinderbark_door",
            new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_DOOR)));
    public static final Block CINDERBARK_TRAPDOOR = registerBlock("cinderbark_trapdoor",
            new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR)));

    // CINDERBARK NATURE BLOCKS
    public static final Block CINDERBARK_SAPLING = registerBlock("cinderbark_sapling",
            new SaplingBlock(SaplingGenerator.OAK,AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));

    public static final Block CINDERBARK_LEAVES = registerBlock("cinderbark_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));

    public static final Block SOOTWEED = registerBlock("sootweed",
            new SootweedBlock(AbstractBlock.Settings.create()
                    .nonOpaque()
                    .ticksRandomly()
                    .replaceable()
                    .noCollision()
                    .strength(0.2F)
                    .sounds(BlockSoundGroup.VINE)
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)));


    // SPICEWOOD BLOCKS

    public static final Block SPICEWOOD_BLOCK = registerBlock("spicewood_block",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.BAMBOO_BLOCK)));
    public static final Block STRIPPED_SPICEWOOD_BLOCK = registerBlock("stripped_spicewood_block",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_BAMBOO_BLOCK)));
    public static final Block SPICEWOOD_PLANKS = registerBlock("spicewood_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.BAMBOO_PLANKS)));
    public static final Block SPICEWOOD_STAIRS = registerBlock("spicewood_stairs",
            new StairsBlock(ModBlocks.SPICEWOOD_PLANKS.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.OAK_STAIRS)));
    public static final Block SPICEWOOD_SLAB = registerBlock("spicewood_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB)));
    public static final Block SPICEWOOD_BUTTON = registerBlock("spicewood_button",
            new ButtonBlock(BlockSetType.OAK, 15,
                    AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)));
    public static final Block SPICEWOOD_PRESSURE_PLATE = registerBlock("spicewood_pressure_plate",
            new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE)));
    public static final Block SPICEWOOD_FENCE = registerBlock("spicewood_fence",
            new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE)));
    public static final Block SPICEWOOD_FENCE_GATE = registerBlock("spicewood_fence_gate",
            new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE)));
    public static final Block SPICEWOOD_DOOR = registerBlock("spicewood_door",
            new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_DOOR)));
    public static final Block SPICEWOOD_TRAPDOOR = registerBlock("spicewood_trapdoor",
            new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR)));
    public static final Block STRIPPED_SPICEWOOD_PLANKS = registerBlock("stripped_spicewood_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.BAMBOO_PLANKS)));
    public static final Block STRIPPED_SPICEWOOD_STAIRS = registerBlock("stripped_spicewood_stairs",
            new StairsBlock(ModBlocks.STRIPPED_SPICEWOOD_PLANKS.getDefaultState(),
                    AbstractBlock.Settings.copy(Blocks.OAK_STAIRS)));
    public static final Block STRIPPED_SPICEWOOD_SLAB = registerBlock("stripped_spicewood_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB)));
    public static final Block STRIPPED_SPICEWOOD_BUTTON = registerBlock("stripped_spicewood_button",
            new ButtonBlock(BlockSetType.OAK, 15,
                    AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)));
    public static final Block STRIPPED_SPICEWOOD_PRESSURE_PLATE = registerBlock("stripped_spicewood_pressure_plate",
            new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE)));
    public static final Block STRIPPED_SPICEWOOD_FENCE = registerBlock("stripped_spicewood_fence",
            new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE)));
    public static final Block STRIPPED_SPICEWOOD_FENCE_GATE = registerBlock("stripped_spicewood_fence_gate",
            new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE)));
    public static final Block STRIPPED_SPICEWOOD_DOOR = registerBlock("stripped_spicewood_door",
            new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_DOOR)));
    public static final Block STRIPPED_SPICEWOOD_TRAPDOOR = registerBlock("stripped_spicewood_trapdoor",
            new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR)));


    // SPICEWOOD NATURE BLOCKS

    public static final Block SPICEWOOD_SAPLING = registerItemlessBlock("spicewood_sapling",
            new SpicewoodSaplingBlock(AbstractBlock.Settings.copy(Blocks.BAMBOO_SAPLING)));
    public static final Block SPICEWOOD_STALK = registerItemlessBlock("spicewood",
            new SpicewoodStalk(AbstractBlock.Settings.copy(Blocks.BAMBOO)));
    public static final Block SPICEWOOD_LEAVES = registerBlock("spicewood_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));


    // SPICE POWDER BLOCKS

    public static final Block SPICE_BLOCK = registerBlock("spice_block",
            new Block(AbstractBlock.Settings.copy(Blocks.MOSS_BLOCK)));
    public static final Block PACKED_SPICE = registerBlock("packed_spice",
            new Block(AbstractBlock.Settings.copy(Blocks.PACKED_MUD)));
    public static final Block SPICE_BRICKS = registerBlock("spice_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));
    public static final Block SPICE_BRICK_WALL = registerBlock("spice_brick_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.MUD_BRICK_WALL)));


    // MODDED WOOD BEAMS

    public static final Block SPICEWOOD_BEAM = registerBlock("spicewood_beam",
            new BeamBlock(AbstractBlock.Settings.create()));
    public static final Block STRIPPED_SPICEWOOD_BEAM = registerBlock("stripped_spicewood_beam",
            new BeamBlock(AbstractBlock.Settings.create()));
    public static final Block CINDERBARK_BEAM = registerBlock("cinderbark_beam",
            new BeamBlock(AbstractBlock.Settings.create()));
    public static final Block STRIPPED_CINDERBARK_BEAM = registerBlock("stripped_cinderbark_beam",
            new BeamBlock(AbstractBlock.Settings.create()));

    // VANILLA WOOD BEAMS

    public static final Block OAK_BEAM = registerBlock("oak_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block SPRUCE_BEAM = registerBlock("spruce_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_LOG)));
    public static final Block BIRCH_BEAM = registerBlock("birch_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.BIRCH_LOG)));
    public static final Block JUNGLE_BEAM = registerBlock("jungle_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.JUNGLE_LOG)));
    public static final Block ACACIA_BEAM = registerBlock("acacia_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_LOG)));
    public static final Block DARK_OAK_BEAM = registerBlock("dark_oak_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.DARK_OAK_LOG)));
    public static final Block MANGROVE_BEAM = registerBlock("mangrove_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_LOG)));
    public static final Block CHERRY_BEAM = registerBlock("cherry_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_LOG)));
    public static final Block BAMBOO_BEAM = registerBlock("bamboo_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.BAMBOO_BLOCK)));
    public static final Block CRIMSON_BEAM = registerBlock("crimson_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.CRIMSON_STEM)));
    public static final Block WARPED_BEAM = registerBlock("warped_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.WARPED_STEM)));

    public static final Block STRIPPED_OAK_BEAM = registerBlock("stripped_oak_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block STRIPPED_SPRUCE_BEAM = registerBlock("stripped_spruce_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_LOG)));
    public static final Block STRIPPED_BIRCH_BEAM = registerBlock("stripped_birch_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.BIRCH_LOG)));
    public static final Block STRIPPED_JUNGLE_BEAM = registerBlock("stripped_jungle_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.JUNGLE_LOG)));
    public static final Block STRIPPED_ACACIA_BEAM = registerBlock("stripped_acacia_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_LOG)));
    public static final Block STRIPPED_DARK_OAK_BEAM = registerBlock("stripped_dark_oak_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.DARK_OAK_LOG)));
    public static final Block STRIPPED_MANGROVE_BEAM = registerBlock("stripped_mangrove_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_LOG)));
    public static final Block STRIPPED_CHERRY_BEAM = registerBlock("stripped_cherry_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_LOG)));
    public static final Block STRIPPED_BAMBOO_BEAM = registerBlock("stripped_bamboo_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.BAMBOO_BLOCK)));
    public static final Block STRIPPED_CRIMSON_BEAM = registerBlock("stripped_crimson_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.CRIMSON_STEM)));
    public static final Block STRIPPED_WARPED_BEAM = registerBlock("stripped_warped_beam",
            new BeamBlock(AbstractBlock.Settings.copy(Blocks.WARPED_STEM)));

    // VANILLA ORES SCRAP BLOCKS

    public static final Block IRON_SCRAP = registerItemlessBlock("iron_scrap",
    new OreScrapBlock(AbstractBlock.Settings.create()
            .noCollision().nonOpaque().strength(0.5f).sounds(BlockSoundGroup.METAL).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block COPPER_SCRAP = registerItemlessBlock("copper_scrap",
            new OreScrapBlock(AbstractBlock.Settings.create()
                    .noCollision().nonOpaque().strength(0.5f).sounds(BlockSoundGroup.COPPER).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block GOLD_SCRAP = registerItemlessBlock("gold_scrap",
            new OreScrapBlock(AbstractBlock.Settings.create()
                    .noCollision().nonOpaque().strength(0.5f).sounds(BlockSoundGroup.NETHER_GOLD_ORE).pistonBehavior(PistonBehavior.DESTROY)));

    // FERROUS BLOCKS

    public static final Block FERROUS_DIRT = registerBlock("ferrous_dirt",
            new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));
    public static final Block COARSE_FERROUS_DIRT = registerBlock("coarse_ferrous_dirt",
            new Block(AbstractBlock.Settings.copy(Blocks.COARSE_DIRT)));
    public static final Block FERROUS_GRASS_BLOCK = registerBlock("ferrous_grass_block",
            new FerrousGrassBlock(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK)));
    public static final Block FERROUS_SHORT_GRASS = registerBlock("ferrous_short_grass",
            new FerrousShortGrass(AbstractBlock.Settings.copy(Blocks.SHORT_GRASS)));
    public static final Block FERROUS_PETALS = registerBlock("ferrous_petals",
            new FerrousPetals(AbstractBlock.Settings.copy(Blocks.PINK_PETALS)));
    public static final Block FERROUS_STONE = registerBlock("ferrous_stone",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE)));
    public static final Block FERROUS_COBBLESTONE = registerBlock("ferrous_cobblestone",
            new Block(AbstractBlock.Settings.copy(Blocks.COBBLESTONE)));


    // TUTORIAL LEFTOVERS

    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            new TutorialMagicBlock(AbstractBlock.Settings.create()
                    .strength(1f)
                    .requiresTool()));

    // Register Block - Creates Block + Block Item

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(PalPatch.MOD_ID, name), block);
    }

    // Register Itemless Blocks - things like Spicewood Saplings which are planted by a stick of Spicewood similar to Vanilla's Bamboo Sapling

    private static Block registerItemlessBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(PalPatch.MOD_ID, name), block);
    }

    // Helper called by registerBlock

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(PalPatch.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }



    public static void registerModBlocks() {

        // Sending info to LOGGER to keep the log posted on what the mod is doing
        PalPatch.LOGGER.info("Registering Mod Blocks for " + PalPatch.MOD_ID);

        // Add items to Vanilla ItemGroups, to add to ModItemGroups (Custom Item Groups) go to palpatch.item.ModItemGroups.class

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.addAfter(Blocks.WARPED_BUTTON,
                    ModBlocks.CINDERBARK_LOG,
                    ModBlocks.CINDERBARK_WOOD, ModBlocks.STRIPPED_CINDERBARK_LOG,
                    ModBlocks.STRIPPED_CINDERBARK_WOOD, ModBlocks.CINDERBARK_PLANKS,
                    ModBlocks.CINDERBARK_STAIRS, ModBlocks.CINDERBARK_SLAB,
                    ModBlocks.CINDERBARK_FENCE, ModBlocks.CINDERBARK_FENCE_GATE,
                    ModBlocks.CINDERBARK_DOOR, ModBlocks.CINDERBARK_TRAPDOOR,
                    ModBlocks.CINDERBARK_PRESSURE_PLATE, ModBlocks.CINDERBARK_BUTTON,
                    ModBlocks.SPICEWOOD_BLOCK, ModBlocks.SPICEWOOD_PLANKS,
                    ModBlocks.SPICEWOOD_STAIRS, ModBlocks.SPICEWOOD_SLAB,
                    ModBlocks.SPICEWOOD_FENCE, ModBlocks.SPICEWOOD_FENCE_GATE,
                    ModBlocks.SPICEWOOD_DOOR, ModBlocks.SPICEWOOD_TRAPDOOR,
                    ModBlocks.SPICEWOOD_PRESSURE_PLATE, ModBlocks.SPICEWOOD_BUTTON,
                    ModBlocks.STRIPPED_SPICEWOOD_BLOCK, ModBlocks.STRIPPED_SPICEWOOD_PLANKS,
                    ModBlocks.STRIPPED_SPICEWOOD_STAIRS, ModBlocks.STRIPPED_SPICEWOOD_SLAB,
                    ModBlocks.STRIPPED_SPICEWOOD_FENCE, ModBlocks.STRIPPED_SPICEWOOD_FENCE_GATE,
                    ModBlocks.STRIPPED_SPICEWOOD_DOOR, ModBlocks.STRIPPED_SPICEWOOD_TRAPDOOR,
                    ModBlocks.STRIPPED_SPICEWOOD_PRESSURE_PLATE, ModBlocks.STRIPPED_SPICEWOOD_BUTTON);

            entries.addAfter(Blocks.POLISHED_ANDESITE_SLAB, ModBlocks.FERROUS_STONE,
                    ModBlocks.FERROUS_COBBLESTONE);

            entries.addAfter(Blocks.MUD_BRICK_WALL,ModBlocks.SPICE_BLOCK,
                    ModBlocks.PACKED_SPICE,
                    ModBlocks.SPICE_BRICKS,
                    ModBlocks.SPICE_BRICK_WALL);

            entries.addBefore(Blocks.OAK_FENCE,ModBlocks.OAK_BEAM, ModBlocks.STRIPPED_OAK_BEAM);
            entries.addBefore(Blocks.SPRUCE_FENCE,ModBlocks.SPRUCE_BEAM, ModBlocks.STRIPPED_SPRUCE_BEAM);
            entries.addBefore(Blocks.BIRCH_FENCE,ModBlocks.BIRCH_BEAM, ModBlocks.STRIPPED_BIRCH_BEAM);
            entries.addBefore(Blocks.JUNGLE_FENCE,ModBlocks.JUNGLE_BEAM, ModBlocks.STRIPPED_JUNGLE_BEAM);
            entries.addBefore(Blocks.ACACIA_FENCE,ModBlocks.ACACIA_BEAM, ModBlocks.STRIPPED_ACACIA_BEAM);
            entries.addBefore(Blocks.DARK_OAK_FENCE,ModBlocks.DARK_OAK_BEAM, ModBlocks.STRIPPED_DARK_OAK_BEAM);
            entries.addBefore(Blocks.MANGROVE_FENCE,ModBlocks.MANGROVE_BEAM, ModBlocks.STRIPPED_MANGROVE_BEAM);
            entries.addBefore(Blocks.CHERRY_FENCE,ModBlocks.CHERRY_BEAM, ModBlocks.STRIPPED_CHERRY_BEAM);
            entries.addBefore(Blocks.BAMBOO_FENCE,ModBlocks.BAMBOO_BEAM, ModBlocks.STRIPPED_BAMBOO_BEAM);
            entries.addBefore(Blocks.CRIMSON_FENCE,ModBlocks.CRIMSON_BEAM, ModBlocks.STRIPPED_CRIMSON_BEAM);
            entries.addBefore(Blocks.WARPED_FENCE,ModBlocks.WARPED_BEAM, ModBlocks.STRIPPED_WARPED_BEAM);
        });

         ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Blocks.CHERRY_SAPLING,ModBlocks.CINDERBARK_SAPLING);
             entries.addAfter(Blocks.CHERRY_LEAVES,ModBlocks.CINDERBARK_LEAVES,
             ModBlocks.SPICEWOOD_LEAVES);
             entries.addAfter(Blocks.FARMLAND,ModBlocks.FERROUS_GRASS_BLOCK,
                     ModBlocks.FERROUS_DIRT, ModBlocks.COARSE_FERROUS_DIRT);

             entries.addAfter(Blocks.SHORT_GRASS,ModBlocks.FERROUS_SHORT_GRASS);
             entries.addBefore(Blocks.PINK_PETALS,ModBlocks.FERROUS_PETALS);

             entries.addAfter(Blocks.VINE,ModBlocks.SOOTWEED);
            });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> {
            entries.add(ModBlocks.MAGIC_BLOCK);
            });
    }
}
