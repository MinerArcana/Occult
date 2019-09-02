package com.minerarcana.occult.blocks.vegetation;

import com.minerarcana.occult.Occult;
import com.minerarcana.occult.blocks.OccultBlocks;
import com.minerarcana.occult.items.OccultItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

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
            worldIn.setBlockState(pos, OccultBlocks.echobush1.getDefaultState());
        }
        else{
            worldIn.setBlockState(pos, OccultBlocks.echobush2.getDefaultState());
        }
    }

}
