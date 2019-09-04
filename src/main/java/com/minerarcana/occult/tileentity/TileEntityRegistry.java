package com.minerarcana.occult.tileentity;

import com.minerarcana.occult.tileentity.ritualfire.RitualFireTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TileEntityRegistry {


    @SubscribeEvent
    public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event)
    {

        event.getRegistry().register(TileEntityType.Builder.create(RitualFireTileEntity:: new, OccultTileEntities.RITUALFIRE).build(null).setRegistryName("ritualfire"));

    }
}
