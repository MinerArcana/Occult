package com.minerarcana.occult.items;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class OccultItems
{

    public static Item ivyleaf;


    public static Item occult_icon;




    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {

        ivyleaf = registerItem(new Item(new Item.Properties()), "ivy");
        occult_icon = registerItem(new Item(new Item.Properties()), "occult_icon");

    }

    public static Item registerItem(Item item, String name)
    {
        item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}
