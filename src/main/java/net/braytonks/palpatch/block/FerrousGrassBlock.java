package net.braytonks.palpatch.block;

import net.braytonks.palpatch.util.ModTags;
import net.minecraft.block.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;

public class FerrousGrassBlock extends GrassBlock {
    public FerrousGrassBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!canSurvive(state, world, pos)) {
            world.setBlockState(pos, ModBlocks.FERROUS_DIRT.getDefaultState());
        } else {
            if (world.getLightLevel(pos.up()) >= 9) {
                for (int i = 0; i < 4; ++i) {
                    BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    // Check if the target is your custom Ferrous Dirt
                    if (world.getBlockState(blockPos).isOf(ModBlocks.FERROUS_DIRT) && canSpread(getDefaultState(), world, blockPos)) {
                        world.setBlockState(blockPos, this.getDefaultState());
                    }
                }
            }
        }
    }

    private static boolean canSurvive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        return blockState.getOpacity(world, blockPos) < world.getMaxLightLevel();
    }

    private static boolean canSpread(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        return canSurvive(state, world, pos) && !world.getFluidState(blockPos).isIn(net.minecraft.registry.tag.FluidTags.WATER);
    }

    // --- BONEMEAL LOGIC ---

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockPos blockPos = pos.up();
        BlockState blockState = ModBlocks.FERROUS_SHORT_GRASS.getDefaultState();
        outer:
        for (int i = 0; i < 128; ++i) {
            BlockPos targetPos = blockPos;

            for (int j = 0; j < i / 16; ++j) {
                targetPos = targetPos.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                // Ensure we stay on top of a Ferrous Grass block

                BlockState floor = world.getBlockState(targetPos.down());

                if (!(floor.isIn(ModTags.Blocks.FERROUS_PLANTABLE_ON) || floor.isIn(BlockTags.DIRT))
                        || world.getBlockState(targetPos).isFullCube(world, targetPos)) {
                    continue outer;
                }
            }
            BlockState targetState = world.getBlockState(targetPos);
            if (targetState.isAir()) {
                int roll = random.nextInt(10);

                if (roll < 4) {
                    world.setBlockState(targetPos, ModBlocks.FERROUS_SHORT_GRASS.getDefaultState(), Block.NOTIFY_LISTENERS);
                }
                else if (roll < 7) {
                    BlockState randomPetalState = ModBlocks.FERROUS_PETALS.getDefaultState()
                            .with(FlowerbedBlock.FACING, Direction.Type.HORIZONTAL.random(random))
                            .with(FlowerbedBlock.FLOWER_AMOUNT, random.nextInt(3) + 1);

                    world.setBlockState(targetPos, randomPetalState, Block.NOTIFY_LISTENERS);
                }
            }
        }
    }
}