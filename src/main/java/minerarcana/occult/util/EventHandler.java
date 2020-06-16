package minerarcana.occult.util;

import minerarcana.occult.api.PressureStorageProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static minerarcana.occult.Occult.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    @SubscribeEvent
    public static void attachCapToChunk(AttachCapabilitiesEvent<Chunk> event) {
        event.addCapability(new ResourceLocation(MOD_ID, "pressure"), new PressureStorageProvider());
    }
    @SubscribeEvent
    public static void attachCapToTileEntity(AttachCapabilitiesEvent<TileEntity> event) {
        //event.addCapability(new ResourceLocation(MOD_ID, "pressure"), new PressureStorageProvider());
    }

}
