package minerarcana.occult.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import minerarcana.occult.tileentities.CrucibleTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;

import java.util.ArrayList;
import java.util.List;

import static minerarcana.occult.blocks.RotatableBlock.FACING;

public class CrucibleTileRenderer extends TileEntityRenderer<CrucibleTile> {

    public CrucibleTileRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(CrucibleTile tileEntityIn, float partialTicks, MatrixStack matrix, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        float a;
        float b;
        float c;
        float d;
        if (tileEntityIn.getBlockState().get(FACING).equals(Direction.NORTH) || tileEntityIn.getBlockState().get(FACING).equals(Direction.SOUTH)) {
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
        if (tileEntityIn.getItemList().size() == 1) {
            renderItem(tileEntityIn.getItemList().get(0), .5F, 1.7F, .5F, 0.3F, matrix, combinedLightIn, combinedOverlayIn, bufferIn);
        } else if (tileEntityIn.getItemList().size() == 2) {
            renderItem(tileEntityIn.getItemList().get(0), a, 1.7F, c, 0.3F, matrix, combinedLightIn, combinedOverlayIn, bufferIn);
            renderItem(tileEntityIn.getItemList().get(1), b, 1.7F, d, 0.3F, matrix, combinedLightIn, combinedOverlayIn, bufferIn);
        } else if (tileEntityIn.getItemList().size() == 3) {
            renderItem(tileEntityIn.getItemList().get(0), .5F, 1.7F, .5F, 0.3F, matrix, combinedLightIn, combinedOverlayIn, bufferIn);
            renderItem(tileEntityIn.getItemList().get(1), a, 1.6F, c, 0.3F, matrix, combinedLightIn, combinedOverlayIn, bufferIn);
            renderItem(tileEntityIn.getItemList().get(2), b, 1.6F, d, 0.3F, matrix, combinedLightIn, combinedOverlayIn, bufferIn);
        }

    }

    private void renderItem(ItemStack item, float x, float y, float z, float scale, MatrixStack stack, int lightIn, int overlay, IRenderTypeBuffer buffer) {
        stack.push();
        stack.translate(x, y, z);
        stack.scale(scale, scale, scale);
        Minecraft.getInstance().getItemRenderer().renderItem(item, ItemCameraTransforms.TransformType.FIXED, lightIn, overlay, stack, buffer);
        stack.pop();
    }
}
