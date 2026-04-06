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
                    .icon(() -> new ItemStack(ModBlocks.CINDERBARK_LOG))
                        .displayName(Text.translatable("itemgroup.palpatch.palette_patcher_items"))
                        .entries((displayContext, entries) -> {
                            entries.add(ModBlocks.CINDERBARK_LOG);
                            entries.add(ModBlocks.CINDERBARK_WOOD);
                            entries.add(ModBlocks.STRIPPED_CINDERBARK_LOG);
                            entries.add(ModBlocks.STRIPPED_CINDERBARK_WOOD);
                            entries.add(ModBlocks.CINDERBARK_PLANKS);

                            entries.add(ModBlocks.SPICEWOOD_BLOCK);
                            entries.add(ModBlocks.STRIPPED_SPICEWOOD_BLOCK);
                            entries.add(ModBlocks.SPICEWOOD_PLANKS);
                            entries.add(ModBlocks.STRIPPED_SPICEWOOD_PLANKS);
                            entries.add(ModBlocks.SPICE_BLOCK);
                            entries.add(ModBlocks.PACKED_SPICE);
                            entries.add(ModBlocks.SPICE_BRICKS);

                            entries.add(ModItems.SPICEWOOD_STICK);
                            entries.add(ModItems.SPICEWOOD_POWDER);

                        }).build());

    public static void registerItemGroups() {
        PalPatch.LOGGER.info("Registering Item Groups for " + PalPatch.MOD_ID);
    }
}
