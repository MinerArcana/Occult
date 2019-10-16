package com.minerarcana.occult.blocks;

import com.minerarcana.occult.blocks.tileentity.EldritchStoneTile;
import com.minerarcana.occult.util.lib.OccultTagLib;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import static com.minerarcana.occult.util.lib.OccultPropertyLib.*;
import static com.minerarcana.occult.util.lib.OccultTagLib.Blocks.ELDRITCH;

public class EldritchStone extends Block {

    public EldritchStone(Properties properties) {
        super(properties);
        this.setDefaultState((this.stateContainer.getBaseState()).with(MIDDLE, false));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new EldritchStoneTile();
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState block = context.getWorld().getBlockState(context.getPos().down());
        return (BlockState) this.getDefaultState().with(MIDDLE, !block.isIn(ELDRITCH));
    }

    public BlockState updatePostPlacement(BlockState state, Direction direction, BlockState state1, IWorld world, BlockPos pos, BlockPos pos2) {
        BlockState block = world.getBlockState(pos.down());
        BlockState block2 = world.getBlockState(pos.down(2));
        BlockState block3 = world.getBlockState(pos.down(3));
        if (block.isIn(ELDRITCH) && block2.isIn(ELDRITCH) && block3.isIn(ELDRITCH)) {
            world.destroyBlock(pos, true);
        }
        if (block.isIn(ELDRITCH) && !block3.isIn(ELDRITCH)) {
            if (block2.isIn(ELDRITCH)) {
                return (BlockState) state.with(TOP, block.isIn(ELDRITCH));
            } else {
                return (BlockState) state.with(MIDDLE, block.isIn(ELDRITCH));
            }

        } else {
            return (BlockState) state.with(MIDDLE, !block.isIn(ELDRITCH));
        }
    }


    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> state) {
        state.add(MIDDLE);
        state.add(TOP);
    }

    static {
        MIDDLE = MIDDLEPROPERTY;
        TOP = TOPPROPERTY;
    }

}





