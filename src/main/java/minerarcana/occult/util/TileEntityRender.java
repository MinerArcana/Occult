package minerarcana.occult.util;

import minerarcana.occult.content.OccultBlocks;
import minerarcana.occult.render.CrucibleTileRenderer;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class TileEntityRender {

   public static void init(){
       ClientRegistry.bindTileEntityRenderer(OccultBlocks.CRUCIBLE.getTileEntityType(), CrucibleTileRenderer::new);

   }

}
