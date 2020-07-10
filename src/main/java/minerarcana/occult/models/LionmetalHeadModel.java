package minerarcana.occult.models;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * lionmetalhelm - Either Mojang or a mod author
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class LionmetalHeadModel extends BipedModel<LivingEntity> {
    public ModelRenderer helm;
    public ModelRenderer wings;
    public ModelRenderer shadow;

    public LionmetalHeadModel() {
        super(1.0F, 0, 64, 64);
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.shadow = new ModelRenderer(this, 36, 31);
        this.shadow.setRotationPoint(-0.1F, -1.0F, 0.0F);
        this.shadow.addBox(-3.5F, -7.8F, -3.6F, 7.0F, 8.0F, 7.0F, 0.5F, 0.5F, 0.5F);
        this.wings = new ModelRenderer(this, 32, 19);
        this.wings.setRotationPoint(-8.0F, -8.6F, -3.4F);
        this.wings.addBox(0.0F, 0.0F, 0.0F, 16.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.helm = new ModelRenderer(this, 32, 0);
        this.helm.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.helm.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 9.0F, 8.0F, 0.5F, 0.5F, 0.5F);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.shadow, this.wings, this.helm).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
