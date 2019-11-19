package com.minerarcana.occult.events;

import com.minerarcana.occult.util.lib.OccultNameLib;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.minerarcana.occult.events.OccultStaticHelperMethods.*;


@Mod.EventBusSubscriber
public class OccultEventHandler {

    @SubscribeEvent
    public static void onFeatureRegistryEvent(RegistryEvent.Register<Feature<?>> event) {
        //Overworld Ores
        registerOreOverworld(OccultNameLib.cinnabarore,1, 1, 0, 24);

        //Specific Biome Ores
        registerOreSpecificBiome(OccultNameLib.saltore, Biome.Category.OCEAN,12, 20, 0, 80);


    }

}
