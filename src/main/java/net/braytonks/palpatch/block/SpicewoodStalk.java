package net.braytonks.palpatch.block;

import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
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
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

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
        SpicewoodLeaf(String name) { this.name = name; }
        @Override
        public String asString() { return this.name; }
    }

    protected static final VoxelShape YOUNG_STALK = Block.createCuboidShape(6.5, 0.0, 6.5, 9.5, 16.0, 9.5);
    protected static final VoxelShape MATURE_STALK = Block.createCuboidShape(6, 0.0, 6, 10, 16.0, 10);
    private static final int MAX_HEIGHT = 16;

    public SpicewoodStalk(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(AGE, 0).with(STAGE, 0).with(STRIPPED, false)
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

        if (world.isAir(pos.up())) {
            int growthChance = (currentTotalHeight >= 8) ? 4 : 2;
            if (currentTotalHeight < MAX_HEIGHT && random.nextInt(growthChance) == 0) {
                world.setBlockState(pos.up(), this.getDefaultState().with(AGE, 0).with(STAGE, 0), Block.NOTIFY_ALL);
                if (currentTotalHeight >= 5) this.ageThicken(world, pos.up());
                actionTaken = true;
            } else if (currentTotalHeight >= 8 && random.nextInt(5) == 0) {
                world.setBlockState(pos, state.with(STAGE, 5), Block.NOTIFY_ALL);
                return;
            }
        }

        if (!actionTaken && heightBelow >= 3) {
            Direction randomDir = Direction.Type.HORIZONTAL.random(random);
            EnumProperty<SpicewoodLeaf> prop = getProperty(randomDir);
            SpicewoodLeaf currentLeaf = state.get(prop);

            if (currentLeaf != SpicewoodLeaf.LARGE) {
                if (random.nextBoolean()) {
                    SpicewoodLeaf next = switch (currentLeaf) {
                        case NONE -> SpicewoodLeaf.LEAFLET;
                        case LEAFLET -> SpicewoodLeaf.SMALL;
                        default -> SpicewoodLeaf.LARGE;
                    };
                    nextState = nextState.with(prop, next);
                    actionTaken = true;
                }
            }
        }

        if (actionTaken) {
            world.setBlockState(pos, nextState, Block.NOTIFY_ALL);
        } else {
            world.setBlockState(pos, state.with(STAGE, currentStage + 1), Block.NOTIFY_ALL);
        }
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape base = state.get(AGE) == 1 ? MATURE_STALK : YOUNG_STALK;
        Vec3d offset = state.getModelOffset(pos);
        return base.offset(offset.x, offset.y, offset.z);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape base = state.get(AGE) == 1 ? MATURE_STALK : YOUNG_STALK;
        Vec3d offset = state.getModelOffset(pos);
        return base.offset(offset.x, offset.y, offset.z);
    }

    private void ageThicken(World world, BlockPos pos) {
        BlockPos.Mutable mutablePos = pos.mutableCopy();
        while (world.getBlockState(mutablePos).isOf(this)) {
            BlockState s = world.getBlockState(mutablePos);
            if (s.get(AGE) == 0) world.setBlockState(mutablePos, s.with(AGE, 1), Block.NOTIFY_LISTENERS);
            mutablePos.move(Direction.DOWN);
        }
        mutablePos.set(pos);
        while (world.getBlockState(mutablePos).isOf(this)) {
            BlockState s = world.getBlockState(mutablePos);
            if (s.get(AGE) == 0) world.setBlockState(mutablePos, s.with(AGE, 1), Block.NOTIFY_LISTENERS);
            mutablePos.move(Direction.UP);
        }
    }

    protected int getHeightBelow(BlockView world, BlockPos pos) {
        int i = 0;
        while (i < MAX_HEIGHT && world.getBlockState(pos.down(i + 1)).isOf(this)) { i++; }
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
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        if (direction == Direction.DOWN && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.getItem() instanceof AxeItem && !state.get(STRIPPED)) {
            world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1f, 1f);
            if (!world.isClient) {
                BlockState strippedState = state.with(STRIPPED, true)
                        .with(AGE, 0)
                        .with(NORTH_LEAF, SpicewoodLeaf.NONE)
                        .with(SOUTH_LEAF, SpicewoodLeaf.NONE)
                        .with(EAST_LEAF, SpicewoodLeaf.NONE)
                        .with(WEST_LEAF, SpicewoodLeaf.NONE);

                world.setBlockState(pos, strippedState, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                stack.damage(1, player, hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
            }
            return ActionResult.SUCCESS;
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }
}