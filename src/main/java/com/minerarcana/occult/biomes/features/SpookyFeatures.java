package com.minerarcana.occult.biomes.features;


import com.minerarcana.occult.biomes.features.bushes.DeepGrassFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



public class SpookyFeatures
{


    public static final Feature<NoFeatureConfig> deepgrassfeature = registerFeature("deepgrassfeature", new DeepGrassFeature(NoFeatureConfig::deserialize));


    private static<C extends IFeatureConfig, F extends Feature<C>> F registerFeature(String name, F feature)
    {
        return (F)(Registry.<Feature<?>>register(Registry.FEATURE, name, feature));

    }
}
