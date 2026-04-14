package net.braytonks.palpatch;

import net.braytonks.palpatch.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class PalPatchClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerCutouts();
    }

    private void registerCutouts() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                ModBlocks.SOOTWEED,
                ModBlocks.SPICEWOOD_STALK,
                ModBlocks.SPICEWOOD_SAPLING,
                ModBlocks.CINDERBARK_LEAVES,
                ModBlocks.SPICEWOOD_LEAVES,
                ModBlocks.CINDERBARK_SAPLING,
                ModBlocks.CINDERBARK_DOOR,
                ModBlocks.CINDERBARK_TRAPDOOR,
                ModBlocks.SPICEWOOD_DOOR,
                ModBlocks.STRIPPED_SPICEWOOD_DOOR,
                ModBlocks.SPICEWOOD_TRAPDOOR,
                ModBlocks.STRIPPED_SPICEWOOD_TRAPDOOR,
                ModBlocks.FERROUS_SHORT_GRASS,
                ModBlocks.FERROUS_PETALS
        );
    }
}
