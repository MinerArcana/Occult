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
    public void tick(BlockState state, World world, BlockPos pos, Random random) {
        echoNightTransformation(world,pos);
    }

    public boolean isNight(World world){
        long n = world.getDayTime();
        long a = (n / 13000) * 13000;
        long b = a + 13000;
        long a1 = (n / 22500) * 22500;
        long b1 = a1 + 22500;
        return n > b1 && n < b;
    }

    public void echoNightTransformation(World world, BlockPos pos){
        if(!world.isRemote);
        if (!world.isAreaLoaded(pos, 3));
        if(isNight(world))
        {
            world.setBlockState(pos, echobush1.getDefaultState());
        }
        else{
            world.setBlockState(pos, echobush2.getDefaultState());
        }
    }


}
