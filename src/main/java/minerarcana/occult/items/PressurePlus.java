package minerarcana.occult.items;

import minerarcana.occult.Occult;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import static minerarcana.occult.api.chunkpressure.ChunkPressureCap.addChunkPressure;
import static minerarcana.occult.content.OccultPressure.*;

public class PressurePlus extends Item {

    public PressurePlus() {
        super(new Item.Properties().group(Occult.OG).maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand handIn) {
        if (!world.isRemote) {
            addChunkPressure(world.getChunk(player.chunkCoordX,player.chunkCoordZ),SPIRITUAL.get(),2);
            addChunkPressure(world.getChunk(player.chunkCoordX,player.chunkCoordZ),INFERNAL.get(),6);
            addChunkPressure(world.getChunk(player.chunkCoordX,player.chunkCoordZ),NATURAL.get(),9);
            return ActionResult.resultSuccess(player.getHeldItem(handIn));
        }
        return ActionResult.resultPass(player.getHeldItem(handIn));
    }
}
