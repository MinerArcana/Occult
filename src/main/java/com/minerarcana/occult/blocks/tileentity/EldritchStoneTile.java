package com.minerarcana.occult.blocks.tileentity;

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
        setEldritchResourceLocation();
        spawnEldritchMidnight();
    }


    public void spawnEldritchMidnight() {
        //if time is a multiple of midnight, set block above to Eldritch Stone
        //TODO seperate Blockstate for Eldritch Stone above?
        long midnightMultiple = world.getDayTime() - 210000;
        for (int i = 0; i < 1; i++) {
            if (world.getDayTime() == 18000 || midnightMultiple % 216000 == 0) {
                if (world.getBlockState(pos.up()).isAir()) {
                    world.setBlockState(pos.up(), getBlockState());
                }
            }
        }
    }

    public ResourceLocation setEldritchResourceLocation() {
        if (world.getBlockState(pos.down(1)) == this.getBlockState() && world.getBlockState(pos.down(2)) == this.getBlockState()) {
            ELDRITCH_TEXTURE = ELDRITCH_TOP_VARIENT;
        } else if (world.getBlockState(pos.down(1)) == this.getBlockState()) {
            ELDRITCH_TEXTURE = ELDRITCH_MIDDLE_VARIENT;
        } else {
            ELDRITCH_TEXTURE = ELDRITCH_BOTTOM_VARIENT;
        }

        return ELDRITCH_TEXTURE;
    }
}
