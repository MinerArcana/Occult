package com.minerarcana.occult.tileentity.ritualfire;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class RitualBase extends Block {


    public RitualBase(Properties properties) {
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
        return new RitualBaseTile();
    }

    

}
