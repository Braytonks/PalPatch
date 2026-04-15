package net.braytonks.palpatch.item;

import net.braytonks.palpatch.PalPatch;
import net.braytonks.palpatch.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.BiFunction;
import java.util.function.Function;

// Add Items Here + Below to Item Groups
public class ModItems {
//    public static final Item SPICEWOOD_STICK = registerItem("spicewood_stick",
//            new AliasedBlockItem(ModBlocks.CAULIFLOWER_CROP, new Item.Settings()));

    public static final Item SPICEWOOD_STICK = registerItem("spicewood_stick",
            BlockItem::new,
            ModBlocks.SPICEWOOD_SAPLING
    );

    public static final Item SPICEWOOD_POWDER = registerItem("spicewood_powder", Item::new);

//    public static final Item CAULIFLOWER_SEEDS = registerItem("cauliflower_seeds",
//            new BlockItem(ModBlocks.CAULIFLOWER_CROP, new Item.Settings()));

//    public static final Item CAULIFLOWER = registerItem("cauliflower", new Item(new Item.Settings().food(ModFoodComponents.CAULIFLOWER)) {
//        @Override
//        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
//            tooltip.add(Text.translatable("tooltip.tutorialmod.cauliflower.tooltip"));
//            super.appendTooltip(stack, context, tooltip, type);
//        }
//    });


    private static Item registerItem(String name, BiFunction<Block, Item.Settings, Item> factory, Block block) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PalPatch.MOD_ID, name));
        return Registry.register(Registries.ITEM, itemKey, factory.apply(block, new Item.Settings().registryKey(itemKey)));
    }

    private static Item registerItem(String name, Function<Item.Settings, Item> factory) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PalPatch.MOD_ID, name));
        return Registry.register(Registries.ITEM, itemKey, factory.apply(new Item.Settings().registryKey(itemKey)));
    }

    public static void registerModItems() {
        PalPatch.LOGGER.info("Registering Mod Items for " + PalPatch.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(SPICEWOOD_STICK);
            entries.add(SPICEWOOD_POWDER);
        });
    }
}
