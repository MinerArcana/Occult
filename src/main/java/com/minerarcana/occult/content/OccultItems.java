package com.minerarcana.occult.content;

import com.minerarcana.occult.items.Sulphur;
import com.minerarcana.occult.items.lionmetaltype.HungryLionMetalArmor;
import com.minerarcana.occult.items.lionmetaltype.SatedLionMetalArmor;
import com.minerarcana.occult.util.itemgroup.OccultGroup;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static com.minerarcana.occult.Occult.MOD_ID;

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
    public static final RegistryObject<Item> SATED_LIONMETAL_INGOT = ITEMS.register("sated_lionmetal_ingot",
            () -> new Item(Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> HUNGRY_LIONMETAL_INGOT = ITEMS.register("hungry_lionmetal_ingot",
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
            () -> new ArmorItem(new SatedLionMetalArmor(), EquipmentSlotType.HEAD ,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> SATED_LIONMETAL_CHEST = ITEMS.register("sated_lionmetal_chest",
            () -> new ArmorItem(new SatedLionMetalArmor(), EquipmentSlotType.CHEST ,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> SATED_LIONMETAL_LEGS = ITEMS.register("sated_lionmetal_legs",
            () -> new ArmorItem(new SatedLionMetalArmor(), EquipmentSlotType.LEGS ,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> SATED_LIONMETAL_FEET = ITEMS.register("sated_lionmetal_feet",
            () -> new ArmorItem(new SatedLionMetalArmor(), EquipmentSlotType.FEET ,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );

    public static final RegistryObject<Item> HUNGRY_LIONMETAL_HELM = ITEMS.register("hungry_lionmetal_helm",
            () -> new ArmorItem(new HungryLionMetalArmor(), EquipmentSlotType.HEAD ,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> HUNGRY_LIONMETAL_CHEST = ITEMS.register("hungry_lionmetal_chest",
            () -> new ArmorItem(new HungryLionMetalArmor(), EquipmentSlotType.CHEST ,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> HUNGRY_LIONMETAL_LEGS = ITEMS.register("hungry_lionmetal_legs",
            () -> new ArmorItem(new HungryLionMetalArmor(), EquipmentSlotType.LEGS ,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );
    public static final RegistryObject<Item> HUNGRY_LIONMETAL_FEET = ITEMS.register("hungry_lionmetal_feet",
            () -> new ArmorItem(new HungryLionMetalArmor(), EquipmentSlotType.FEET ,Objects.requireNonNull(new Item.Properties()
                    .group(OccultGroup.instance)))
    );

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
