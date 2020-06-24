package minerarcana.occult.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import minerarcana.occult.tileentities.CrucibleTile;
import minerarcana.occult.util.FluidRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.fluids.FluidStack;

import static minerarcana.occult.blocks.base.RotatableBlock.FACING;

public class CrucibleTileRenderer extends TileEntityRenderer<CrucibleTile> {

    public CrucibleTileRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(CrucibleTile tile, float partialTicks, MatrixStack matrix, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        renderInventory(tile,matrix,bufferIn,combinedLightIn,combinedOverlayIn);
        renderFluidInCrucible(tile, matrix, bufferIn, combinedLightIn);
    }

    private void renderFluidInCrucible(CrucibleTile tile,MatrixStack matrix,IRenderTypeBuffer bufferIn,int combinedLightIn){
        FluidStack liquid = tile.getFluidStack();
        IVertexBuilder builder = bufferIn.getBuffer(FluidRenderer.getBlockRenderType());
        float minY = 5.1F;
        float maxY = 19F;
        if(!liquid.isEmpty()){
            float sections = (maxY - minY)/tile.getCapacity();
            float height = (float)liquid.getAmount()*sections;
            FluidRenderer.renderScaledFluidCuboid(liquid,matrix,builder,combinedLightIn,4F,minY,4F,12F,minY + height,12F);
        }
    }

    private void renderInventory(CrucibleTile tile,MatrixStack matrix, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn){
        float a;
        float b;
        float c;
        float d;
        if (isNorthorSouth(tile)) {
            a = 0.25F;
            b = 0.75F;
            c = 0.5F;
            d = 0.5F;
        } else {
            a = 0.5F;
            b = 0.5F;
            c = 0.25F;
            d = 0.75F;
        }
        if (tile.getItemList().size() == 1) {
            renderItem(tile.getItemList().get(0), tile,.5F, 1.8F, .5F, 0.3F, matrix, combinedLightIn, combinedOverlayIn, bufferIn);
        } else if (tile.getItemList().size() == 2) {
            renderItem(tile.getItemList().get(0), tile,a, 1.8F, c, 0.3F, matrix, combinedLightIn, combinedOverlayIn, bufferIn);
            renderItem(tile.getItemList().get(1), tile,b, 1.8F, d, 0.3F, matrix, combinedLightIn, combinedOverlayIn, bufferIn);
        } else if (tile.getItemList().size() == 3) {
            renderItem(tile.getItemList().get(0), tile,.5F, 1.8F, .5F, 0.3F, matrix, combinedLightIn, combinedOverlayIn, bufferIn);
            renderItem(tile.getItemList().get(1), tile,a, 1.7F, c, 0.3F, matrix, combinedLightIn, combinedOverlayIn, bufferIn);
            renderItem(tile.getItemList().get(2), tile,b, 1.7F, d, 0.3F, matrix, combinedLightIn, combinedOverlayIn, bufferIn);
        }

        if(!tile.getItemInSlot(3).isEmpty()){
            renderItem(tile.getItemInSlot(3),tile,.5F, 1.45F, .5F,0.3F,matrix,combinedLightIn,combinedOverlayIn,bufferIn);
        }
    }

    private boolean isNorthorSouth(CrucibleTile tile){
        return tile.getBlockState().get(FACING).equals(Direction.NORTH) || tile.getBlockState().get(FACING).equals(Direction.SOUTH);
    }

    private void renderItem(ItemStack item, CrucibleTile tile,float x, float y, float z, float scale, MatrixStack stack, int lightIn, int overlay, IRenderTypeBuffer buffer) {
        stack.push();
        stack.translate(x, y, z);
        if(!isNorthorSouth(tile)){
            stack.rotate(new Quaternion(90,-90,90,true));
        }
        stack.scale(scale, scale, scale);
        Minecraft.getInstance().getItemRenderer().renderItem(item, ItemCameraTransforms.TransformType.FIXED, lightIn, overlay, stack, buffer);
        stack.pop();
    }
}
