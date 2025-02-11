package com.yanny.age.stone.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yanny.age.stone.Reference;
import com.yanny.age.stone.client.models.SaberToothTigerModel;
import com.yanny.age.stone.entities.SaberToothTigerEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class SaberToothTigerRenderer extends MobRenderer<SaberToothTigerEntity, SaberToothTigerModel> {
    private static final ResourceLocation SABER_TOOTH_TIGER_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/saber_tooth_tiger.png");

    public SaberToothTigerRenderer(@Nonnull EntityRendererProvider.Context context) {
        super(context, new SaberToothTigerModel(context.bakeLayer(SaberToothTigerModel.SABERTOOTHTIGER_LAYER)), 0.5f);
    }

    @Override
    public void render(@Nonnull SaberToothTigerEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, @Nonnull MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.scale(1.2f, 1.2f, 1.2f);
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull SaberToothTigerEntity entity) {
        return SABER_TOOTH_TIGER_TEXTURE;
    }

    @Override
    protected boolean shouldShowName(SaberToothTigerEntity entity) {
        return entity.hasCustomName();
    }

   /* public static class RenderFactory implements IRenderFactory<SaberToothTigerEntity> {

        @Override
        public EntityRenderer<? super SaberToothTigerEntity> createRenderFor(EntityRenderDispatcher manager) {
            return new SaberToothTigerRenderer(manager);
        }
    }*/
}
