package com.yanny.age.stone.blocks;

import com.yanny.age.stone.compatibility.top.TopBlockInfoProvider;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.Containers;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DryingRackBlock extends HorizontalDirectionalBlock implements TopBlockInfoProvider {
    private static final VoxelShape SHAPE_NS = Block.box(0.0D, 0.0D, 7.5D, 16.0D, 16.0D, 8.5D);
    private static final VoxelShape SHAPE_EW = Block.box(7.5D, 0.0D, 0.0D, 8.5D, 16.0D, 16.0D);

    public DryingRackBlock() {
        super(Block.Properties.of(Material.WOOD).harvestLevel(Tiers.WOOD.getLevel()).harvestTool(ToolType.AXE).strength(2.0f));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
        return new DryingRackTileEntity();
    }

    @SuppressWarnings("deprecation")
    @Override
    @Nonnull
    public RenderShape getRenderShape(@Nonnull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public boolean hasDynamicShape() {
        return true;
    }

    @Nonnull
    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(@Nonnull BlockState state, Level worldIn, @Nonnull BlockPos pos, @Nonnull Player player,
                                             @Nonnull InteractionHand handIn, @Nonnull BlockHitResult hit) {
        BlockEntity tileentity = worldIn.getBlockEntity(pos);

        if (tileentity instanceof DryingRackTileEntity) {
            ((DryingRackTileEntity) tileentity).blockActivated(player);
            return InteractionResult.SUCCESS;
        }

        return super.use(state, worldIn, pos, player, handIn, hit);
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        if (state.getValue(FACING).getAxis() == Direction.Axis.Z) {
            return SHAPE_NS;
        } else {
            return SHAPE_EW;
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, @Nonnull Level worldIn, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileentity = worldIn.getBlockEntity(pos);

            if (tileentity instanceof DryingRackTileEntity) {
                Containers.dropContents(worldIn, pos, ((DryingRackTileEntity)tileentity).getInventory());
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public void addProbeInfo(@Nonnull ProbeMode probeMode, @Nonnull IProbeInfo iProbeInfo, @Nonnull Player playerEntity,
                             @Nonnull Level world, @Nonnull BlockState blockState, @Nonnull IProbeHitData iProbeHitData) {
        BlockEntity te = world.getBlockEntity(iProbeHitData.getPos());

        if (te instanceof DryingRackTileEntity) {
            DryingRackTileEntity dataTileEntity = (DryingRackTileEntity) te;

            for (int i = 0; i < DryingRackTileEntity.ITEMS; i++) {
                DryingRackTileEntity.DryingItem item = dataTileEntity.getItem(i);

                if (!item.result.isEmpty()) {
                    iProbeInfo.horizontal().item(item.result).progress(100 - (int) (item.remaining / (float) item.dryingTime * 100), 100,
                            iProbeInfo.defaultProgressStyle().suffix("%"));
                }
            }
        }
    }
}
