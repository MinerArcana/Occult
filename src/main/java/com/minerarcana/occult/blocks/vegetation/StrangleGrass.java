package com.minerarcana.occult.blocks.vegetation;

import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StrangleGrass extends BushBlock
{


    public StrangleGrass(Properties properties) {
        super(properties);

    }

    @Override
    @Deprecated
    public void onEntityCollision(BlockState state, World p_196262_2_, BlockPos p_196262_3_, Entity entity) {
        entity.attackEntityFrom(DamageSource.SWEET_BERRY_BUSH, 1.0F);
    }

}
