package com.minerarcana.occult;

import com.minerarcana.occult.api.PressureRegistry;
import com.minerarcana.occult.api.PressureType;
import com.minerarcana.occult.api.pressuretypes.InfernalPressure;
import com.minerarcana.occult.api.pressuretypes.NaturalPressure;
import com.minerarcana.occult.api.pressuretypes.SpiritualPressure;
import com.minerarcana.occult.getteritem.ScryingCrystal;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.minerarcana.occult.OccultGroup.OCCULT_GROUP;

public class PressureRegistryEvent {

    public static final DeferredRegister<PressureType> PRESSURE = new DeferredRegister<>(PressureRegistry.pressureTypes, Occult.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Occult.MOD_ID);

    public static final RegistryObject<PressureType> INFERNAL = PRESSURE.register("infernal",
            InfernalPressure::new);

    public static final RegistryObject<PressureType> SPIRITUAL = PRESSURE.register("spiritual",
            SpiritualPressure::new);

    public static final RegistryObject<PressureType> NATURAL = PRESSURE.register("natural",
            NaturalPressure::new);

    //Items

    public static final RegistryObject<Item> SCRYING_CRYSTAL = ITEMS.register("scrying_crystal",
            () -> new ScryingCrystal(new Item.Properties().group(OCCULT_GROUP)));

    public static void register(IEventBus eventBus) {
        PRESSURE.register(eventBus);
        ITEMS.register(eventBus);
    }

}
