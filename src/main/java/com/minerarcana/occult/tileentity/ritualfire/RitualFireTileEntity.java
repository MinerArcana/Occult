package com.minerarcana.occult.tileentity.ritualfire;

import com.minerarcana.occult.tileentity.tileinventory.BaseTileInventory;

import static com.minerarcana.occult.tileentity.OccultTileEntities.RITUALTILE;

public class RitualFireTileEntity extends BaseTileInventory {

    public RitualFireTileEntity() {
        super(RITUALTILE);
    }




    public boolean isActive(boolean active)
    {
        return false;
    }


    @Override
    public int getSizeInventory() {
        return 32;
    }
}
