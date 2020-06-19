package minerarcana.occult.util;

import minerarcana.occult.api.chunkpressure.ChunkPressureStorageProvider;
import minerarcana.occult.api.worldpressure.WorldPressureStorageProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static minerarcana.occult.Occult.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventHandler {

    @SubscribeEvent
    public static void attachCapToChunk(AttachCapabilitiesEvent<Chunk> event) {
        event.addCapability(new ResourceLocation(MOD_ID, "pressure_chunk"), new ChunkPressureStorageProvider(event.getObject()));
    }
    @SubscribeEvent
    public static void attachCapToWorld(AttachCapabilitiesEvent<World> event) {
        event.addCapability(new ResourceLocation(MOD_ID, "pressure_world"), new WorldPressureStorageProvider());
    }
    @SubscribeEvent
    public static void attachCapToTileEntity(AttachCapabilitiesEvent<TileEntity> event) {
        //event.addCapability(new ResourceLocation(MOD_ID, "pressure"), new ChunkPressureStorageProvider());
    }

}
