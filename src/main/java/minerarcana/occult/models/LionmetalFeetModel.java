package minerarcana.occult.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LionmetalFeetModel extends BipedModel<LivingEntity> {
	private final ModelRenderer rightfoot;
	private final ModelRenderer leftfoot;

	public LionmetalFeetModel() {
		super(1.1F, 0, 64, 64);
		this.textureWidth = 64;
		this.textureHeight = 64;

		rightfoot = new ModelRenderer(this);
		rightfoot.setRotationPoint(8.0F, 24.0F, -9.0F);
		rightfoot.setTextureOffset(0, 0).addBox(-12.0F, -6.0F, 7.0F, 5.0F, 6.0F, 4.0F, 0.0F, false);

		leftfoot = new ModelRenderer(this);
		leftfoot.setRotationPoint(8.0F, 24.0F, -9.0F);
		leftfoot.setTextureOffset(0, 0).addBox(-9.0F, -6.0F, 7.0F, 5.0F, 6.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
					   float red, float green, float blue, float alpha) {
		rightfoot.rotateAngleX = this.bipedRightLeg.rotateAngleX;
		rightfoot.rotateAngleY = this.bipedRightLeg.rotateAngleY;
		rightfoot.rotateAngleZ = this.bipedRightLeg.rotateAngleZ;
		rightfoot.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		leftfoot.rotateAngleX = this.bipedLeftLeg.rotateAngleX;
		leftfoot.rotateAngleY = this.bipedLeftLeg.rotateAngleY;
		leftfoot.rotateAngleZ = this.bipedLeftLeg.rotateAngleZ;
		leftfoot.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

}