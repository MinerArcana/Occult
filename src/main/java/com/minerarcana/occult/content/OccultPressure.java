package com.minerarcana.occult.content;

import com.minerarcana.occult.Occult;
import com.minerarcana.occult.api.pressure.PressureRegistry;
import com.minerarcana.occult.api.pressure.PressureType;
import com.minerarcana.occult.api.pressure.pressuretypes.InfernalPressure;
import com.minerarcana.occult.api.pressure.pressuretypes.NaturalPressure;
import com.minerarcana.occult.api.pressure.pressuretypes.SpiritualPressure;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class OccultPressure {

    public static final DeferredRegister<PressureType> PRESSURE = new DeferredRegister<>(PressureRegistry.pressureTypes, Occult.MOD_ID);

    public static final RegistryObject<PressureType> INFERNAL = PRESSURE.register("infernal",
            InfernalPressure::new);

    public static final RegistryObject<PressureType> SPIRITUAL = PRESSURE.register("spiritual",
            SpiritualPressure::new);

    public static final RegistryObject<PressureType> NATURAL = PRESSURE.register("natural",
            NaturalPressure::new);

    public static void register(IEventBus eventBus) {
        PRESSURE.register(eventBus);
    }

}
