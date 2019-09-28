package com.minerarcana.occult.biomes.surfaces;

import com.minerarcana.occult.blocks.OccultBlocks;
import com.minerarcana.occult.items.OccultItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class OccultSurfaces {

    public static SurfaceBuilderConfig DEEPSPOOKYSURFACE ;


    public OccultSurfaces(){}
    static
    {
        DEEPSPOOKYSURFACE = new SurfaceBuilderConfig(OccultBlocks.deepgrass.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState());

    }




}
