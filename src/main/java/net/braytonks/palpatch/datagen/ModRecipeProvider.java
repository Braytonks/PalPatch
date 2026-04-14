package net.braytonks.palpatch.datagen;

import net.braytonks.palpatch.block.ModBlocks;
import net.braytonks.palpatch.item.ModItems;
import net.braytonks.palpatch.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    public static void build2x2Recipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input, int count) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, output, count)
                .pattern("##")
                .pattern("##")
                .input('#', input)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        List<ItemConvertible> TEST = List.of(ModItems.SPICEWOOD_STICK, ModItems.SPICEWOOD_POWDER);

//        offerSmelting(exporter, TEST, RecipeCategory.MISC, ModBlocks.SOOTWEED, .25f, 200, "test");
//        offerBlasting(exporter, TEST, RecipeCategory.MISC, ModBlocks.SOOTWEED, .25f, 100, "test");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.SPICEWOOD_STICK, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPICEWOOD_BLOCK);
        offerShapelessRecipe(exporter, ModItems.SPICEWOOD_STICK, ModBlocks.STRIPPED_SPICEWOOD_BLOCK,
                "spicewood_stick", 9);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,ModBlocks.CINDERBARK_WOOD, 3)
                .pattern("LL")
                .pattern("LL")
                .input('L', ModBlocks.CINDERBARK_LOG)
                .criterion(hasItem(ModBlocks.CINDERBARK_LOG), conditionsFromItem(ModBlocks.CINDERBARK_LOG))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,ModBlocks.STRIPPED_CINDERBARK_WOOD, 3)
                .pattern("LL")
                .pattern("LL")
                .input('L', ModBlocks.STRIPPED_CINDERBARK_LOG)
                .criterion(hasItem(ModBlocks.STRIPPED_CINDERBARK_LOG), conditionsFromItem(ModBlocks.STRIPPED_CINDERBARK_LOG))
                .offerTo(exporter);

        build2x2Recipe(exporter, ModBlocks.PACKED_SPICE, ModBlocks.SPICE_BLOCK, 4);
        build2x2Recipe(exporter, ModBlocks.SPICE_BRICKS, ModBlocks.PACKED_SPICE, 4);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CINDERBARK_PLANKS, 4)
                .input(ModTags.Items.CINDERBARK_LOGS)
                .criterion("has_cinderbark_log", conditionsFromTag(ModTags.Items.CINDERBARK_LOGS))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPICEWOOD_PLANKS, 2)
                .input(ModBlocks.SPICEWOOD_BLOCK)
                .criterion(hasItem(ModBlocks.SPICEWOOD_BLOCK), conditionsFromItem(ModBlocks.SPICEWOOD_BLOCK))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRIPPED_SPICEWOOD_PLANKS, 2)
                .input(ModBlocks.STRIPPED_SPICEWOOD_BLOCK)
                .criterion(hasItem(ModBlocks.STRIPPED_SPICEWOOD_BLOCK), conditionsFromItem(ModBlocks.STRIPPED_SPICEWOOD_BLOCK))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SPICEWOOD_POWDER, 1)
                .input(ModItems.SPICEWOOD_STICK)
                .criterion(hasItem(ModItems.SPICEWOOD_STICK), conditionsFromItem(ModItems.SPICEWOOD_STICK))
                .offerTo(exporter);

    }
}
