package com.minerarcana.occult.tileentity;

import com.minerarcana.occult.tileentity.ritualfire.RitualBaseTileEntity;
import com.minerarcana.occult.tileentity.ritualfire.RitualBase;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class OccultTileEntities
{
    @ObjectHolder("occult:ritualbase")
    public static RitualBase RITUALBASE;

    @ObjectHolder("occult:ritualbase")
    public static TileEntityType<RitualBaseTileEntity> RITUALBASETILE;






}
