package com.minerarcana.occult.common.items;

import com.minerarcana.occult.common.util.OccultItemGroup;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class OccultItems
{

    public static Item ivyleaf;
    public static Item amalgam;
    public static Item sacredsalt;
    public static Item greenvitriol;
    public static Item pyrite;
    public static Item lionmetal;
    public static Item chalk;
    public static Item mercury;
    public static Item sulphur;
    public static Item salt;

    public static Item occult_icon;


    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {

        ivyleaf = registerItem(new Item(new Item.Properties().group(OccultItemGroup.instance)), "ivy");
        amalgam = registerItem(new Item(new Item.Properties().group(OccultItemGroup.instance)), "amalgam");
        sacredsalt = registerItem(new SacredSalt(new Item.Properties().group(OccultItemGroup.instance)), "sacredsalt");
        greenvitriol = registerItem(new Item(new Item.Properties().group(OccultItemGroup.instance)), "greenvitriol");
        pyrite = registerItem(new Item(new Item.Properties().group(OccultItemGroup.instance)), "pyrite");
        lionmetal = registerItem(new Item(new Item.Properties().group(OccultItemGroup.instance)), "lionmetal");
        chalk = registerItem(new Item(new Item.Properties().group(OccultItemGroup.instance)), "chalk");
        mercury = registerItem(new Item(new Item.Properties().group(OccultItemGroup.instance)), "mercury");
        sulphur = registerItem(new Item(new Item.Properties().group(OccultItemGroup.instance)), "sulphur");
        salt = registerItem(new Item(new Item.Properties().group(OccultItemGroup.instance)), "salt");

    }

    public static Item registerItem(Item item, String name)
    {
        item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}
