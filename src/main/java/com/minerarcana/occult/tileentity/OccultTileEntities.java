package com.minerarcana.occult.tileentity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class OccultTileEntities
{
    @ObjectHolder("occult:ritualfire")
    public static RitualFire RITUALFIRE;

    @ObjectHolder("occult:ritualfire")
    public static TileEntityType<FireRitualTileEntity> RITUAL_TILE;


}
