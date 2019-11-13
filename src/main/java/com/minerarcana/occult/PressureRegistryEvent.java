package com.minerarcana.occult;

import com.minerarcana.occult.api.PressureRegistry;
import com.minerarcana.occult.api.PressureType;
import com.minerarcana.occult.api.pressuretypes.InfernalPressure;
import com.minerarcana.occult.api.pressuretypes.NaturalPressure;
import com.minerarcana.occult.api.pressuretypes.SpiritualPressure;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class PressureRegistryEvent {

    private static final DeferredRegister<PressureType> PRESSURE = new DeferredRegister<>(PressureRegistry.pressureTypes, Occult.MOD_ID);

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
