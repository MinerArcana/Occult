package com.minerarcana.occult.blocks.tileentity;

import com.minerarcana.occult.util.lib.OccultTagLib;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;


import static com.minerarcana.occult.util.lib.OccultNameLib.ELDRITCHSTONETILEENTITY;
import static com.minerarcana.occult.util.lib.OccultPropertyLib.*;
import static com.minerarcana.occult.util.lib.OccultTagLib.Blocks.ELDRITCH;

public class EldritchStoneTile extends TileEntity implements ITickableTileEntity {

    public EldritchStoneTile() {
        super(ELDRITCHSTONETILEENTITY);
    }

    @Override
    public void tick() {
        spawnEldritchMidnight();
        eldritchSelfDestruct();
    }

    @Deprecated
    public void spawnEldritchMidnight() {
        //if time is a multiple of midnight, set block above to Eldritch Stone
        //TODO seperate Blockstate for Eldritch Stone above?
        long midnightMultiple = world.getDayTime() - 210000;
        for (int i = 0; i < 1; i++) {
            if (world.getDayTime() == 18000 || midnightMultiple % 216000 == 0) {
                BlockState blockstate = this.getBlockState();
                if (world.getBlockState(pos.down()).isIn(ELDRITCH) && world.getBlockState(pos.down(2)).isIn(ELDRITCH) && world.getBlockState(pos).isIn(ELDRITCH)) {
                    break;
                }
                else if (world.getBlockState(pos.up()).isAir() && !world.getBlockState(pos.down()).isIn(ELDRITCH)) {
                    world.setBlockState(pos.up(), blockstate.with(MIDDLE, world.getBlockState(pos.down()).isIn(ELDRITCH)));
                }
                else if (world.getBlockState(pos.up()).isAir() && world.getBlockState(pos.down()).isIn(ELDRITCH)) {
                    world.setBlockState(pos.up(), blockstate.with(TOP, world.getBlockState(pos.down()).isIn(ELDRITCH)));
                }

            }
        }
    }

    public void eldritchSelfDestruct(){
        if (world.getBlockState(pos.down()).isIn(ELDRITCH) && world.getBlockState(pos.down(2)).isIn(ELDRITCH)
                && world.getBlockState(pos.down(3)).isIn(ELDRITCH)) {
            world.destroyBlock(pos, true);
        }

    }

}
