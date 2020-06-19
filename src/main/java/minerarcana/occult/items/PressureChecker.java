package minerarcana.occult.items;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import minerarcana.occult.Occult;
import minerarcana.occult.api.PressureType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.Objects;

import static minerarcana.occult.api.chunkpressure.ChunkPressureCap.getAllPressureFromChunk;

public class PressureChecker extends Item {

    public PressureChecker() {
        super(new Item.Properties().group(Occult.OG).maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand handIn) {
        if(!world.isRemote) {
            Object2IntMap<PressureType> chunkPressure = getAllPressureFromChunk(world.getChunk(player.chunkCoordX, player.chunkCoordZ));
            for(PressureType pressure: chunkPressure.keySet()){
                player.sendMessage(new StringTextComponent(Objects.requireNonNull(pressure.getRegistryName()).getPath() + chunkPressure.get(pressure)));
            }
            return ActionResult.resultSuccess(player.getHeldItem(handIn));
        }
        return ActionResult.resultPass(player.getHeldItem(handIn));
    }
}
