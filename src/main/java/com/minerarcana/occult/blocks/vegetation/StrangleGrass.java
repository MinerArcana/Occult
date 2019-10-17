package com.minerarcana.occult.blocks.vegetation;


import com.minerarcana.occult.util.damage.StrangeDamage;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

import static com.minerarcana.occult.util.lib.OccultNameLib.*;


public class StrangleGrass extends BushBlock
{

    public StrangleGrass(Properties properties) {
        super(properties);

    }

    @Override
    @Deprecated
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.attackEntityFrom(StrangeDamage.STRANGEGRASS, 1.0F);
    }

    public static void onStrangleDeath(BlockPos pos, World world, Random random) {

            BlockPos blockpos = pos.add(random.nextInt(10) - 1, random.nextInt(10) - 3, random.nextInt(10) - 1);
            if(world.getBlockState(blockpos) == Blocks.AIR.getDefaultState() && world.getBlockState(blockpos.down()) != rockytrails.getDefaultState() && world.getBlockState(blockpos.down()) != Blocks.AIR.getDefaultState()) {
                world.setBlockState(blockpos, stranglegrass.getDefaultState());
            }
        }

}

