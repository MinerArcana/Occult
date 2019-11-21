package com.minerarcana.occult.content;

import com.minerarcana.occult.items.Sulphur;
import com.minerarcana.occult.util.itemgroup.OccultGroup;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static com.minerarcana.occult.Occult.MOD_ID;

public class Items {

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

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
