package net.braytonks.palpatch.item;

import net.braytonks.palpatch.PalPatch;
import net.braytonks.palpatch.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

// Add Items Here + Below to Item Groups
public class ModItems {
//    public static final Item SPICEWOOD_STICK = registerItem("spicewood_stick",
//            new AliasedBlockItem(ModBlocks.CAULIFLOWER_CROP, new Item.Settings()));

    public static final Item SPICEWOOD_STICK = registerItem("spicewood_stick", new AliasedBlockItem(ModBlocks.SPICEWOOD_SAPLING, new Item.Settings()));

    public static final Item SPICEWOOD_POWDER = registerItem("spicewood_powder", new Item(new Item.Settings()));

//    public static final Item CAULIFLOWER_SEEDS = registerItem("cauliflower_seeds",
//            new AliasedBlockItem(ModBlocks.CAULIFLOWER_CROP, new Item.Settings()));

//    public static final Item CAULIFLOWER = registerItem("cauliflower", new Item(new Item.Settings().food(ModFoodComponents.CAULIFLOWER)) {
//        @Override
//        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
//            tooltip.add(Text.translatable("tooltip.tutorialmod.cauliflower.tooltip"));
//            super.appendTooltip(stack, context, tooltip, type);
//        }
//    });


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(PalPatch.MOD_ID, name), item);
    }

    public static void registerModItems() {
        PalPatch.LOGGER.info("Registering Mod Items for " + PalPatch.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(SPICEWOOD_STICK);
            entries.add(SPICEWOOD_POWDER);
        });
    }
}
