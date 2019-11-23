package com.minerarcana.occult.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.PaneBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.minerarcana.occult.api.DamageSources.SATED_FIRE;
import static com.minerarcana.occult.content.OccultBlocks.SATED_LIONMETAL_BARS;

public class OccultBars extends PaneBlock {

    public OccultBars(Properties builder) {
        super(builder);
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entity) {
        if(this.equals(SATED_LIONMETAL_BARS.get())) {
            entity.attackEntityFrom(SATED_FIRE, 3);
        }
    }
}
