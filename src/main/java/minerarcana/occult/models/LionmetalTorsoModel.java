package minerarcana.occult.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LionmetalTorsoModel extends BipedModel<LivingEntity> {
    private final ModelRenderer torso;
    private final ModelRenderer rightarm;
    private final ModelRenderer rightpauldron;
    private final ModelRenderer leftarm;
    private final ModelRenderer leftpauldron;


    public LionmetalTorsoModel() {
        super(1.1F, 0, 64, 64);
        this.textureWidth = 64;
        this.textureHeight = 64;

        torso = new ModelRenderer(this);
        torso.setRotationPoint(-7.0F, 14.0F, 0.0F);
        torso.setTextureOffset(1, 28).addBox(3.0F, -14.0F, -4.0F, 8.0F, 8.0F, 5.0F, 0.0F, false);
        torso.setTextureOffset(32, 0).addBox(3.0F, -14.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.1F, false);

        rightarm = new ModelRenderer(this);
        rightarm.setRotationPoint(4.0F, 0.0F, -4.0F);
        rightarm.setTextureOffset(0, 0).addBox(0.2F, 0.0F, 2.0F, 4.0F, 12.0F, 4.0F, 0.1F, false);

        rightpauldron = new ModelRenderer(this);
        rightpauldron.setRotationPoint(4.0F, 24.0F, -5.0F);
        rightarm.addChild(rightpauldron);
        rightpauldron.setTextureOffset(22, 16).addBox(-4.0F, -24.0F, 6.0F, 5.0F, 5.0F, 6.0F, 0.0F, false);

        leftarm = new ModelRenderer(this);
        leftarm.setRotationPoint(-7.0F, 4.0F, -1.0F);
        leftarm.setTextureOffset(0, 0).addBox(-1.2F, -4.0F, -1.0F, 4.0F, 12.0F, 4.0F, 0.1F, false);

        leftpauldron = new ModelRenderer(this);
        leftpauldron.setRotationPoint(11.0F, -4.0F, -3.0F);
        leftarm.addChild(leftpauldron);
        leftpauldron.setTextureOffset(22, 16).addBox(-13.0F, 0.0F, 1.0F, 5.0F, 5.0F, 6.0F, 0.0F, false);

    }



    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
                       float red, float green, float blue, float alpha) {
        torso.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        leftarm.rotateAngleX = this.bipedRightArm.rotateAngleX;
        leftarm.rotateAngleY = this.bipedRightArm.rotateAngleY;
        leftarm.rotateAngleZ = this.bipedRightArm.rotateAngleZ;
        leftarm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        rightarm.rotateAngleX = this.bipedLeftArm.rotateAngleX;
        rightarm.rotateAngleY = this.bipedLeftArm.rotateAngleY;
        rightarm.rotateAngleZ = this.bipedLeftArm.rotateAngleZ;
        rightarm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

}