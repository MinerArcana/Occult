package com.minerarcana.occult.blocks;

import com.minerarcana.occult.blocks.tileentity.TransformedStoneTileEntity;
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
        return new TransformedStoneTileEntity();
    }

}
