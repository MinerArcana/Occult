package com.minerarcana.occult.common.biomes.surfaces;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import static com.minerarcana.occult.update.util.lib.OccultNameLib.deepgrass;

public class OccultSurfaces {

    public static SurfaceBuilderConfig DEEPSPOOKYSURFACE ;


    public OccultSurfaces(){}
    static
    {
        DEEPSPOOKYSURFACE = new SurfaceBuilderConfig(deepgrass.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState());

    }




}
