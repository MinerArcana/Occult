package com.minerarcana.occult.blocks.tileentity;

import com.google.common.collect.Maps;
import com.minerarcana.occult.api.pressure.PressureType;
import net.minecraft.tileentity.TileEntity;

import java.util.Map;


import static com.minerarcana.occult.util.lib.OccultNameLib.ELDRITCHSTONETILEENTITY;

public class EldritchStoneTileEntity extends TileEntity {


    public final Map<PressureType, Integer> pressures;

    public EldritchStoneTileEntity() {
        super(ELDRITCHSTONETILEENTITY);
        this.pressures = Maps.newHashMap();
    }


}
