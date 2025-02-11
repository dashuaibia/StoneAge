package com.yanny.age.stone.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.yanny.age.stone.blocks.DryingRackTileEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

import static net.minecraft.client.renderer.block.model.ItemTransforms.*;

@OnlyIn(Dist.CLIENT)
public class DryingRackRenderer implements BlockEntityRenderer<DryingRackTileEntity> {
    public DryingRackRenderer(@Nonnull BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(@Nonnull DryingRackTileEntity tileEntityIn, float partialTicks, @Nonnull PoseStack matrixStack,
                       @Nonnull MultiBufferSource renderTypeBuffer, int overlayUV, int lightmapUV) {
        Direction direction = tileEntityIn.getBlockState().getValue(HorizontalDirectionalBlock.FACING);
        int p = (int)tileEntityIn.getBlockPos().asLong();

        for (int i = 0; i < DryingRackTileEntity.ITEMS; i++) {
            float xOffset = (direction == Direction.EAST || direction == Direction.WEST) ? ((i / 2 == 0) ? 0.46f : 0.54f) : ((i % 2 == 0) ? 0.35f : 0.65f);
            float zOffset = (direction == Direction.NORTH || direction == Direction.SOUTH) ? ((i / 2 == 0) ? 0.46f : 0.54f) : ((i % 2 == 0) ? 0.35f : 0.65f);

            matrixStack.pushPose();
            matrixStack.translate(xOffset, 14 / 16.0, zOffset);
            matrixStack.mulPose(Axis.YP.rotationDegrees((direction == Direction.EAST || direction == Direction.WEST) ? 90 : 0));
            matrixStack.scale(0.25F, 0.25F, 0.25F);

            Minecraft.getInstance().getItemRenderer().renderStatic(tileEntityIn.getInventory().getItem(i), ItemDisplayContext.FIXED,
                    overlayUV, lightmapUV, matrixStack, renderTypeBuffer,tileEntityIn.getLevel(),p);
            Minecraft.getInstance().getItemRenderer().renderStatic(tileEntityIn.getInventory().getItem(i + DryingRackTileEntity.ITEMS), ItemDisplayContext.FIXED,
                    overlayUV, lightmapUV, matrixStack, renderTypeBuffer,tileEntityIn.getLevel(),p);

            matrixStack.popPose();
        }
    }
}
