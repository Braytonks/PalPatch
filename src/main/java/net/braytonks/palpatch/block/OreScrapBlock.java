package net.braytonks.palpatch.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

public class OreScrapBlock extends Block {
    public static final IntProperty SCRAP_COUNT = IntProperty.of("count", 1,4);
    public static final EnumProperty <ScrapDirection> DIRECTION = EnumProperty.of("direction", ScrapDirection.class);

    public OreScrapBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(SCRAP_COUNT, 1)
                .with(DIRECTION, ScrapDirection.NORTH));
    }

    public enum ScrapDirection implements StringIdentifiable {
        NORTH("north"),
        SOUTH("south"),
        EAST("east"),
        WEST("west");

        private final String name;
        ScrapDirection(String name) { this.name = name; }
        @Override public String asString() { return this.name; }}

        @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SCRAP_COUNT, DIRECTION);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return sideCoversSmallSquare(world, pos.down(), Direction.UP);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        if (direction == Direction.DOWN && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());

        // If there's already a matching scrap block here, increment the count
        if (blockState.isOf(this)) {
            return blockState.with(SCRAP_COUNT, Math.min(4, blockState.get(SCRAP_COUNT) + 1));}

        Direction playerFacing = ctx.getHorizontalPlayerFacing();

        ScrapDirection scrapDir = switch (playerFacing) {
            case SOUTH -> ScrapDirection.SOUTH;
            case EAST -> ScrapDirection.EAST;
            case WEST -> ScrapDirection.WEST;
            default -> ScrapDirection.NORTH;
        };

        return this.getDefaultState().with(DIRECTION, scrapDir);}

    private static final VoxelShape SHAPE_1 = Block.createCuboidShape(6, 0, 6, 10, 2, 10);
    private static final VoxelShape SHAPE_2 = Block.createCuboidShape(5, 0, 5, 11, 3, 11);
    private static final VoxelShape SHAPE_3 = Block.createCuboidShape(4, 0, 4, 12, 4, 12);
    private static final VoxelShape SHAPE_4 = Block.createCuboidShape(3, 0, 3, 13, 5, 13);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(SCRAP_COUNT)) {
            case 2 -> SHAPE_2;
            case 3 -> SHAPE_3;
            case 4 -> SHAPE_4;
            default -> SHAPE_1;
        };
    }

}
