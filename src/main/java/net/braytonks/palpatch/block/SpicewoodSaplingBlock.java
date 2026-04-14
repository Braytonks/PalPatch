package net.braytonks.palpatch.block;

import net.minecraft.block.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class SpicewoodSaplingBlock extends Block {
    // The "Bounding Box" - A small 4x12x4 area in the center of the block
    protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 12.0, 12.0);

    public SpicewoodSaplingBlock(Settings settings) {
        super(settings);
    }

    // --- SELECTION & COLLISION ---

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Returning an empty shape means entities can walk right through it!
        return VoxelShapes.empty();
    }

    // --- GROWTH LOGIC ---

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        // Only attempt to grow if there is light and space above
        if (world.getLightLevel(pos.up()) >= 9 && random.nextInt(3) == 0) {
            this.grow(world, pos);
        }
    }

    public void grow(ServerWorld world, BlockPos pos) {
        // Swap this sapling for the actual "Adult" Spicewood Stalk
        // We set STAGE to 0 here because it's the very first segment of the stalk
        world.setBlockState(pos, ModBlocks.SPICEWOOD_STALK.getDefaultState()
                .with(SpicewoodStalk.STAGE, 0), Block.NOTIFY_ALL);
    }

    // --- REQUIREMENTS & PHYSICS ---

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState floor = world.getBlockState(pos.down());
        return floor.isIn(BlockTags.DIRT) || floor.isIn(BlockTags.SAND);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
}