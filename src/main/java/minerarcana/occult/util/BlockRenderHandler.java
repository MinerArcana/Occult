package minerarcana.occult.util;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

import static minerarcana.occult.content.OccultBlocks.*;

public class BlockRenderHandler {

    public static void setRenderLayers(){
        RenderType cutout = RenderType.getCutout();
        RenderTypeLookup.setRenderLayer(CRUCIBLE.get(), cutout);
        RenderTypeLookup.setRenderLayer(CRUCIBLETOP.get(), cutout);
    }

}
