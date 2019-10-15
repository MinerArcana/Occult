package com.minerarcana.occult.world.biome.surfaces;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import static com.minerarcana.occult.util.lib.OccultNameLib.deepgrass;

public class OccultSurfaces {

    public static SurfaceBuilderConfig DEEPSPOOKYSURFACE ;


    public OccultSurfaces(){}
    static
    {
        DEEPSPOOKYSURFACE = new SurfaceBuilderConfig(deepgrass.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState());

    }




}
