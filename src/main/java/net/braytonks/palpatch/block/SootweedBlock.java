package net.braytonks.palpatch.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.VineBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class SootweedBlock extends VineBlock {
    public static final BooleanProperty CAN_GROW = BooleanProperty.of("can_grow");

    public SootweedBlock(Settings settings) {
        super(settings);

        this.setDefaultState(this.getStateManager().getDefaultState().with(UP, false).with(NORTH, false)
                .with(EAST, false).with(SOUTH, false).with(WEST, false).with(CAN_GROW, true));

    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(CAN_GROW);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.isOf(Items.SHEARS) && state.get(CAN_GROW)) {
            if (!world.isClient) {
                world.setBlockState(pos, state.with(CAN_GROW, false), Block.NOTIFY_LISTENERS);
                world.playSound(null,pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS,1f,1f);
                stack.damage(1,player, LivingEntity.getSlotForHand(hand));
            }
            return ItemActionResult.success(world.isClient);
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(CAN_GROW)) {
            super.randomTick(state, world, pos, random);
        }
    }

    private boolean canGrowInto(BlockState state) {
        if (!state.isOf(this)) return true;
        return state.get(CAN_GROW);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = super.getPlacementState(ctx);
        if (state != null && state.contains(CAN_GROW)) {
            World world = ctx.getWorld();
            BlockPos pos = ctx.getBlockPos();
            BlockState lookingAt = world.getBlockState(ctx.getBlockPos().offset(ctx.getSide().getOpposite()));

            if (lookingAt.isOf(this) && lookingAt.contains(CAN_GROW) && !lookingAt.get(CAN_GROW)) {
                return state.with(CAN_GROW, false);
            }
        }
        return state;
    }
}
