package net.braytonks.palpatch.datagen;

import net.braytonks.palpatch.block.BeamBlock;
import net.braytonks.palpatch.block.ModBlocks;
import net.braytonks.palpatch.block.OreScrapBlock;
import net.braytonks.palpatch.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Optional;

import static net.minecraft.data.client.ModelIds.getItemModelId;
import static net.minecraft.data.client.Models.GENERATED;
import static net.minecraft.data.client.TextureMap.layer0;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }


    // DEFINE BEAM MODEL TEMPLATES
    public static final Model TEMPLATE_BEAM_CENTER = new Model(Optional.of(Identifier.of("palpatch", "block/template_beam_center")), Optional.empty(), TextureKey.TEXTURE);

    public static final Model TEMPLATE_BEAM_NORTH = new Model(Optional.of(Identifier.of("palpatch", "block/template_beam_north")), Optional.empty(), TextureKey.TEXTURE);
    public static final Model TEMPLATE_BEAM_SOUTH = new Model(Optional.of(Identifier.of("palpatch", "block/template_beam_south")), Optional.empty(), TextureKey.TEXTURE);
    public static final Model TEMPLATE_BEAM_EAST = new Model(Optional.of(Identifier.of("palpatch", "block/template_beam_east")), Optional.empty(), TextureKey.TEXTURE);
    public static final Model TEMPLATE_BEAM_WEST = new Model(Optional.of(Identifier.of("palpatch", "block/template_beam_west")), Optional.empty(), TextureKey.TEXTURE);

    public static final Model TEMPLATE_BEAM_NE = new Model(Optional.of(Identifier.of("palpatch", "block/template_beam_ne")), Optional.empty(), TextureKey.TEXTURE);
    public static final Model TEMPLATE_BEAM_SE = new Model(Optional.of(Identifier.of("palpatch", "block/template_beam_se")), Optional.empty(), TextureKey.TEXTURE);
    public static final Model TEMPLATE_BEAM_SW = new Model(Optional.of(Identifier.of("palpatch", "block/template_beam_sw")), Optional.empty(), TextureKey.TEXTURE);
    public static final Model TEMPLATE_BEAM_NW = new Model(Optional.of(Identifier.of("palpatch", "block/template_beam_nw")), Optional.empty(), TextureKey.TEXTURE);

    public static final Model TEMPLATE_BEAM_INVENTORY = new Model(Optional.of(Identifier.of("palpatch", "block/template_beam_inventory")), Optional.empty(), TextureKey.TEXTURE);

    public void generateWoodBeams(BlockStateModelGenerator generator, String woodName, Block beamBlock) {
        TextureMap textures = new TextureMap()
                .put(TextureKey.TEXTURE, Identifier.of("palpatch", "block/" + woodName + "_beam"));

        // UPLOAD TEMPLATE IDs
        Identifier idCenter = TEMPLATE_BEAM_CENTER.upload(Identifier.of("palpatch", "block/" + woodName + "_beam_center"), textures, generator.modelCollector);
        Identifier idN  = TEMPLATE_BEAM_NORTH.upload(Identifier.of("palpatch", "block/" + woodName + "_beam_north"), textures, generator.modelCollector);
        Identifier idS  = TEMPLATE_BEAM_SOUTH.upload(Identifier.of("palpatch", "block/" + woodName + "_beam_south"), textures, generator.modelCollector);
        Identifier idE  = TEMPLATE_BEAM_EAST.upload(Identifier.of("palpatch", "block/" + woodName + "_beam_east"), textures, generator.modelCollector);
        Identifier idW  = TEMPLATE_BEAM_WEST.upload(Identifier.of("palpatch", "block/" + woodName + "_beam_west"), textures, generator.modelCollector);
        Identifier idNE = TEMPLATE_BEAM_NE.upload(Identifier.of("palpatch", "block/" + woodName + "_beam_ne"), textures, generator.modelCollector);
        Identifier idSE = TEMPLATE_BEAM_SE.upload(Identifier.of("palpatch", "block/" + woodName + "_beam_se"), textures, generator.modelCollector);
        Identifier idSW = TEMPLATE_BEAM_SW.upload(Identifier.of("palpatch", "block/" + woodName + "_beam_sw"), textures, generator.modelCollector);
        Identifier idNW = TEMPLATE_BEAM_NW.upload(Identifier.of("palpatch", "block/" + woodName + "_beam_nw"), textures, generator.modelCollector);

        Identifier idInventory = TEMPLATE_BEAM_INVENTORY.upload(Identifier.of("palpatch", "block/" + woodName + "_beam_inventory"), textures, generator.modelCollector);


        // GENERATE BEAM BLOCKSTATE FILES (the main reason I don't wanna do this all manually haha)
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(beamBlock)
                .coordinate(BlockStateVariantMap.create(Properties.AXIS, BeamBlock.HORIZ_POS, BeamBlock.VERT_POS)

                        // Z-AXIS (North-South)
                        .register(Direction.Axis.Z, BeamBlock.BeamPos.MIDDLE,   BeamBlock.BeamPos.MIDDLE,   variant(idCenter, null, null))
                        .register(Direction.Axis.Z, BeamBlock.BeamPos.MIDDLE,   BeamBlock.BeamPos.NEGATIVE, variant(idS, null, null))
                        .register(Direction.Axis.Z, BeamBlock.BeamPos.MIDDLE,   BeamBlock.BeamPos.POSITIVE, variant(idN, null, null))
                        .register(Direction.Axis.Z, BeamBlock.BeamPos.NEGATIVE, BeamBlock.BeamPos.MIDDLE,   variant(idE, null, null))
                        .register(Direction.Axis.Z, BeamBlock.BeamPos.POSITIVE, BeamBlock.BeamPos.MIDDLE,   variant(idW, null, null))
                        .register(Direction.Axis.Z, BeamBlock.BeamPos.POSITIVE, BeamBlock.BeamPos.NEGATIVE, variant(idSW, null, null))
                        .register(Direction.Axis.Z, BeamBlock.BeamPos.POSITIVE, BeamBlock.BeamPos.POSITIVE, variant(idNW, null, null))
                        .register(Direction.Axis.Z, BeamBlock.BeamPos.NEGATIVE, BeamBlock.BeamPos.POSITIVE, variant(idNE, null, null))
                        .register(Direction.Axis.Z, BeamBlock.BeamPos.NEGATIVE, BeamBlock.BeamPos.NEGATIVE, variant(idSE, null, null))

                        // Y-AXIS (Vertical)
                        .register(Direction.Axis.Y, BeamBlock.BeamPos.MIDDLE,   BeamBlock.BeamPos.MIDDLE,   variant(idCenter, VariantSettings.Rotation.R90, null))
                        .register(Direction.Axis.Y, BeamBlock.BeamPos.MIDDLE,   BeamBlock.BeamPos.NEGATIVE, variant(idN, VariantSettings.Rotation.R90, null))
                        .register(Direction.Axis.Y, BeamBlock.BeamPos.MIDDLE,   BeamBlock.BeamPos.POSITIVE, variant(idS, VariantSettings.Rotation.R90, null))
                        .register(Direction.Axis.Y, BeamBlock.BeamPos.NEGATIVE, BeamBlock.BeamPos.MIDDLE,   variant(idE, VariantSettings.Rotation.R90, null))
                        .register(Direction.Axis.Y, BeamBlock.BeamPos.POSITIVE, BeamBlock.BeamPos.MIDDLE,   variant(idW, VariantSettings.Rotation.R90, null))
                        .register(Direction.Axis.Y, BeamBlock.BeamPos.POSITIVE, BeamBlock.BeamPos.NEGATIVE, variant(idNW, VariantSettings.Rotation.R90, null))
                        .register(Direction.Axis.Y, BeamBlock.BeamPos.POSITIVE, BeamBlock.BeamPos.POSITIVE, variant(idSW, VariantSettings.Rotation.R90, null))
                        .register(Direction.Axis.Y, BeamBlock.BeamPos.NEGATIVE, BeamBlock.BeamPos.POSITIVE, variant(idSE, VariantSettings.Rotation.R90, null))
                        .register(Direction.Axis.Y, BeamBlock.BeamPos.NEGATIVE, BeamBlock.BeamPos.NEGATIVE, variant(idNE, VariantSettings.Rotation.R90, null))

                        // X-AXIS (East-West)
                        .register(Direction.Axis.X, BeamBlock.BeamPos.MIDDLE,   BeamBlock.BeamPos.MIDDLE,   variant(idCenter, null, VariantSettings.Rotation.R90))
                        .register(Direction.Axis.X, BeamBlock.BeamPos.MIDDLE,   BeamBlock.BeamPos.NEGATIVE, variant(idS, null, VariantSettings.Rotation.R90))
                        .register(Direction.Axis.X, BeamBlock.BeamPos.MIDDLE,   BeamBlock.BeamPos.POSITIVE, variant(idN, null, VariantSettings.Rotation.R90))
                        .register(Direction.Axis.X, BeamBlock.BeamPos.NEGATIVE, BeamBlock.BeamPos.MIDDLE,   variant(idE, null, VariantSettings.Rotation.R90))
                        .register(Direction.Axis.X, BeamBlock.BeamPos.POSITIVE, BeamBlock.BeamPos.MIDDLE,   variant(idW, null, VariantSettings.Rotation.R90))
                        .register(Direction.Axis.X, BeamBlock.BeamPos.POSITIVE, BeamBlock.BeamPos.NEGATIVE, variant(idSW, null, VariantSettings.Rotation.R90))
                        .register(Direction.Axis.X, BeamBlock.BeamPos.POSITIVE, BeamBlock.BeamPos.POSITIVE, variant(idNW, null, VariantSettings.Rotation.R90))
                        .register(Direction.Axis.X, BeamBlock.BeamPos.NEGATIVE, BeamBlock.BeamPos.POSITIVE, variant(idNE, null, VariantSettings.Rotation.R90))
                        .register(Direction.Axis.X, BeamBlock.BeamPos.NEGATIVE, BeamBlock.BeamPos.NEGATIVE, variant(idSE, null, VariantSettings.Rotation.R90))
                )
        );
    }

    // SCRAP TEMPLATE DEFINITIONS
    public static final Model TEMPLATE_SCRAP1 = new Model(Optional.of(Identifier.of("palpatch", "block/template_scrap1")), Optional.empty(), TextureKey.TEXTURE);
    public static final Model TEMPLATE_SCRAP2 = new Model(Optional.of(Identifier.of("palpatch", "block/template_scrap2")), Optional.empty(), TextureKey.TEXTURE);
    public static final Model TEMPLATE_SCRAP3 = new Model(Optional.of(Identifier.of("palpatch", "block/template_scrap3")), Optional.empty(), TextureKey.TEXTURE);
    public static final Model TEMPLATE_SCRAP4 = new Model(Optional.of(Identifier.of("palpatch", "block/template_scrap4")), Optional.empty(), TextureKey.TEXTURE);

    public void generateScrap(BlockStateModelGenerator generator, String oreName, Block scrapBlock) {
        TextureMap textures = new TextureMap()
                .put(TextureKey.TEXTURE, Identifier.of("minecraft", "block/raw_" + oreName + "_block"));

        // UPLOAD TEMPLATE IDs
        Identifier scrap1 = TEMPLATE_SCRAP1.upload(Identifier.of("palpatch", "block/" + oreName + "_scrap1"), textures, generator.modelCollector);
        Identifier scrap2 = TEMPLATE_SCRAP2.upload(Identifier.of("palpatch", "block/" + oreName + "_scrap2"), textures, generator.modelCollector);
        Identifier scrap3 = TEMPLATE_SCRAP3.upload(Identifier.of("palpatch", "block/" + oreName + "_scrap3"), textures, generator.modelCollector);
        Identifier scrap4 = TEMPLATE_SCRAP4.upload(Identifier.of("palpatch", "block/" + oreName + "_scrap4"), textures, generator.modelCollector);

        var variantMap = BlockStateVariantMap.create(OreScrapBlock.DIRECTION, OreScrapBlock.SCRAP_COUNT);

        registerDirectionGroup(variantMap, OreScrapBlock.ScrapDirection.NORTH, null, scrap1, scrap2, scrap3, scrap4);
        registerDirectionGroup(variantMap, OreScrapBlock.ScrapDirection.SOUTH, VariantSettings.Rotation.R90, scrap1, scrap2, scrap3, scrap4);
        registerDirectionGroup(variantMap, OreScrapBlock.ScrapDirection.EAST,  VariantSettings.Rotation.R180, scrap1, scrap2, scrap3, scrap4);
        registerDirectionGroup(variantMap, OreScrapBlock.ScrapDirection.WEST,  VariantSettings.Rotation.R270, scrap1, scrap2, scrap3, scrap4);

        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(scrapBlock).coordinate(variantMap));
    }

    private void registerDirectionGroup(BlockStateVariantMap.DoubleProperty<OreScrapBlock.ScrapDirection, Integer> map,
                                        OreScrapBlock.ScrapDirection dir,
                                        VariantSettings.Rotation yRot,
                                        Identifier... models) {
        for (int i = 0; i < models.length; i++) {
            // (Direction, Count, Variant)
            map.register(dir, i + 1, variant(models[i], null, yRot));
        }
    }

    private BlockStateVariant variant(Identifier modelId, VariantSettings.Rotation xRot, VariantSettings.Rotation yRot) {
        BlockStateVariant variant = BlockStateVariant.create().put(VariantSettings.MODEL, modelId);
        if (xRot != null) variant.put(VariantSettings.X, xRot);
        if (yRot != null) variant.put(VariantSettings.Y, yRot);
        return variant;
    }


    // This will be where we register all the blocks that we want Blockstates and models for
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerLog(ModBlocks.CINDERBARK_LOG)
                .log(ModBlocks.CINDERBARK_LOG).wood(ModBlocks.CINDERBARK_WOOD);

        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_CINDERBARK_LOG)
                .log(ModBlocks.STRIPPED_CINDERBARK_LOG).wood(ModBlocks.STRIPPED_CINDERBARK_WOOD);

        blockStateModelGenerator.registerAxisRotated(ModBlocks.SPICEWOOD_BLOCK, TexturedModel.END_FOR_TOP_CUBE_COLUMN,
                TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL);

        blockStateModelGenerator.registerAxisRotated(ModBlocks.STRIPPED_SPICEWOOD_BLOCK, TexturedModel.END_FOR_TOP_CUBE_COLUMN,
                TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL);

        blockStateModelGenerator.registerSingleton(ModBlocks.CINDERBARK_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerSingleton(ModBlocks.SPICEWOOD_LEAVES, TexturedModel.LEAVES);

        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.CINDERBARK_SAPLING,
                BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.SPICEWOOD_SAPLING,
                BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CINDERBARK_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SPICEWOOD_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.STRIPPED_SPICEWOOD_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SPICE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PACKED_SPICE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SPICE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FERROUS_DIRT);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COARSE_FERROUS_DIRT);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FERROUS_STONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FERROUS_COBBLESTONE);

        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.FERROUS_SHORT_GRASS,
                BlockStateModelGenerator.TintType.NOT_TINTED);

        generateWoodBeams(blockStateModelGenerator, "cinderbark", ModBlocks.CINDERBARK_BEAM);
        generateWoodBeams(blockStateModelGenerator, "stripped_cinderbark", ModBlocks.STRIPPED_CINDERBARK_BEAM);
        generateWoodBeams(blockStateModelGenerator, "spicewood", ModBlocks.SPICEWOOD_BEAM);
        generateWoodBeams(blockStateModelGenerator, "stripped_spicewood", ModBlocks.STRIPPED_SPICEWOOD_BEAM);

        generateWoodBeams(blockStateModelGenerator, "oak", ModBlocks.OAK_BEAM);
        generateWoodBeams(blockStateModelGenerator, "stripped_oak", ModBlocks.STRIPPED_OAK_BEAM);
        generateWoodBeams(blockStateModelGenerator, "spruce", ModBlocks.SPRUCE_BEAM);
        generateWoodBeams(blockStateModelGenerator, "stripped_spruce", ModBlocks.STRIPPED_SPRUCE_BEAM);
        generateWoodBeams(blockStateModelGenerator, "birch", ModBlocks.BIRCH_BEAM);
        generateWoodBeams(blockStateModelGenerator, "stripped_birch", ModBlocks.STRIPPED_BIRCH_BEAM);
        generateWoodBeams(blockStateModelGenerator, "jungle", ModBlocks.JUNGLE_BEAM);
        generateWoodBeams(blockStateModelGenerator, "stripped_jungle", ModBlocks.STRIPPED_JUNGLE_BEAM);
        generateWoodBeams(blockStateModelGenerator, "acacia", ModBlocks.ACACIA_BEAM);
        generateWoodBeams(blockStateModelGenerator, "stripped_acacia", ModBlocks.STRIPPED_ACACIA_BEAM);
        generateWoodBeams(blockStateModelGenerator, "dark_oak", ModBlocks.DARK_OAK_BEAM);
        generateWoodBeams(blockStateModelGenerator, "stripped_dark_oak", ModBlocks.STRIPPED_DARK_OAK_BEAM);
        generateWoodBeams(blockStateModelGenerator, "mangrove", ModBlocks.MANGROVE_BEAM);
        generateWoodBeams(blockStateModelGenerator, "stripped_mangrove", ModBlocks.STRIPPED_MANGROVE_BEAM);
        generateWoodBeams(blockStateModelGenerator, "cherry", ModBlocks.CHERRY_BEAM);
        generateWoodBeams(blockStateModelGenerator, "stripped_cherry", ModBlocks.STRIPPED_CHERRY_BEAM);
        generateWoodBeams(blockStateModelGenerator, "bamboo", ModBlocks.BAMBOO_BEAM);
        generateWoodBeams(blockStateModelGenerator, "stripped_bamboo", ModBlocks.STRIPPED_BAMBOO_BEAM);
        generateWoodBeams(blockStateModelGenerator, "crimson", ModBlocks.CRIMSON_BEAM);
        generateWoodBeams(blockStateModelGenerator, "stripped_crimson", ModBlocks.STRIPPED_CRIMSON_BEAM);
        generateWoodBeams(blockStateModelGenerator, "warped", ModBlocks.WARPED_BEAM);
        generateWoodBeams(blockStateModelGenerator, "stripped_warped", ModBlocks.STRIPPED_WARPED_BEAM);

       generateScrap(blockStateModelGenerator, "copper", ModBlocks.COPPER_SCRAP);
        generateScrap(blockStateModelGenerator, "gold", ModBlocks.GOLD_SCRAP);
    }

    // Creates Beam Item Model which points towards block/template_beam_inventory as its parent
    public void registerBeamItem(ItemModelGenerator generator, String woodName, Block beamBlock) {
        TextureMap textures = new TextureMap()
                .put(TextureKey.TEXTURE, Identifier.of("palpatch", "block/" + woodName + "_beam"));
        TEMPLATE_BEAM_INVENTORY.upload(ModelIds.getItemModelId(beamBlock.asItem()), textures, generator.writer);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.SPICEWOOD_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPICEWOOD_STICK, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.SOOTWEED.asItem(), Models.GENERATED);

        GENERATED.upload(getItemModelId(ModBlocks.CINDERBARK_SAPLING.asItem()), layer0(ModBlocks.CINDERBARK_SAPLING), itemModelGenerator.writer);

        GENERATED.upload(getItemModelId(ModBlocks.FERROUS_SHORT_GRASS.asItem()), layer0(ModBlocks.FERROUS_SHORT_GRASS), itemModelGenerator.writer);

        itemModelGenerator.register(ModBlocks.CINDERBARK_DOOR.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.SPICEWOOD_DOOR.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.STRIPPED_SPICEWOOD_DOOR.asItem(), Models.GENERATED);

        registerBeamItem(itemModelGenerator, "cinderbark", ModBlocks.CINDERBARK_BEAM);
        registerBeamItem(itemModelGenerator, "stripped_cinderbark", ModBlocks.STRIPPED_CINDERBARK_BEAM);
        registerBeamItem(itemModelGenerator, "spicewood", ModBlocks.SPICEWOOD_BEAM);
        registerBeamItem(itemModelGenerator, "stripped_spicewood", ModBlocks.STRIPPED_SPICEWOOD_BEAM);

        registerBeamItem(itemModelGenerator, "oak", ModBlocks.OAK_BEAM);
        registerBeamItem(itemModelGenerator, "stripped_oak", ModBlocks.STRIPPED_OAK_BEAM);
        registerBeamItem(itemModelGenerator, "spruce", ModBlocks.SPRUCE_BEAM);
        registerBeamItem(itemModelGenerator, "stripped_spruce", ModBlocks.STRIPPED_SPRUCE_BEAM);
        registerBeamItem(itemModelGenerator, "birch", ModBlocks.BIRCH_BEAM);
        registerBeamItem(itemModelGenerator, "stripped_birch", ModBlocks.STRIPPED_BIRCH_BEAM);
        registerBeamItem(itemModelGenerator, "jungle", ModBlocks.JUNGLE_BEAM);
        registerBeamItem(itemModelGenerator, "stripped_jungle", ModBlocks.STRIPPED_JUNGLE_BEAM);
        registerBeamItem(itemModelGenerator, "acacia", ModBlocks.ACACIA_BEAM);
        registerBeamItem(itemModelGenerator, "stripped_acacia", ModBlocks.STRIPPED_ACACIA_BEAM);
        registerBeamItem(itemModelGenerator, "dark_oak", ModBlocks.DARK_OAK_BEAM);
        registerBeamItem(itemModelGenerator, "stripped_dark_oak", ModBlocks.STRIPPED_DARK_OAK_BEAM);
        registerBeamItem(itemModelGenerator, "mangrove", ModBlocks.MANGROVE_BEAM);
        registerBeamItem(itemModelGenerator, "stripped_mangrove", ModBlocks.STRIPPED_MANGROVE_BEAM);
        registerBeamItem(itemModelGenerator, "cherry", ModBlocks.CHERRY_BEAM);
        registerBeamItem(itemModelGenerator, "stripped_cherry", ModBlocks.STRIPPED_CHERRY_BEAM);
        registerBeamItem(itemModelGenerator, "bamboo", ModBlocks.BAMBOO_BEAM);
        registerBeamItem(itemModelGenerator, "stripped_bamboo", ModBlocks.STRIPPED_BAMBOO_BEAM);
        registerBeamItem(itemModelGenerator, "crimson", ModBlocks.CRIMSON_BEAM);
        registerBeamItem(itemModelGenerator, "stripped_crimson", ModBlocks.STRIPPED_CRIMSON_BEAM);
        registerBeamItem(itemModelGenerator, "warped", ModBlocks.WARPED_BEAM);
        registerBeamItem(itemModelGenerator, "stripped_warped", ModBlocks.STRIPPED_WARPED_BEAM);

    }


}
