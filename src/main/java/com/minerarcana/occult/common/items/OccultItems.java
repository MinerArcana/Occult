package com.minerarcana.occult.common.items;

import com.minerarcana.occult.update.util.itemgroup.OccultGroup;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import static com.minerarcana.occult.update.util.lib.OccultNameLib.*;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class OccultItems
{


    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {

        ivyleaf = registerItem(new Item(new Item.Properties().group(OccultGroup.instance)), "ivy");
        amalgam = registerItem(new Item(new Item.Properties().group(OccultGroup.instance)), "amalgam");
        sacredsalt = registerItem(new SacredSalt(new Item.Properties().group(OccultGroup.instance)), "sacredsalt");
        greenvitriol = registerItem(new Item(new Item.Properties().group(OccultGroup.instance)), "greenvitriol");
        pyrite = registerItem(new Item(new Item.Properties().group(OccultGroup.instance)), "pyrite");
        lionmetal = registerItem(new Item(new Item.Properties().group(OccultGroup.instance)), "lionmetal");
        chalk = registerItem(new Item(new Item.Properties().group(OccultGroup.instance)), "chalk");
        mercury = registerItem(new Item(new Item.Properties().group(OccultGroup.instance)), "mercury");
        sulphur = registerItem(new Item(new Item.Properties().group(OccultGroup.instance)), "sulphur");
        salt = registerItem(new Item(new Item.Properties().group(OccultGroup.instance)), "salt");

    }

    public static Item registerItem(Item item, String name)
    {
        item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}
