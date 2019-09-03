package com.minerarcana.occult.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class RitualFire extends Block {

    public RitualFire(Properties properties) {
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
        return new FireRitualTileEntity();
    }



}
