package com.minerarcana.occult.blocks.vegetation;

import com.minerarcana.occult.util.lib.OccultTagLib;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

import static com.minerarcana.occult.util.lib.OccultNameLib.*;
import static com.minerarcana.occult.util.lib.OccultPropertyLib.MIDDLE;
import static com.minerarcana.occult.util.lib.OccultPropertyLib.TOP;
import static com.minerarcana.occult.util.lib.OccultTagLib.Blocks.ELDRITCH;

public class MovingGrass extends TallGrassBlock {

    public MovingGrass(Properties properties) {
        super(properties);
    }

    @Deprecated
    @Override
    public void tick(BlockState state, World world, BlockPos pos, Random random) {
        eerieMovingGrass(world, pos, random);

        super.tick(state, world, pos, random);
    }

    @Deprecated
    public void eerieMovingGrass(World world, BlockPos pos, Random random) {
        boolean moved = false;
        long midnightMultiple = world.getDayTime() - 210000;

        if (world.getDayTime() == 18000 || midnightMultiple % 216000 == 0) {
            BlockPos blockpos = pos.add(random.nextInt(10) - 1, random.nextInt(10) - 3, random.nextInt(10) - 1);
            if (world.getBlockState(blockpos) == Blocks.AIR.getDefaultState() && world.getBlockState(blockpos.down()) != rockytrails.getDefaultState() && world.getBlockState(blockpos.down()).isIn(OccultTagLib.Blocks.VALIDGROUND)) {
                world.setBlockState(blockpos, movinggrass.getDefaultState());
                moved = true;
                if (moved) {
                    world.destroyBlock(pos, false);
                    moved = false;
                }


            }

        }
    }


}
