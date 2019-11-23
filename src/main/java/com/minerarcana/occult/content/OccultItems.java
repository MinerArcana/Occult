package com.minerarcana.occult.content;

import com.minerarcana.occult.items.Sulphur;
import com.minerarcana.occult.items.liontools.*;
import com.minerarcana.occult.util.itemgroup.OccultGroup;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static com.minerarcana.occult.Occult.MOD_ID;
import static com.minerarcana.occult.items.liontools.OccultArmorTypes.hungry_lion_tools;
import static com.minerarcana.occult.items.liontools.OccultArmorTypes.sated_lion_tools;

public class OccultItems {

    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MOD_ID);

    //Metal Nuggets
    //-----------------------------------------------------------------------------------------------------
    public static final RegistryObject<Item> AMALGAM_NUGGET = ITEMS.register("amalgam_nugget",
            () -> new Item(Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> HUNGRY_LIONMETAL_NUGGET = ITEMS.register("hungry_lionmetal_nugget",
            () -> new Item(Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> SATED_LIONMETAL_NUGGET = ITEMS.register("sated_lionmetal_nugget",
            () -> new Item(Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    //Metal ingots
    //-----------------------------------------------------------------------------------------------------
    public static final RegistryObject<Item> AMALGAM_INGOT = ITEMS.register("amalgam_ingot",
            () -> new Item(Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );

    public static final RegistryObject<Item> HUNGRY_LIONMETAL_INGOT = ITEMS.register("hungry_lionmetal_ingot",
            () -> new Item(Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> SATED_LIONMETAL_INGOT = ITEMS.register("sated_lionmetal_ingot",
            () -> new Item(Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );

    //Crafted Items
    //-----------------------------------------------------------------------------------------------------
    public static final RegistryObject<Item> SACRED_SALT = ITEMS.register("sacred_salt",
            () -> new Item(Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> SALT = ITEMS.register("salt",
            () -> new Item(Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> MERCURY = ITEMS.register("mercury",
            () -> new Item(Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> SULPHUR = ITEMS.register("sulphur",
            () -> new Sulphur(DyeColor.YELLOW, Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> CHALK = ITEMS.register("chalk",
            () -> new Item(Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> PEALRASH = ITEMS.register("pearlash",
            () -> new Item(Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );

    //Armor
    //-----------------------------------------------------------------------------------------------------
    public static final RegistryObject<Item> SATED_LIONMETAL_HELM = ITEMS.register("sated_lionmetal_helm",
            () -> new SatedLionMetalArmorItem(EquipmentSlotType.HEAD ,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> SATED_LIONMETAL_CHEST = ITEMS.register("sated_lionmetal_chest",
            () -> new SatedLionMetalArmorItem(EquipmentSlotType.CHEST ,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> SATED_LIONMETAL_LEGS = ITEMS.register("sated_lionmetal_legs",
            () -> new SatedLionMetalArmorItem(EquipmentSlotType.LEGS ,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> SATED_LIONMETAL_FEET = ITEMS.register("sated_lionmetal_feet",
            () -> new SatedLionMetalArmorItem(EquipmentSlotType.FEET ,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );

    public static final RegistryObject<Item> HUNGRY_LIONMETAL_HELM = ITEMS.register("hungry_lionmetal_helm",
            () -> new HungryLionArmorItem(EquipmentSlotType.HEAD ,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> HUNGRY_LIONMETAL_CHEST = ITEMS.register("hungry_lionmetal_chest",
            () -> new HungryLionArmorItem(EquipmentSlotType.CHEST ,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> HUNGRY_LIONMETAL_LEGS = ITEMS.register("hungry_lionmetal_legs",
            () -> new HungryLionArmorItem(EquipmentSlotType.LEGS ,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> HUNGRY_LIONMETAL_FEET = ITEMS.register("hungry_lionmetal_feet",
            () -> new HungryLionArmorItem(EquipmentSlotType.FEET ,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );

    //Tools
    public static final RegistryObject<Item> HUNGRY_LIONMETAL_HATCHET = ITEMS.register("hungry_lionmetal_hatchet",
            () -> new OccultHatchet(hungry_lion_tools,0,0, Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> HUNGRY_LIONMETAL_SWORD = ITEMS.register("hungry_lionmetal_sword",
            () -> new OccultSword(hungry_lion_tools,0,0,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> HUNGRY_LIONMETAL_SHOVEL = ITEMS.register("hungry_lionmetal_shovel",
            () -> new OccultShovel(hungry_lion_tools,0,0,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> HUNGRY_LIONMETAL_PICKAXE = ITEMS.register("hungry_lionmetal_pickaxe",
            () -> new OccultPickaxe(hungry_lion_tools,0,0,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> SATED_LIONMETAL_HATCHET = ITEMS.register("sated_lionmetal_hatchet",
            () -> new OccultHatchet(sated_lion_tools,0,0,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> SATED_LIONMETAL_SWORD = ITEMS.register("sated_lionmetal_sword",
            () -> new OccultSword(sated_lion_tools,0,0,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> SATED_LIONMETAL_SHOVEL = ITEMS.register("sated_lionmetal_shovel",
            () -> new OccultShovel(sated_lion_tools,0,0,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> SATED_LIONMETAL_PICKAXE = ITEMS.register("sated_lionmetal_pickaxe",
            () -> new OccultPickaxe(sated_lion_tools,0,0,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
