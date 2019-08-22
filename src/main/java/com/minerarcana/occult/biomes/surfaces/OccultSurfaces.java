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


    public BlockState setGrassState(World world, BlockPos pos)
    {
        BlockPos blockpos = pos.up();
        BlockState state;
        if(world.getBlockState(pos.up()).getBlock() == OccultBlocks.halfgrass )
        {
            state = Blocks.DIRT.getDefaultState();
        }
        else{
            state = OccultBlocks.halfgrass.getDefaultState();
        }
        return state;
    }

    public OccultSurfaces(){}
    static
    {
        DEEPSPOOKYSURFACE = new SurfaceBuilderConfig(OccultBlocks.deepgrass.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState());

    }




}
