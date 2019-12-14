package com.minerarcana.occult;

import com.minerarcana.occult.api.pressure.IPressure;
import com.minerarcana.occult.api.pressure.PressureStorage;
import com.minerarcana.occult.api.pressure.PressureStorageProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;


@Mod("occult")
public class Occult {

    public static final String MOD_ID = "occult";
    public static Occult instance;

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);


    public Occult() {

        IEventBus bus =  FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.addGenericListener(Chunk.class, Occult::attachChunkCapabilities);

        MinecraftForge.EVENT_BUS.register(this);
        PressureRegistryEvent.register(bus);

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


    @SubscribeEvent
    public static void attachChunkCapabilities(final AttachCapabilitiesEvent<Chunk> event) {
        event.addCapability(new ResourceLocation(MOD_ID, "pressure"), new PressureStorageProvider());
    }
}
