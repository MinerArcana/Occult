package com.minerarcana.occult;

import com.minerarcana.occult.api.PressureType;
import com.minerarcana.occult.api.capabilities.handlers.SerializableCapabilityProvider;
import com.minerarcana.occult.api.pressure.ChunkPressureStorage;
import com.minerarcana.occult.api.pressure.IPressure;
import com.minerarcana.occult.api.pressure.PressureStorage;
import com.minerarcana.occult.util.OccultConfigHandler;
import com.minerarcana.occult.util.network.OccultNetwork;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;



@Mod("occult")
public class Occult {

    public static final String MOD_ID = "occult";
    public static Occult instance;

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final SimpleChannel network = OccultNetwork.getNetworkChannel();
    private static PressureType pressureType;


    public Occult() {

        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, OccultConfigHandler.CLIENT_SPEC);
        modLoadingContext.registerConfig(ModConfig.Type.COMMON, OccultConfigHandler.COMMON_SPEC);


        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.addGenericListener(Chunk.class, Occult::attachChunkCapabilities);

        MinecraftForge.EVENT_BUS.register(this);


    }

        private void setup(final FMLCommonSetupEvent event) {

            CapabilityManager.INSTANCE.register(IPressure.class, new Capability.IStorage<IPressure>() {
                @Nullable
                @Override
                public INBT writeNBT(Capability<IPressure> capability, IPressure instance, Direction side) {
                    return instance.serializeNBT();
                }

                @Override
                public void readNBT(Capability<IPressure> capability, IPressure instance, Direction side, INBT nbt) {
                    instance.deserializeNBT((CompoundNBT) nbt);
                }
            }, PressureStorage::new);
    }


    private void doClientStuff(final FMLClientSetupEvent event) {


    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void processIMC(final InterModProcessEvent event) {

    }


    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }

    @SubscribeEvent
    public static void attachChunkCapabilities(final AttachCapabilitiesEvent<Chunk> event) {
        event.addCapability(new ResourceLocation(MOD_ID, "chunkpressure"), new ChunkPressureStorage(1000));
    }
}
