package mod.maxbogomol.wizards_reborn.common.block.light_transfer_lens;

import mod.maxbogomol.fluffy_fur.common.block.entity.BlockSimpleInventory;
import mod.maxbogomol.fluffy_fur.common.block.entity.TickableBlockEntity;
import mod.maxbogomol.fluffy_fur.common.network.BlockEntityUpdate;
import mod.maxbogomol.wizards_reborn.common.item.equipment.WissenWandItem;
import mod.maxbogomol.wizards_reborn.registry.common.WizardsRebornSounds;
import mod.maxbogomol.wizards_reborn.registry.common.item.WizardsRebornItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.stream.Stream;

public class LightTransferLensBlock extends FaceAttachedHorizontalDirectionalBlock implements EntityBlock, SimpleWaterloggedBlock  {

    private static final VoxelShape SHAPE_D = Stream.of(
            Block.box(5, 0, 5, 11, 2, 11),
            Block.box(7, 2, 7, 9, 3, 9),
            Block.box(6, 3, 6, 10, 4, 10),
            Block.box(7, 4, 7, 9, 5, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_T = Stream.of(
            Block.box(5, 14, 5, 11, 16, 11),
            Block.box(7, 13, 7, 9, 14, 9),
            Block.box(6, 12, 6, 10, 13, 10),
            Block.box(7, 11, 7, 9, 12, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.box(5, 5, 14, 11, 11, 16),
            Block.box(7, 7, 13, 9, 9, 14),
            Block.box(6, 6, 12, 10, 10, 13),
            Block.box(7, 7, 11, 9, 9, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.box(5, 5, 0, 11, 11, 2),
            Block.box(7, 7, 2, 9, 9, 3),
            Block.box(6, 6, 3, 10, 10, 4),
            Block.box(7, 7, 4, 9, 9, 5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.box(14, 5, 5, 16, 11, 11),
            Block.box(13, 7, 7, 14, 9, 9),
            Block.box(12, 6, 6, 13, 10, 10),
            Block.box(11, 7, 7, 12, 9, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.box(0, 5, 5, 2, 11, 11),
            Block.box(2, 7, 7, 3, 9, 9),
            Block.box(3, 6, 6, 4, 10, 10),
            Block.box(4, 7, 7, 5, 9, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public LightTransferLensBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, false));
    }

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACE)) {
            case FLOOR -> SHAPE_D;
            case WALL -> switch (state.getValue(FACING)) {
                case NORTH -> SHAPE_N;
                case SOUTH -> SHAPE_S;
                case WEST -> SHAPE_W;
                case EAST -> SHAPE_E;
                default -> SHAPE_N;
            };
            case CEILING -> SHAPE_T;
            default -> SHAPE_D;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(FACE);
        builder.add(BlockStateProperties.WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        for(Direction direction : context.getNearestLookingDirections()) {
            BlockState blockstate;
            if (direction.getAxis() == Direction.Axis.Y) {
                blockstate = this.defaultBlockState().setValue(FACE, direction == Direction.UP ? AttachFace.CEILING : AttachFace.FLOOR).setValue(FACING, context.getHorizontalDirection()).setValue(BlockStateProperties.WATERLOGGED, fluidState.getType() == Fluids.WATER);
            } else {
                blockstate = this.defaultBlockState().setValue(FACE, AttachFace.WALL).setValue(FACING, direction.getOpposite()).setValue(BlockStateProperties.WATERLOGGED, fluidState.getType() == Fluids.WATER);
            }

            if (blockstate.canSurvive(context.getLevel(), context.getClickedPos())) {
                return blockstate;
            }
        }
        return this.defaultBlockState();
    }

    @Override
    public void onRemove(@Nonnull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, @Nonnull BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof BlockSimpleInventory blockSimpleInventory) {
                net.minecraft.world.Containers.dropContents(level, pos, (blockSimpleInventory).getItemHandler());
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        LightTransferLensBlockEntity blockEntity = (LightTransferLensBlockEntity) level.getBlockEntity(pos);
        ItemStack stack = player.getItemInHand(hand).copy();

        if (stack.getItem() instanceof WissenWandItem) {
            if (WissenWandItem.getMode(stack) != 4) {
                level.updateNeighbourForOutputSignal(pos, this);
                BlockEntityUpdate.packet(blockEntity);
                return InteractionResult.SUCCESS;
            }
        }

        if ((!stack.isEmpty()) && (blockEntity.getItemHandler().getItem(0).isEmpty())) {
            if (stack.is(WizardsRebornItemTags.ARCANE_LUMOS)) {
                if (stack.getCount() > 1) {
                    player.getItemInHand(hand).setCount(stack.getCount() - 1);
                    stack.setCount(1);
                    blockEntity.getItemHandler().setItem(0, stack);
                    level.updateNeighbourForOutputSignal(pos, this);
                    BlockEntityUpdate.packet(blockEntity);
                    level.playSound(null, pos, WizardsRebornSounds.PEDESTAL_INSERT.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
                    return InteractionResult.SUCCESS;
                } else {
                    blockEntity.getItemHandler().setItem(0, stack);
                    player.getInventory().removeItem(player.getItemInHand(hand));
                    level.updateNeighbourForOutputSignal(pos, this);
                    BlockEntityUpdate.packet(blockEntity);
                    level.playSound(null, pos, WizardsRebornSounds.PEDESTAL_INSERT.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
                    return InteractionResult.SUCCESS;
                }
            }
        }

        if (!blockEntity.getItemHandler().getItem(0).isEmpty()) {
            if (player.getInventory().getSlotWithRemainingSpace(blockEntity.getItemHandler().getItem(0)) != -1 || player.getInventory().getFreeSlot() > -1) {
                player.getInventory().add(blockEntity.getItemHandler().getItem(0).copy());
            } else {
                level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5F, pos.getY() + 1.0F, pos.getZ() + 0.5F, blockEntity.getItemHandler().getItem(0).copy()));
            }
            blockEntity.getItemHandler().removeItem(0, 1);
            level.updateNeighbourForOutputSignal(pos, this);
            BlockEntityUpdate.packet(blockEntity);
            level.playSound(null, pos, WizardsRebornSounds.PEDESTAL_REMOVE.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : Fluids.EMPTY.defaultFluidState();
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (state.getValue(BlockStateProperties.WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LightTransferLensBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        return TickableBlockEntity.getTickerHelper();
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        BlockSimpleInventory blockEntity = (BlockSimpleInventory) level.getBlockEntity(pos);
        return AbstractContainerMenu.getRedstoneSignalFromContainer(blockEntity.getItemHandler());
    }
}
