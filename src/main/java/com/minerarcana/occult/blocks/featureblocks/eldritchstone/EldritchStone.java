package com.minerarcana.occult.blocks.featureblocks.eldritchstone;

import com.minerarcana.occult.api.pressure.IPressure;
import com.minerarcana.occult.blocks.OccultBlocks;
import com.minerarcana.occult.tileentity.ritualfire.RitualBaseTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class EldritchStone extends Block {


    public EldritchStone(Properties properties) {
        super(properties);
    }


    private void stoneGrowth(String name, BlockPos pos, BlockState state, IPressure pressure) {
        /*BlockPos up = pos.up();
        BlockPos down = pos.down(2);
        int stored = pressure.getPressure();
        if (up == Blocks.AIR.getDefaultState() && stored == 25 && down. != OccultBlocks.eldritchstone.getDefaultState()) {

        }*/

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





