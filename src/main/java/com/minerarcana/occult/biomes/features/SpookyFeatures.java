package com.minerarcana.occult.biomes.features;


import com.minerarcana.occult.biomes.features.bushes.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



public class SpookyFeatures
{


    public static final Feature<NoFeatureConfig> deepgrassfeature = registerFeature("deepgrassfeature", new DeepGrassFeature(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> echobushfeature = registerFeature("echobushfeature", new EchoBushFeature(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> phantombushfeature = registerFeature("phantombushfeature", new PhantomBushFeature(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> ivyfeature = registerFeature("ivyfeature", new IvyFeature(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> eldritchstonefeature = registerFeature("eldritchstonefeature", new EldritchStoneFeature(NoFeatureConfig::deserialize));


    private static<C extends IFeatureConfig, F extends Feature<C>> F registerFeature(String name, F feature)
    {
        return (F)(Registry.<Feature<?>>register(Registry.FEATURE, name, feature));

    }
}
