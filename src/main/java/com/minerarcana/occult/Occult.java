package com.minerarcana.occult;

import com.minerarcana.occult.api.capabilities.PressureChunkStorage;
import com.minerarcana.occult.api.capabilities.handlers.SerializableCapabilityProvider;
import com.minerarcana.occult.api.pressure.IPressure;
import com.minerarcana.occult.api.pressure.PressureType;
import com.minerarcana.occult.util.network.OccultNetwork;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

import static com.minerarcana.occult.api.capabilities.ChunkPressureCapability.*;
import static com.minerarcana.occult.content.Blocks.CINNABARORE;
import static com.minerarcana.occult.content.Blocks.SALTORE;
import static com.minerarcana.occult.util.OccultStaticHelperMethods.*;


@Mod("occult")
public class Occult {

    public static final String MOD_ID = "occult";
    public static Occult instance;

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final SimpleChannel network = OccultNetwork.getNetworkChannel();
    private static PressureType PressureType;


    public Occult() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);


    }

        private void setup(final FMLCommonSetupEvent event) {
            //Overworld Features
            registerOreOverworld(CINNABARORE.get(),1, 1, 0, 24);

            //SpecificBiomeFeature
            registerOreSpecificBiome(SALTORE.get(), Biome.Category.OCEAN,12, 20, 0, 80);


            CapabilityManager.INSTANCE.register(IPressure.class, new Capability.IStorage<IPressure>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<IPressure> capability, IPressure instance, Direction side) {
                return new IntNBT(instance.getPressure());
            }

            @Override
            public void readNBT(Capability<IPressure> capability, IPressure instance, Direction side, INBT nbt) {
                if (!(instance instanceof PressureChunkStorage))
                    throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
                ((PressureChunkStorage) instance).setPressure(((IntNBT) nbt).getInt(), PressureType);
            }
        }, () -> null);
    }

    @SubscribeEvent
    public static void attachChunkCapabilities(final AttachCapabilitiesEvent<Chunk> event) {
        final Chunk chunk = event.getObject();
        final IPressure chunkPressure = new PressureChunkStorage(DEFAULT_CAPACITY, PressureType, chunk.getWorld(), chunk.getPos());
        event.addCapability(new ResourceLocation(MOD_ID, "chunkpressure"), new SerializableCapabilityProvider<>(CHUNKPRESSURECAPABILITY, DEFAULT_FACING, chunkPressure));

    }
}
