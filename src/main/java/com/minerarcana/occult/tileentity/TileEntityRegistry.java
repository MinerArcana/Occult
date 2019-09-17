package com.minerarcana.occult.tileentity;

import com.minerarcana.occult.blocks.featureblocks.eldritchstone.EldritchStoneTileEntity;
import com.minerarcana.occult.tileentity.crucible.CrucibleTile;
import com.minerarcana.occult.tileentity.ritualfire.RitualBaseTile;
import com.minerarcana.occult.tileentity.ritualfire.RitualFireTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import static com.minerarcana.occult.Occult.MOD_ID;


@Mod.EventBusSubscriber
public class TileEntityRegistry {


    @SubscribeEvent
    public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event)
    {
        event.getRegistry().register(TileEntityType.Builder.create(CrucibleTile:: new, OccultTileEntities.CRUCIBLE).build(null).setRegistryName("crucible"));
        event.getRegistry().register(TileEntityType.Builder.create(RitualBaseTile:: new, OccultTileEntities.RITUALBASE).build(null).setRegistryName("ritualbase"));
        event.getRegistry().register(TileEntityType.Builder.create(RitualFireTile:: new, OccultTileEntities.RITUALFIRE).build(null).setRegistryName("ritualfire"));
        event.getRegistry().register(TileEntityType.Builder.create(EldritchStoneTileEntity:: new, OccultTileEntities.ELDRITCHSTONE).build(null).setRegistryName("eldritchstone"));

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
