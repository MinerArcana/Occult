package com.minerarcana.occult.items;

import net.minecraft.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class OccultItems
{

    public static Item ivyleaf;


    public static Item occult_icon;




    public static void init()
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
