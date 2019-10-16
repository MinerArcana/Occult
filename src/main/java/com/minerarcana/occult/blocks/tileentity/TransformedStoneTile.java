package com.minerarcana.occult.blocks.tileentity;

import com.minerarcana.occult.blocks.EldritchStone;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

import static com.minerarcana.occult.util.lib.OccultNameLib.TRANSFORMEDSTONETILEENTITY;
import static com.minerarcana.occult.util.lib.OccultNameLib.transformedstone;


public class TransformedStoneTile extends TileEntity implements ITickableTileEntity {

    public TransformedStoneTile() {
        super(TRANSFORMEDSTONETILEENTITY);
    }


    @Override
    public void tick() {
        BlockState teBlockAbove = world.getBlockState(pos.up());
        BlockState teBlockAbove2 = world.getBlockState(pos.up(2));

        if (teBlockAbove.getBlock() instanceof EldritchStone && teBlockAbove2.getBlock() instanceof EldritchStone) {
            world.setBlockState(pos, transformedstone.getDefaultState());
            boolean b = world.setBlockState(pos.up(2), Blocks.AIR.getDefaultState());
        }
    }
}
