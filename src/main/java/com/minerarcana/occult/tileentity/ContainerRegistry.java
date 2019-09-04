package com.minerarcana.occult.tileentity;


import com.minerarcana.occult.tileentity.ritualfire.RitualFireContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ContainerRegistry
{

    @SubscribeEvent
    public static void onContainerRegistry(final RegistryEvent<ContainerType<?>> event)
    {

        event.getRegistry().register(IForgeContainerType.create(((windowId, inv, data) -> {
            return new RitualFireContainer();

        })));


    }


}
