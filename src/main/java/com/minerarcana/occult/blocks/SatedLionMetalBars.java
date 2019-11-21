package com.minerarcana.occult.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.PaneBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.minerarcana.occult.api.DamageSources.SATED_FIRE;

public class SatedLionMetalBars extends PaneBlock {

    public SatedLionMetalBars(Properties builder) {
        super(builder.lightValue(12));
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entity) {
        entity.attackEntityFrom(SATED_FIRE, 3);
    }
}
