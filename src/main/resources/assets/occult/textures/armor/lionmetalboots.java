// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class fullarmor extends EntityModel<Entity> {
	private final ModelRenderer rightfoot;
	private final ModelRenderer leftfoot;

	public fullarmor() {
		textureWidth = 16;
		textureHeight = 16;

		rightfoot = new ModelRenderer(this);
		rightfoot.setRotationPoint(8.0F, 24.0F, -9.0F);
		rightfoot.setTextureOffset(0, 0).addBox(-12.0F, -6.0F, 7.0F, 5.0F, 6.0F, 4.0F, 0.0F, false);

		leftfoot = new ModelRenderer(this);
		leftfoot.setRotationPoint(8.0F, 24.0F, -9.0F);
		leftfoot.setTextureOffset(0, 0).addBox(-9.0F, -6.0F, 7.0F, 5.0F, 6.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		rightfoot.render(matrixStack, buffer, packedLight, packedOverlay);
		leftfoot.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}