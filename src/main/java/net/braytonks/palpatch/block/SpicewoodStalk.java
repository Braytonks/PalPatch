package net.braytonks.palpatch.block;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.HashMap;
import java.util.Map;

public class SpicewoodStalk extends Block {
    public static final IntProperty AGE = Properties.AGE_1;
    public static final IntProperty STAGE = IntProperty.of("stage", 0, 5);
    public static final BooleanProperty STRIPPED = BooleanProperty.of("stripped");

    public static final EnumProperty<SpicewoodLeaf> NORTH_LEAF = EnumProperty.of("north", SpicewoodLeaf.class);
    public static final EnumProperty<SpicewoodLeaf> SOUTH_LEAF = EnumProperty.of("south", SpicewoodLeaf.class);
    public static final EnumProperty<SpicewoodLeaf> EAST_LEAF = EnumProperty.of("east", SpicewoodLeaf.class);
    public static final EnumProperty<SpicewoodLeaf> WEST_LEAF = EnumProperty.of("west", SpicewoodLeaf.class);

    public enum SpicewoodLeaf implements StringIdentifiable {
        NONE("none"), LEAFLET("leaflet"), SMALL("small"), LARGE("large");
        private final String name;

        SpicewoodLeaf(String name) {
            this.name = name;
        }

        @Override
        public String asString() {
            return this.name;
        }
    }

    protected static final VoxelShape YOUNG_STALK = Block.createCuboidShape(6.5, 0.0, 6.5, 9.5, 16.0, 9.5);
    protected static final VoxelShape MATURE_STALK = Block.createCuboidShape(6, 0.0, 6, 10, 16.0, 10);

    private final Map<BlockState, VoxelShape> shapeCache = new HashMap<>();
    private static final int MAX_HEIGHT = 16;

    public SpicewoodStalk(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(AGE, 0).with(STAGE, 0)
                .with(STRIPPED, false)
                .with(NORTH_LEAF, SpicewoodLeaf.NONE).with(SOUTH_LEAF, SpicewoodLeaf.NONE)
                .with(EAST_LEAF, SpicewoodLeaf.NONE).with(WEST_LEAF, SpicewoodLeaf.NONE));
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int currentStage = state.get(STAGE);
        if (currentStage >= 5) return;

        boolean actionTaken = false;
        BlockState nextState = state;
        int heightBelow = this.getHeightBelow(world, pos);
        int currentTotalHeight = heightBelow + 1;

        // --- PART 1: STAGGERED VERTICAL GROWTH ---
        if (world.isAir(pos.up())) {
            // Lower stalks grow fast; taller stalks (8+) have a higher chance to stall
            int growthChance = (currentTotalHeight >= 8) ? 4 : 2;

            if (currentTotalHeight < MAX_HEIGHT && random.nextInt(growthChance) == 0) {
                world.setBlockState(pos.up(), this.getDefaultState().with(AGE, 0).with(STAGE, 0), 3);
                if (currentTotalHeight >= 5) this.ageThicken(world, pos.up());
                actionTaken = true;
            }
            // VARIATION LOGIC: 20% chance to permanently "cap" growth if it didn't grow this tick
            else if (currentTotalHeight >= 8 && random.nextInt(5) == 0) {
                world.setBlockState(pos, state.with(STAGE, 5), 3);
                return;
            }
        }

        // --- PART 2: LEAF GROWTH (Only if 3+ blocks off the ground) ---
        if (!actionTaken && heightBelow >= 3) {
            Direction randomDir = Direction.Type.HORIZONTAL.random(random);
            SpicewoodLeaf currentLeaf = state.get(getProperty(randomDir));

            if (currentLeaf != SpicewoodLeaf.LARGE) {
                if (random.nextInt(2) == 0) {
                    SpicewoodLeaf next = switch (currentLeaf) {
                        case NONE -> SpicewoodLeaf.LEAFLET;
                        case LEAFLET -> SpicewoodLeaf.SMALL;
                        default -> SpicewoodLeaf.LARGE;
                    };
                    nextState = nextState.with(getProperty(randomDir), next);
                    actionTaken = true;
                }
            }
        }

        // --- PART 3: FATIGUE RESOLUTION ---
        if (actionTaken) {
            world.setBlockState(pos, nextState, 3);
        } else {
            world.setBlockState(pos, state.with(STAGE, currentStage + 1), 3);
        }
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape base = state.get(AGE) == 1 ? MATURE_STALK : YOUNG_STALK;
        Vec3d offset = state.getModelOffset(world, pos);
        return base.offset(offset.x, offset.y, offset.z);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Only the central stalk has collision to allow smooth movement through groves
        VoxelShape base = state.get(AGE) == 1 ? MATURE_STALK : YOUNG_STALK;
        Vec3d offset = state.getModelOffset(world, pos);
        return base.offset(offset.x, offset.y, offset.z);
    }

    private void ageThicken(World world, BlockPos pos) {
        BlockPos.Mutable mutablePos = pos.mutableCopy();
        while (world.getBlockState(mutablePos).isOf(this)) {
            BlockState s = world.getBlockState(mutablePos);
            if (s.get(AGE) == 0) world.setBlockState(mutablePos, s.with(AGE, 1), 3);
            mutablePos.move(Direction.DOWN);
        }
        mutablePos.set(pos);
        while (world.getBlockState(mutablePos).isOf(this)) {
            BlockState s = world.getBlockState(mutablePos);
            if (s.get(AGE) == 0) world.setBlockState(mutablePos, s.with(AGE, 1), 3);
            mutablePos.move(Direction.UP);
        }
    }

    protected int getHeightBelow(BlockView world, BlockPos pos) {
        int i = 0;
        while (i < MAX_HEIGHT && world.getBlockState(pos.down(i + 1)).isOf(this)) {
            i++;
        }
        return i;
    }

    private EnumProperty<SpicewoodLeaf> getProperty(Direction direction) {
        return switch (direction) {
            case NORTH -> NORTH_LEAF;
            case SOUTH -> SOUTH_LEAF;
            case EAST -> EAST_LEAF;
            default -> WEST_LEAF;
        };
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState floor = world.getBlockState(pos.down());
        return floor.isOf(this) || floor.isIn(BlockTags.DIRT) || floor.isIn(BlockTags.SAND);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STAGE, AGE, STRIPPED, NORTH_LEAF, SOUTH_LEAF, EAST_LEAF, WEST_LEAF);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.DOWN && !state.canPlaceAt(world, pos)) return Blocks.AIR.getDefaultState();
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.getItem() instanceof AxeItem && !state.get(STRIPPED)) {
            world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1f, 1f);
            if (!world.isClient) {
                BlockState strippedState = state.with(STRIPPED, true)
                        .with(AGE, 0)
                        .with(NORTH_LEAF, SpicewoodLeaf.NONE)
                        .with(SOUTH_LEAF, SpicewoodLeaf.NONE)
                        .with(EAST_LEAF, SpicewoodLeaf.NONE)
                        .with(WEST_LEAF, SpicewoodLeaf.NONE);

                world.setBlockState(pos, strippedState, 3);
                stack.damage(1, player, LivingEntity.getSlotForHand(hand));
            }
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}