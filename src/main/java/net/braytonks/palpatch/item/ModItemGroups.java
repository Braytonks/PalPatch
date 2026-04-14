package net.braytonks.palpatch.item;

import net.braytonks.palpatch.PalPatch;
import net.braytonks.palpatch.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup PALETTE_PATCHER_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(PalPatch.MOD_ID, "palette_patcher_items_group"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModBlocks.FERROUS_GRASS_BLOCK))
                        .displayName(Text.translatable("itemgroup.palpatch.palette_patcher_items"))
                        .entries((displayContext, entries) -> {
                            entries.add(ModBlocks.CINDERBARK_LOG);
                            entries.add(ModBlocks.CINDERBARK_WOOD);
                            entries.add(ModBlocks.STRIPPED_CINDERBARK_LOG);
                            entries.add(ModBlocks.STRIPPED_CINDERBARK_WOOD);
                            entries.add(ModBlocks.CINDERBARK_PLANKS);
                            entries.add(ModBlocks.CINDERBARK_STAIRS);
                            entries.add(ModBlocks.CINDERBARK_SLAB);
                            entries.add(ModBlocks.CINDERBARK_FENCE);
                            entries.add(ModBlocks.CINDERBARK_FENCE_GATE);
                            entries.add(ModBlocks.CINDERBARK_BEAM);
                            entries.add(ModBlocks.STRIPPED_CINDERBARK_BEAM);
                            entries.add(ModBlocks.CINDERBARK_DOOR);
                            entries.add(ModBlocks.CINDERBARK_TRAPDOOR);
                            entries.add(ModBlocks.CINDERBARK_PRESSURE_PLATE);
                            entries.add(ModBlocks.CINDERBARK_BUTTON);
                            entries.add(ModBlocks.CINDERBARK_LEAVES);
                            entries.add(ModBlocks.CINDERBARK_SAPLING);

                            entries.add(ModBlocks.SOOTWEED);

                            entries.add(ModBlocks.SPICEWOOD_BLOCK);
                            entries.add(ModBlocks.SPICEWOOD_PLANKS);
                            entries.add(ModBlocks.SPICEWOOD_STAIRS);
                            entries.add(ModBlocks.SPICEWOOD_SLAB);
                            entries.add(ModBlocks.SPICEWOOD_FENCE);
                            entries.add(ModBlocks.SPICEWOOD_FENCE_GATE);
                            entries.add(ModBlocks.SPICEWOOD_BEAM);
                            entries.add(ModBlocks.STRIPPED_SPICEWOOD_BEAM);
                            entries.add(ModBlocks.SPICEWOOD_DOOR);
                            entries.add(ModBlocks.SPICEWOOD_TRAPDOOR);
                            entries.add(ModBlocks.SPICEWOOD_PRESSURE_PLATE);
                            entries.add(ModBlocks.SPICEWOOD_BUTTON);
                            
                            entries.add(ModBlocks.STRIPPED_SPICEWOOD_BLOCK);
                            entries.add(ModBlocks.STRIPPED_SPICEWOOD_PLANKS);
                            entries.add(ModBlocks.STRIPPED_SPICEWOOD_STAIRS);
                            entries.add(ModBlocks.STRIPPED_SPICEWOOD_SLAB);
                            entries.add(ModBlocks.STRIPPED_SPICEWOOD_FENCE);
                            entries.add(ModBlocks.STRIPPED_SPICEWOOD_FENCE_GATE);
                            entries.add(ModBlocks.STRIPPED_SPICEWOOD_DOOR);
                            entries.add(ModBlocks.STRIPPED_SPICEWOOD_TRAPDOOR);
                            entries.add(ModBlocks.STRIPPED_SPICEWOOD_PRESSURE_PLATE);
                            entries.add(ModBlocks.STRIPPED_SPICEWOOD_BUTTON);
                            
                            entries.add(ModBlocks.SPICEWOOD_LEAVES);
                            entries.add(ModBlocks.SPICE_BLOCK);
                            entries.add(ModBlocks.PACKED_SPICE);
                            entries.add(ModBlocks.SPICE_BRICKS);
                            entries.add(ModBlocks.SPICE_BRICK_WALL);

                            entries.add(ModItems.SPICEWOOD_STICK);
                            entries.add(ModItems.SPICEWOOD_POWDER);

                            entries.add(ModBlocks.FERROUS_GRASS_BLOCK);
                            entries.add(ModBlocks.FERROUS_DIRT);
                            entries.add(ModBlocks.COARSE_FERROUS_DIRT);
                            entries.add(ModBlocks.FERROUS_SHORT_GRASS);
                            entries.add(ModBlocks.FERROUS_PETALS);
                            entries.add(ModBlocks.FERROUS_STONE);
                            entries.add(ModBlocks.FERROUS_COBBLESTONE);

                            entries.add(ModBlocks.OAK_BEAM);
                            entries.add(ModBlocks.STRIPPED_OAK_BEAM);
                            entries.add(ModBlocks.SPRUCE_BEAM);
                            entries.add(ModBlocks.STRIPPED_SPRUCE_BEAM);
                            entries.add(ModBlocks.BIRCH_BEAM);
                            entries.add(ModBlocks.STRIPPED_BIRCH_BEAM);
                            entries.add(ModBlocks.JUNGLE_BEAM);
                            entries.add(ModBlocks.STRIPPED_JUNGLE_BEAM);
                            entries.add(ModBlocks.ACACIA_BEAM);
                            entries.add(ModBlocks.STRIPPED_ACACIA_BEAM);
                            entries.add(ModBlocks.DARK_OAK_BEAM);
                            entries.add(ModBlocks.STRIPPED_DARK_OAK_BEAM);
                            entries.add(ModBlocks.MANGROVE_BEAM);
                            entries.add(ModBlocks.STRIPPED_MANGROVE_BEAM);
                            entries.add(ModBlocks.CHERRY_BEAM);
                            entries.add(ModBlocks.STRIPPED_CHERRY_BEAM);
                            entries.add(ModBlocks.BAMBOO_BEAM);
                            entries.add(ModBlocks.STRIPPED_BAMBOO_BEAM);
                            entries.add(ModBlocks.CRIMSON_BEAM);
                            entries.add(ModBlocks.STRIPPED_CRIMSON_BEAM);
                            entries.add(ModBlocks.WARPED_BEAM);
                            entries.add(ModBlocks.STRIPPED_WARPED_BEAM);

                        }).build());

    public static void registerItemGroups() {
        PalPatch.LOGGER.info("Registering Item Groups for " + PalPatch.MOD_ID);
    }
}
