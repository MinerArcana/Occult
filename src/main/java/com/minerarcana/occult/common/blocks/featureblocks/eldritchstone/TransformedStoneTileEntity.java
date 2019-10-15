package com.minerarcana.occult.common.blocks.featureblocks.eldritchstone;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.minerarcana.occult.common.tileentity.OccultTileEntities.TRANSFORMEDSTONE;
import static com.minerarcana.occult.common.tileentity.OccultTileEntities.TRANSFORMEDSTONETILEENTITY;

public class TransformedStoneTileEntity extends TileEntity {

    public TransformedStoneTileEntity() {
        super(TRANSFORMEDSTONETILEENTITY);
    }

    public void TransformEldritchStone() {
        BlockState teBlockAbove = world.getBlockState(pos.up());
        BlockState teBlockAbove2 = world.getBlockState(pos.up(2));

        if (teBlockAbove.getBlock() instanceof  EldritchStone && teBlockAbove2.getBlock() instanceof EldritchStone) {
                world.setBlockState(pos, TRANSFORMEDSTONE.getDefaultState());
                world.setBlockState(pos.up(), Blocks.AIR.getDefaultState());
                world.setBlockState(pos.up(2), Blocks.AIR.getDefaultState());
        }
    }

}
