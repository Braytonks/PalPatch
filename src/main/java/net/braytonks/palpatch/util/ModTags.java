package net.braytonks.palpatch.util;

import net.braytonks.palpatch.PalPatch;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> CINDERBARK_LOGS = createTag("cinderbark_logs");
        public static final TagKey<Block> SPICEWOOD_BLOCKS = createTag("spicewood_blocks");
        public static final TagKey<Block> FERROUS_PLANTABLE_ON = createTag("ferrous_plantable_on");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(PalPatch.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
        public static final TagKey<Item> CINDERBARK_LOGS = createTag("cinderbark_logs");
        public static final TagKey<Item> SPICEWOOD_BLOCKS = createTag("spicewood_blocks");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(PalPatch.MOD_ID, name));
        }
    }
}
