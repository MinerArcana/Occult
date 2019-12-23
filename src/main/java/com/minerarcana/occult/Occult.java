package com.minerarcana.occult;

import com.minerarcana.occult.api.pressure.pressure.IPressure;
import com.minerarcana.occult.api.pressure.pressure.PressureStorage;
import com.minerarcana.occult.content.*;
import com.minerarcana.occult.util.OccultEvents;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

import static com.minerarcana.occult.content.OccultBlocks.CINNABAR_ORE;
import static com.minerarcana.occult.content.OccultBlocks.SALT_ORE;
import static com.minerarcana.occult.util.OccultStaticHelperMethods.*;


@Mod("occult")
public class Occult {

    public static final String MOD_ID = "occult";
    public static Occult instance;

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);


    public Occult() {

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        OccultTileEntity.register(modBus);
        OccultBlocks.register(modBus);
        OccultItems.register(modBus);
        OccultFluids.register(modBus);
        OccultPressure.register(modBus);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(OccultEvents.class);

    }

        private void setup(final FMLCommonSetupEvent event) {
            //Overworld Features
            registerOreOverworld(CINNABAR_ORE.get(),1, 1, 0, 24);

            //SpecificBiomeFeature
            registerOreSpecificBiome(SALT_ORE.get(), Biome.Category.OCEAN,12, 20, 0, 80);


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

}
