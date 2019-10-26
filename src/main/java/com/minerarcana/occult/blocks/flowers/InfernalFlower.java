package com.minerarcana.occult.blocks.flowers;

import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class InfernalFlower extends BushBlock {

    public InfernalFlower(Properties properties)
    {
        super(properties);
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {



        super.animateTick(state, world, pos, random);
    }
}
