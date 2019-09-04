package com.minerarcana.occult.tileentity;

import com.minerarcana.occult.tileentity.ritualfire.FireRitualTileEntity;
import com.minerarcana.occult.tileentity.ritualfire.RitualFire;
import com.minerarcana.occult.tileentity.ritualfire.RitualFireContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class OccultTileEntities
{
    @ObjectHolder("occult:ritualfire")
    public static RitualFire RITUALFIRE;

    @ObjectHolder("occult:ritualfire")
    public static TileEntityType<FireRitualTileEntity> RITUALTILE;

    @ObjectHolder("occult:ritualfire")
    public static ContainerType<RitualFireContainer> RITUALCONTAINER;


}
