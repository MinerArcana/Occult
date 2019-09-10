package com.minerarcana.occult.blocks.featureblocks.eldritchstone;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

public class EldritchStone extends Block {

    public EldritchStone(Properties properties) {
        super(properties);
    }


    private void stoneGrowth(BlockPos pos, IPressure pressure)
    {
        BlockPos up = pos.up();
        IPressure stored = pressure.getPressure("natural", pressure);
        if(up == Blocks.AIR.getDefaultState() && )

    }




}
