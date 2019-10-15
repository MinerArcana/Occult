package com.minerarcana.occult.blocks.vegetation;

import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

import static com.minerarcana.occult.util.lib.OccultNameLib.*;

public class EchocryBushes extends BushBlock {


    public EchocryBushes(Properties properties)
    {
        super(properties);
    }

    @Deprecated
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        if(!worldIn.isRemote);
        if (!worldIn.isAreaLoaded(pos, 3));
        if(worldIn.getDayTime()>= 13000)
        {
            worldIn.setBlockState(pos, echobush1.getDefaultState());
        }
        else{
            worldIn.setBlockState(pos, echobush2.getDefaultState());
        }
    }

}
