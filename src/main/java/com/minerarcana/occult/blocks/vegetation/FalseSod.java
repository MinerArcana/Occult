package com.minerarcana.occult.blocks.vegetation;


import com.minerarcana.occult.util.lib.OccultTagLib;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class FalseSod extends Block {

    public FalseSod(Properties properties) {
        super(properties);
    }

    @Override
    public void onEntityWalk(World world, BlockPos pos, Entity entity) {
        falseSodDestruction(world, pos, entity);
        super.onEntityWalk(world, pos, entity);
    }

    public void falseSodDestruction(World world, BlockPos pos, Entity entity) {
        if (entity instanceof PlayerEntity) {
            Random rand = world.rand;
            int timer = rand.nextInt(100);
            if (timer == 0) {
                for (int i = -20; i < 20; i++) {
                    for (int k = -4; k < 4; k++) {
                        for (int j = -20; j < 20; j++) {
                            BlockPos possibletarget = pos.add(i, k, j);
                            BlockState getState = world.getBlockState(possibletarget);
                            if (getState.isIn(OccultTagLib.Blocks.FALSESOD)) {
                                for (int w = -15; w < 1; w++) {
                                    BlockPos target = possibletarget.add(0, w, 0);
                                    world.destroyBlock(target, false);
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}
