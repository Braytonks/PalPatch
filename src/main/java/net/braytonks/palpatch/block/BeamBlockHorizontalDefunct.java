package net.braytonks.palpatch.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BeamBlockHorizontalDefunct extends Block {

    // Declare Enum
    public static final EnumProperty<BeamType> BEAM_TYPE = EnumProperty.of("position", BeamType.class);
    public static final EnumProperty<Direction.Axis> AXIS = Properties.HORIZONTAL_AXIS;

    // Set Default States
    public BeamBlockHorizontalDefunct(Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(BEAM_TYPE, BeamType.MIDDLE)
                .with(AXIS, Direction.Axis.X));
    }

    // Enum Definition
    public enum BeamType implements StringIdentifiable {
        BOTTOM("bottom"),
        MIDDLE("middle"),
        TOP("top");

        private final String name;

        BeamType(String name) {
            this.name = name;
        }
        @Override
        public String asString() {
            return this.name;
        }
    }

    // Placement Logic, if ClickedState = BeamBlock.Class && "endcap" is clicked, copy Position BlockState to new
    // else calculateBeamType
    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction side = ctx.getSide();
        BlockState clickedState = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(side.getOpposite()));

        BeamType beam;
        Direction.Axis axis;

        if (clickedState.isOf(this) && side.getAxis() == clickedState.get(AXIS)) {
            beam = clickedState.get(BEAM_TYPE);
            axis = clickedState.get(AXIS);

        } else {
            beam = calculateBeamType(ctx, side);
            axis = side.getAxis().isHorizontal() ? side.getAxis() : ctx.getHorizontalPlayerFacing().getAxis();
        }

        return this.getDefaultState()
                .with(AXIS, axis)
                .with(BEAM_TYPE, beam);
    }

    // Custom helper for determining whether a beam's vertical position should be the floor, middle or ceiling based on which side of the block is clicked
    private static @NotNull BeamType calculateBeamType(ItemPlacementContext ctx, Direction side) {
        // Top or Bottom of Block
        if (side == Direction.UP) return BeamType.BOTTOM;
        if (side == Direction.DOWN) return BeamType.TOP;

        // Side of Block
        double hitY = ctx.getHitPos().y - (double) ctx.getBlockPos().getY();
        if (hitY < 0.25) return BeamType.BOTTOM;
        if (hitY > 0.75) return BeamType.TOP;

        return BeamType.MIDDLE;
    }

    private static final VoxelShape FLOOR_X_SHAPE = Block.createCuboidShape(0, 0, 4, 16, 8, 12);
    private static final VoxelShape FLOOR_Z_SHAPE = Block.createCuboidShape(4, 0, 0, 12, 8, 16);

    private static final VoxelShape MIDDLE_X_SHAPE = Block.createCuboidShape(0, 4, 4, 16, 12, 12);
    private static final VoxelShape MIDDLE_Z_SHAPE = Block.createCuboidShape(4, 4, 0, 12, 12, 16);

    private static final VoxelShape CEILING_X_SHAPE = Block.createCuboidShape(0, 8, 4, 16, 16, 12);
    private static final VoxelShape CEILING_Z_SHAPE = Block.createCuboidShape(4, 8, 0, 12, 16, 16);

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction.Axis axis = state.get(AXIS);
        return switch (state.get(BEAM_TYPE)) {
            case BOTTOM -> (axis == Direction.Axis.X) ? FLOOR_X_SHAPE : FLOOR_Z_SHAPE;
            case MIDDLE -> (axis == Direction.Axis.X) ? MIDDLE_X_SHAPE : MIDDLE_Z_SHAPE;
            case TOP -> (axis == Direction.Axis.X) ? CEILING_X_SHAPE : CEILING_Z_SHAPE;
        };
    }

    // Register BlockState Properties
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BEAM_TYPE);
        builder.add(AXIS);
    }

    @Override
    protected boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    protected int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return 0;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return switch (rotation) {
            case COUNTERCLOCKWISE_90, CLOCKWISE_90 ->
                    state.with(AXIS, state.get(AXIS) == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X);
            default -> state;
        };
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state;
    }
}
