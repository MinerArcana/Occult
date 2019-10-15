package com.minerarcana.occult.common.blocks.featureblocks.eldritchstone;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class TransformedStone extends Block {

    public TransformedStone(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new EldritchStoneTileEntity();
    }

}
