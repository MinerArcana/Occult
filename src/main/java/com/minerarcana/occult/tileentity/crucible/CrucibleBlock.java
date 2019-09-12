package com.minerarcana.occult.tileentity.crucible;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class CrucibleBlock extends Block {

    public CrucibleBlock(Properties properties) {
        super(properties);
    }

    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new CrucibleTile();
    }





}
