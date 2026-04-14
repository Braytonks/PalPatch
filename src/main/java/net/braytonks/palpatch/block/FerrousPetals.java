package net.braytonks.palpatch.block;

import net.braytonks.palpatch.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerbedBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class FerrousPetals extends FlowerbedBlock {
    public FerrousPetals(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(ModTags.Blocks.FERROUS_PLANTABLE_ON) || floor.isIn(BlockTags.DIRT);
    }

}
