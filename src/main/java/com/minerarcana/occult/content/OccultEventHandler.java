package com.minerarcana.occult.content;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.minerarcana.occult.content.Blocks.CINNABARORE;
import static com.minerarcana.occult.content.Blocks.SALTORE;
import static com.minerarcana.occult.util.OccultStaticHelperMethods.*;


@Mod.EventBusSubscriber
public class OccultEventHandler {

    @SubscribeEvent
    public static void onFeatureRegistryEvent(RegistryEvent.Register<Feature<?>> event) {
        //Overworld Ores
        registerOreOverworld(CINNABARORE.get(),1, 1, 0, 24);

        //Specific Biome Ores
        registerOreSpecificBiome(SALTORE.get(), Biome.Category.OCEAN,12, 20, 0, 80);

    }

}
