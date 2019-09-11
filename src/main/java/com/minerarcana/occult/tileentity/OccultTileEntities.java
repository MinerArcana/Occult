package com.minerarcana.occult.tileentity;

import com.minerarcana.occult.blocks.featureblocks.eldritchstone.EldritchStone;
import com.minerarcana.occult.blocks.featureblocks.eldritchstone.EldritchStoneTileEntity;
import com.minerarcana.occult.tileentity.ritualfire.RitualBaseTileEntity;
import com.minerarcana.occult.tileentity.ritualfire.RitualBase;
import com.minerarcana.occult.tileentity.ritualfire.RitualFire;
import com.minerarcana.occult.tileentity.ritualfire.RitualFireTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class OccultTileEntities
{
    @ObjectHolder("occult:ritualbase")
    public static RitualBase RITUALBASE;

    @ObjectHolder("occult:ritualbase")
    public static TileEntityType<RitualBaseTileEntity> RITUALBASETILE;

    @ObjectHolder("occult:ritualfire")
    public static RitualFire RITUALFIRE;

    @ObjectHolder("occult:ritualbase")
    public static TileEntityType<RitualFireTileEntity> RITUALFIRETILE;

    @ObjectHolder("occult:eldritchstone")
    public static EldritchStone ELDRITCHSTONE;

    @ObjectHolder("occult:eldritchstone")
    public static TileEntityType<EldritchStoneTileEntity> ELDRITCHSTONETILEENTITY;






}
