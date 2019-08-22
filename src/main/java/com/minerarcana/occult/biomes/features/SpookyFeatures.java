package com.minerarcana.occult.biomes.features;

import com.minerarcana.occult.Occult;
import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.fml.common.Mod;



public class SpookyFeatures
{




    private static<C extends IFeatureConfig, F extends Feature<C>> F registerFeature(String name, F feature)
    {
        return (F)(Registry.<Feature<?>>register(Registry.FEATURE, name, feature));

    }
}
