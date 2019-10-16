package com.minerarcana.occult.blocks.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import static com.minerarcana.occult.util.lib.OccultHolderLib.*;
import static com.minerarcana.occult.util.lib.OccultNameLib.ELDRITCHSTONETILEENTITY;

public class EldritchStoneTile extends TileEntity implements ITickableTileEntity {

    public EldritchStoneTile() {
        super(ELDRITCHSTONETILEENTITY);
    }

    @Override
    public void tick() {
        spawnEldritchMidnight();
    }


    public void spawnEldritchMidnight() {
        //if time is a multiple of midnight, set block above to Eldritch Stone
        //TODO seperate Blockstate for Eldritch Stone above?
        long midnightMultiple = world.getDayTime() - 210000;
        for (int i = 0; i < 1; i++) {
            if (world.getDayTime() == 18000 || midnightMultiple % 216000 == 0) {
                BlockState blockstate = this.getBlockState();
                if (world.getBlockState(pos.up()).isAir() && world.getBlockState(pos.down()) != this.getBlockState()) {
                    world.setBlockState(pos.up(), blockstate.with(MIDDLE, Boolean.valueOf(world.getBlockState(pos.down()) != this.getBlockState())));
                }
                else if (world.getBlockState(pos.up()).isAir() && world.getBlockState(pos.down()) == this.getBlockState()) {
                    world.setBlockState(pos.up(), blockstate.with(TOP, Boolean.valueOf(world.getBlockState(pos.down()) != this.getBlockState())));
                }
            }
        }
    }


}
