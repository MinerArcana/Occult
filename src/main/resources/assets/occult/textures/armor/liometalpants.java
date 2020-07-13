// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class fullarmor extends EntityModel<Entity> {
	private final ModelRenderer rightleg;
	private final ModelRenderer leftleg;

	public fullarmor() {
		textureWidth = 64;
		textureHeight = 64;

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(0.0F, 16.0F, -1.0F);
		rightleg.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -1.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(0.0F, 16.0F, -1.0F);
		leftleg.setTextureOffset(4, 0).addBox(0.0F, -4.0F, -1.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		rightleg.render(matrixStack, buffer, packedLight, packedOverlay);
		leftleg.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}