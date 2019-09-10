package com.minerarcana.occult;

import com.minerarcana.occult.api.pressure.IPressure;
import com.minerarcana.occult.capabilities.PressureChunkStorage;
import com.minerarcana.occult.capabilities.handlers.SerializableCapabilityProvider;
import com.minerarcana.occult.proxy.ClientProxy;
import com.minerarcana.occult.proxy.CommonProxy;
import com.minerarcana.occult.util.network.OccultNetwork;
import com.minerarcana.occult.world.SpookyWorldType;
import com.minerarcana.occult.world.chunk.SpookyChunkGeneratorType;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
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
import java.util.stream.Collectors;

import static com.minerarcana.occult.capabilities.ChunkPressureCapability.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("occult")
public class Occult {

    public static final String MOD_ID = "occult";
    public static Occult instance;

    public static CommonProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static WorldType SpookyWorldType = new SpookyWorldType("occulttype");
    private static SpookyChunkGeneratorType chunkGeneratorType = new SpookyChunkGeneratorType();
    public static final SimpleChannel network = OccultNetwork.getNetworkChannel();


    public Occult() {


        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);


    }

    private void setup(final FMLCommonSetupEvent event) {
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
                ((PressureChunkStorage) instance).setPressure(((IntNBT) nbt).getInt(), type);
            }
        }, () -> null);
    }


    private void doClientStuff(final FMLClientSetupEvent event) {

        LOGGER.info("Client Registering stuff");
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public static void attachChunkCapabilities(final AttachCapabilitiesEvent<Chunk> event) {
        final Chunk chunk = event.getObject();
        final IPressure chunkPressure = new PressureChunkStorage(DEFAULT_CAPACITY, type, chunk.getWorld(), chunk.getPos());
        event.addCapability(registryName, new SerializableCapabilityProvider<>(CHUNKPRESSURECAPABILITY, DEFAULT_FACING, chunkPressure));

    }
}
