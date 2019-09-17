package com.minerarcana.occult.tileentity;

import com.minerarcana.occult.blocks.featureblocks.eldritchstone.EldritchStone;
import com.minerarcana.occult.blocks.featureblocks.eldritchstone.EldritchStoneTileEntity;
import com.minerarcana.occult.tileentity.crucible.CrucibleBlock;
import com.minerarcana.occult.tileentity.crucible.CrucibleTile;
import com.minerarcana.occult.tileentity.ritualfire.*;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

import static com.minerarcana.occult.Occult.MOD_ID;

@ObjectHolder(MOD_ID)
public class OccultTileEntities
{
    @ObjectHolder("occult:ritualbase")
    public static RitualBase RITUALBASE;

    @ObjectHolder("occult:ritualbase")
    public static TileEntityType<RitualBaseTile> RITUALBASETILE;

    @ObjectHolder("occult:ritualbase")
    public static ContainerType<RitualBaseContainer> RITUALBASECONTAINER;

    @ObjectHolder("occult:ritualfire")
    public static RitualFire RITUALFIRE;

    @ObjectHolder("occult:ritualbase")
    public static TileEntityType<RitualFireTile> RITUALFIRETILE;

    @ObjectHolder("occult:eldritchstone")
    public static EldritchStone ELDRITCHSTONE;

    @ObjectHolder("occult:eldritchstone")
    public static TileEntityType<EldritchStoneTileEntity> ELDRITCHSTONETILEENTITY;

    @ObjectHolder("occult:crucible")
    public static CrucibleBlock CRUCIBLE;

    @ObjectHolder("occult:crucible")
    public static TileEntityType<CrucibleTile> CRUCIBLETILE;




}
