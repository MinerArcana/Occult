package com.minerarcana.occult.blocks;

import com.minerarcana.occult.blocks.tileentity.EldritchStoneTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import static com.minerarcana.occult.util.lib.OccultHolderLib.ELDRITCH_TEXTURE;

public class EldritchStone extends Block {

    public EldritchStone(Properties properties) {
        super(properties);
        setRegistryName(ELDRITCH_TEXTURE);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new EldritchStoneTile();
    }

}





