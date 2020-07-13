package minerarcana.occult.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LionmetalLegsModel extends BipedModel<LivingEntity> {
    private final ModelRenderer rightleg;
    private final ModelRenderer leftleg;

    public LionmetalLegsModel() {
        super(1.2F, 0, 64, 64);
        this.textureWidth = 64;
        this.textureHeight = 64;

        rightleg = new ModelRenderer(this);
        rightleg.setRotationPoint(0.0F, 16.0F, -1.0F);
        rightleg.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -1.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);

        leftleg = new ModelRenderer(this);
        leftleg.setRotationPoint(0.0F, 16.0F, -1.0F);
        leftleg.setTextureOffset(4, 0).addBox(0.0F, -4.0F, -1.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);
  }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
                       float red, float green, float blue, float alpha) {
        rightleg.rotateAngleX = this.bipedRightLeg.rotateAngleX;
        rightleg.rotateAngleY = this.bipedRightLeg.rotateAngleY;
        rightleg.rotateAngleZ = this.bipedRightLeg.rotateAngleZ;
        rightleg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        leftleg.rotateAngleX = this.bipedLeftLeg.rotateAngleX;
        leftleg.rotateAngleY = this.bipedLeftLeg.rotateAngleY;
        leftleg.rotateAngleZ = this.bipedLeftLeg.rotateAngleZ;
        leftleg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

}