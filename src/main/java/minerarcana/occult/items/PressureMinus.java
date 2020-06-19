package minerarcana.occult.items;

import minerarcana.occult.Occult;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import static minerarcana.occult.api.chunkpressure.ChunkPressureCap.removeChunkPressure;
import static minerarcana.occult.content.OccultPressure.*;

public class PressureMinus extends Item {

    public PressureMinus() {
        super(new Item.Properties().group(Occult.OG).maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand handIn) {
        if (!world.isRemote) {
            removeChunkPressure(world.getChunk(player.chunkCoordX, player.chunkCoordZ), SPIRITUAL.get(), 1);
            removeChunkPressure(world.getChunk(player.chunkCoordX, player.chunkCoordZ), INFERNAL.get(), 1);
            removeChunkPressure(world.getChunk(player.chunkCoordX, player.chunkCoordZ), NATURAL.get(), 1);
            return ActionResult.resultSuccess(player.getHeldItem(handIn));
        }
        return ActionResult.resultPass(player.getHeldItem(handIn));
    }
}
