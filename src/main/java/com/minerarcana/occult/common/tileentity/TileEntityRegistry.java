package com.minerarcana.occult.common.tileentity;

import com.minerarcana.occult.common.blocks.featureblocks.eldritchstone.EldritchStoneTileEntity;
import com.minerarcana.occult.common.tileentity.crucible.CrucibleTile;
import com.minerarcana.occult.common.tileentity.ritualfire.RitualBaseTile;
import com.minerarcana.occult.common.tileentity.ritualfire.RitualFireTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.minerarcana.occult.update.util.lib.OccultNameLib.*;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TileEntityRegistry {


    @SubscribeEvent
    public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event)
    {
        event.getRegistry().register(TileEntityType.Builder.create(CrucibleTile:: new, crucible).build(null).setRegistryName("crucible"));
        event.getRegistry().register(TileEntityType.Builder.create(RitualBaseTile:: new, ritualbase).build(null).setRegistryName("ritualbase"));
        event.getRegistry().register(TileEntityType.Builder.create(RitualFireTile:: new, ritualfire).build(null).setRegistryName("ritualfire"));
        event.getRegistry().register(TileEntityType.Builder.create(EldritchStoneTileEntity:: new, eldritchstone).build(null).setRegistryName("eldritchstone"));
        event.getRegistry().register(TileEntityType.Builder.create(EldritchStoneTileEntity:: new, transformedstone).build(null).setRegistryName("transformedstone"));

    }

   /* @SubscribeEvent
    public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event)
    {
        event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
            BlockPos pos = data.readBlockPos();
            return new RitualBaseContainer(windowId, world, pos, inv);

        }).setRegistryName("ritualbase"));
    }*/

}
