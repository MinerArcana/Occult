package com.minerarcana.occult.common.biomes.features;


import com.minerarcana.occult.common.biomes.features.bushes.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.MOD)
public class SpookyFeatures
{


    public static Feature<NoFeatureConfig> deepgrassfeature;
    public static Feature<NoFeatureConfig> echobushfeature;
    public static Feature<NoFeatureConfig> phantombushfeature;
    public static Feature<NoFeatureConfig> ivyfeature;
    public static Feature<NoFeatureConfig> eldritchstonefeature;

    @SubscribeEvent
    public static void registerFeature(RegistryEvent.Register<Feature<?>> event) {

        ivyfeature = registerFeature("ivyfeature", new IvyFeature(NoFeatureConfig::deserialize));
        eldritchstonefeature = registerFeature("eldritchstonefeature", new EldritchStoneFeature(NoFeatureConfig::deserialize));
        phantombushfeature = registerFeature("phantombushfeature", new PhantomBushFeature(NoFeatureConfig::deserialize));
        echobushfeature = registerFeature("echobushfeature", new EchoBushFeature(NoFeatureConfig::deserialize));
        deepgrassfeature = registerFeature("deepgrassfeature", new DeepGrassFeature(NoFeatureConfig::deserialize));

    }


    private static<C extends IFeatureConfig, F extends Feature<C>> F registerFeature(String name, F feature)
    {
        return (F)(Registry.<Feature<?>>register(Registry.FEATURE, name, feature));

    }
}
