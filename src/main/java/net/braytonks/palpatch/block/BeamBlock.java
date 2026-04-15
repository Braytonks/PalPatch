package net.braytonks.palpatch.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class BeamBlock extends Block {

    public static final EnumProperty<Direction.Axis> AXIS = Properties.AXIS;
    public static final EnumProperty<BeamPos> VERT_POS = EnumProperty.of("vert", BeamPos.class);
    public static final EnumProperty<BeamPos> HORIZ_POS = EnumProperty.of("horiz", BeamPos.class);

    public BeamBlock(Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState(this.getDefaultState()
                .with(AXIS, Direction.Axis.Y)
                .with(VERT_POS, BeamPos.MIDDLE)
                .with(HORIZ_POS, BeamPos.MIDDLE));
    }

    public enum BeamPos implements StringIdentifiable {
        NEGATIVE("negative"), // Bottom, North, or West
        MIDDLE("middle"),
        POSITIVE("positive"); // Top, South, or East

        private final String name;
        BeamPos(String name) { this.name = name; }
        @Override public String asString() { return this.name; }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        PlayerEntity player = ctx.getPlayer();
        Direction side = ctx.getSide();
        Direction.Axis faceAxis = side.getAxis();

        BlockPos clickedPos = ctx.getBlockPos().offset(side.getOpposite());
        BlockState clickedState = ctx.getWorld().getBlockState(clickedPos);

        BeamPos vPos = calculatePos(ctx, faceAxis, true);
        BeamPos hPos = calculatePos(ctx, faceAxis, false);
        Direction.Axis axis;

        if (clickedState.getBlock() instanceof BeamBlock && faceAxis == clickedState.get(AXIS)) {
            axis = clickedState.get(AXIS);
            vPos = clickedState.get(VERT_POS);
            hPos = clickedState.get(HORIZ_POS);
        } else if (player != null && player.isSneaking()) {

            if (faceAxis == Direction.Axis.Y) {
                vPos = (side == Direction.UP) ? BeamPos.NEGATIVE : BeamPos.POSITIVE;

                // WHILE CROUCHED
                // If player faces North/South (Z), beam goes East/West (X)
                // If player faces East/West (X), beam goes North/South (Z)
                axis = (player.getHorizontalFacing().getAxis() == Direction.Axis.Z)
                        ? Direction.Axis.X
                        : Direction.Axis.Z;
                hPos = calculatePos(ctx, faceAxis, axis == Direction.Axis.X);

            } else {
                // If clicking a wall (X or Z), stand it up vertically
                double hitY = ctx.getHitPos().y - (double) ctx.getBlockPos().getY();

                if (hitY < 0.25 || hitY > 0.75) {
                    // 1. TRIM SNAP: Place a horizontal beam flush to top or bottom
                    if (faceAxis == Direction.Axis.X) {
                        axis = Direction.Axis.Z;
                        vPos = (hitY < 0.25) ? BeamPos.NEGATIVE : BeamPos.POSITIVE;
                        hPos = (side == Direction.WEST) ? BeamPos.POSITIVE : BeamPos.NEGATIVE;
                    } else {

                        // Ensure the trim is flush against the wall face
                        axis = Direction.Axis.X;
                        vPos = (hitY < 0.25) ? BeamPos.NEGATIVE : BeamPos.POSITIVE;
                        hPos = (side == Direction.NORTH) ? BeamPos.POSITIVE : BeamPos.NEGATIVE;
                    }
                } else {
                    axis = Direction.Axis.Y;
                    if (faceAxis == Direction.Axis.X) {
                        hPos = (side == Direction.WEST) ? BeamPos.POSITIVE : BeamPos.NEGATIVE;
                        vPos = calculatePos(ctx, faceAxis, false);
                    } else {
                        vPos = (side == Direction.NORTH) ? BeamPos.POSITIVE : BeamPos.NEGATIVE;
                        hPos = calculatePos(ctx, faceAxis, false);
                    }
                }
            }
        } else {
            axis = faceAxis;
        }

        return this.getDefaultState().with(AXIS, axis).with(VERT_POS, vPos).with(HORIZ_POS, hPos);
    }

    private BeamPos calculatePos(ItemPlacementContext ctx, Direction.Axis axis, boolean isVertical) {
        // Calculate where on the 1x1 face the player clicked (0.0 to 1.0)
        double hitX = ctx.getHitPos().x - (double) ctx.getBlockPos().getX();
        double hitY = ctx.getHitPos().y - (double) ctx.getBlockPos().getY();
        double hitZ = ctx.getHitPos().z - (double) ctx.getBlockPos().getZ();

        double coord;
        if (axis == Direction.Axis.X) {
            coord = isVertical ? hitY : hitZ;
        } else if (axis == Direction.Axis.Y) {
            coord = isVertical ? hitZ : hitX;
        } else { // Z Axis
            coord = isVertical ? hitY : hitX;
        }

        if (coord < 0.25) return BeamPos.NEGATIVE;
        if (coord > 0.75) return BeamPos.POSITIVE;
        return BeamPos.MIDDLE;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction.Axis axis = state.get(AXIS);
        BeamPos v = state.get(VERT_POS);
        BeamPos h = state.get(HORIZ_POS);

        // Grid coordinates: Middle is 4-12, Neg is 0-8, Pos is 8-16
        double minV = (v == BeamPos.NEGATIVE) ? 0 : (v == BeamPos.POSITIVE) ? 8 : 4;
        double maxV = minV + 8;
        double minH = (h == BeamPos.NEGATIVE) ? 0 : (h == BeamPos.POSITIVE) ? 8 : 4;
        double maxH = minH + 8;

        return switch (axis) {
            case X -> VoxelShapes.cuboid(0, minV / 16.0, minH / 16.0, 1.0, maxV / 16.0, maxH / 16.0);
            case Y -> VoxelShapes.cuboid(minH / 16.0, 0, minV / 16.0, maxH / 16.0, 1.0, maxV / 16.0);
            case Z -> VoxelShapes.cuboid(minH / 16.0, minV / 16.0, 0, maxH / 16.0, maxV / 16.0, 1.0);
        };
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS, VERT_POS, HORIZ_POS);
    }
}