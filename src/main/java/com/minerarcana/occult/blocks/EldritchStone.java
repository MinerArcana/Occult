package com.minerarcana.occult.blocks;

import com.minerarcana.occult.blocks.tileentity.EldritchStoneTile;
import com.minerarcana.occult.util.lib.OccultHolderLib;
import com.minerarcana.occult.util.lib.OccultNameLib;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import static com.minerarcana.occult.util.lib.OccultHolderLib.*;

public class EldritchStone extends Block {

    public EldritchStone(Properties properties) {
        super(properties);
        this.setDefaultState((this.stateContainer.getBaseState()).with(BOTTOM, true));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new EldritchStoneTile();
    }

    public BlockState getStateForPlacement(BlockItemUseContext contest) {
        Block block = contest.getWorld().getBlockState(contest.getPos().down()).getBlock();
        return (BlockState) this.getDefaultState().with(BOTTOM, block != this);
    }

    public BlockState updatePostPlacement(BlockState state, Direction direction, BlockState state1, IWorld world, BlockPos pos, BlockPos pos2) {
            Block block = world.getBlockState(pos.down()).getBlock();
            Block block2 = world.getBlockState(pos.down(2)).getBlock();
            if(block == this){
                if(block2 == this){
                    return (BlockState) state.with(TOP, block == OccultNameLib.eldritchstone);
                }
                else{
                    return (BlockState) state.with(MIDDLE, block == OccultNameLib.eldritchstone);
                }

            }
            else{
                return (BlockState) state.with(BOTTOM, block == OccultNameLib.eldritchstone);
            }
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> state) {
        state.add(BOTTOM);
        state.add(MIDDLE);
        state.add(TOP);
    }

    static {
        BOTTOM = BOTTOMPROPERTY;
        MIDDLE = MIDDLEPROPERTY;
        TOP = TOPPROPERTY;
    }

}





