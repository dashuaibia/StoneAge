package com.yanny.age.stone.blocks;

import com.yanny.age.stone.compatibility.top.TopBlockInfoProvider;
import com.yanny.age.stone.subscribers.TileEntitySubscriber;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MillstoneBlock extends Block implements TopBlockInfoProvider , EntityBlock {
    private static final VoxelShape SHAPE = Shapes.or(Block.box(0, 0, 0, 16, 3, 16),
            Block.box(2.5, 3, 2.5, 13.5, 7, 13.5),
            Block.box(3, 7.05, 3, 13, 11, 13),
            Block.box(7, 7, 7, 9, 12, 9));

    public MillstoneBlock() {
        super(Properties.of().strength(4.0f));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MillstoneTileEntity(pos,state);
    }
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, TileEntitySubscriber.millstone,MillstoneTileEntity::tick);
    }
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> type, BlockEntityType<E> entitytype, BlockEntityTicker<? super E> ticker) {
        return type == entitytype ? (BlockEntityTicker<A>) ticker : null;
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

            if (tileentity instanceof MillstoneTileEntity) {
                Containers.dropContents(worldIn, pos, ((MillstoneTileEntity)tileentity).getInventory());
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    @Nonnull
    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(@Nonnull BlockState state, Level worldIn, @Nonnull BlockPos pos, @Nonnull Player player,
                                             @Nonnull InteractionHand handIn, @Nonnull BlockHitResult hit) {
        MillstoneTileEntity tile = (MillstoneTileEntity) worldIn.getBlockEntity(pos);

        if (tile != null) {
            if (player.isShiftKeyDown()) {
                if (!worldIn.isClientSide) {
                    tile.onActivated();
                    return InteractionResult.SUCCESS;
                }
            } else {
                if (!worldIn.isClientSide) {
                    NetworkHooks.openScreen((ServerPlayer) player, tile, tile.getBlockPos());
                }

                return InteractionResult.SUCCESS;
            }

            return InteractionResult.CONSUME;
        } else {
            throw new IllegalStateException("Named container provider is missing");
        }
    }

    /*@Override
    public void addProbeInfo(@Nonnull ProbeMode probeMode, @Nonnull IProbeInfo iProbeInfo, @Nonnull PlayerEntity playerEntity,
                             @Nonnull World world, @Nonnull BlockState blockState, @Nonnull IProbeHitData iProbeHitData) {
        TileEntity te = world.getBlockEntity(iProbeHitData.getPos());

        if (te instanceof MillstoneTileEntity) {
            MillstoneTileEntity millstone = (MillstoneTileEntity) te;

            if (!millstone.getResult().isEmpty()) {
                iProbeInfo.horizontal().item(millstone.getResult()).progress(millstone.getCraftingProgress(), 100, iProbeInfo.defaultProgressStyle().suffix("%"));
            }
        }
    }*/
}
