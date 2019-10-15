package com.minerarcana.occult.common.tileentity;

import com.minerarcana.occult.common.blocks.featureblocks.eldritchstone.EldritchStone;
import com.minerarcana.occult.common.blocks.featureblocks.eldritchstone.EldritchStoneTileEntity;
import com.minerarcana.occult.common.blocks.featureblocks.eldritchstone.TransformedStone;
import com.minerarcana.occult.common.blocks.featureblocks.eldritchstone.TransformedStoneTileEntity;
import com.minerarcana.occult.common.tileentity.crucible.CrucibleBlock;
import com.minerarcana.occult.common.tileentity.crucible.CrucibleTile;
import com.minerarcana.occult.common.tileentity.ritualfire.*;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

import javax.xml.crypto.dsig.Transform;

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


    @ObjectHolder("occult:eldritchstone")
    public static TransformedStone TRANSFORMEDSTONE;

    @ObjectHolder("occult:transformedstone")
    public static TileEntityType<TransformedStoneTileEntity> TRANSFORMEDSTONETILEENTITY;

}
