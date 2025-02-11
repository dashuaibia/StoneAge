package com.yanny.age.stone.blocks;

import com.yanny.age.stone.subscribers.EntitySubscriber;
import com.yanny.age.stone.subscribers.TileEntitySubscriber;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.Containers;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class StoneChestBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock, EntityBlock {
    private static final VoxelShape SHAPE = Block.box(1, 0, 1, 15, 14, 15);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public StoneChestBlock() {
        super(Properties.of().strength(2.0f));
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED,false));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new StoneChestTileEntity(pos,state);
    }
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, TileEntitySubscriber.stone_chest,StoneChestTileEntity::tick);
    }
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> type, BlockEntityType<E> entitytype, BlockEntityTicker<? super E> ticker) {
        return type == entitytype ? (BlockEntityTicker<A>) ticker : null;
    }
    @SuppressWarnings("deprecation")
    @Override
    @Nonnull
    public RenderShape getRenderShape(@Nonnull BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
    	FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
    }
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
     }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    public boolean hasDynamicShape() {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, @Nonnull Level worldIn, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileentity = worldIn.getBlockEntity(pos);

            if (tileentity instanceof StoneChestTileEntity) {
                Containers.dropContents(worldIn, pos, ((StoneChestTileEntity)tileentity).getInventory());
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    @Nonnull
    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(@Nonnull BlockState state, Level worldIn, @Nonnull BlockPos pos, @Nonnull Player player,
                                             @Nonnull InteractionHand handIn, @Nonnull BlockHitResult hit) {
        StoneChestTileEntity tile = (StoneChestTileEntity) worldIn.getBlockEntity(pos);

        if (tile != null) {
            if (!worldIn.isClientSide) {
                NetworkHooks.openScreen((ServerPlayer) player, tile, tile.getBlockPos());
            }

            return InteractionResult.SUCCESS;
        } else {
            throw new IllegalStateException("Named container provider is missing");
        }
    }

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.getValue(WATERLOGGED)) {
	         worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
	      }
		return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}
}
