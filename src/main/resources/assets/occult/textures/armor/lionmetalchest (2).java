// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class fullarmor extends EntityModel<Entity> {
	private final ModelRenderer torso;
	private final ModelRenderer rightarm;
	private final ModelRenderer rightpauldron;
	private final ModelRenderer leftarm;
	private final ModelRenderer leftpauldron;

	public fullarmor() {
		textureWidth = 64;
		textureHeight = 64;

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
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		torso.render(matrixStack, buffer, packedLight, packedOverlay);
		rightarm.render(matrixStack, buffer, packedLight, packedOverlay);
		leftarm.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}