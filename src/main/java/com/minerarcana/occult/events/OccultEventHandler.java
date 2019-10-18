package com.minerarcana.occult.events;

import com.minerarcana.occult.util.lib.OccultNameLib;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

import static com.minerarcana.occult.Occult.MOD_ID;
import static com.minerarcana.occult.events.OccultStaticHelperMethods.*;


@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OccultEventHandler {

    @SubscribeEvent
    public static void onFeatureRegistryEvent(RegistryEvent.Register<Feature<?>> event) {
        //Overworld Ores
        registerOreOverworld(OccultNameLib.cinnabarore,1, 1, 0, 24);

        //Specific Biome Ores
        registerOreSpecificBiome(OccultNameLib.saltore, Biome.Category.OCEAN,12, 20, 0, 80);


        //Biome Features
        registerFeatureSpecificBiome(Biome.Category.SWAMP, OccultNameLib.stranglegrassfeature, Placement.CHANCE_HEIGHTMAP_DOUBLE, IFeatureConfig.NO_FEATURE_CONFIG,1);
        registerFeatureSpecificBiome(Biome.Category.NETHER, OccultNameLib.netherstranglegrassfeature, Placement.CHANCE_HEIGHTMAP_DOUBLE, IFeatureConfig.NO_FEATURE_CONFIG,1);

    }


}
