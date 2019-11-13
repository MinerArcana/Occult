package com.minerarcana.occult;

import com.minerarcana.occult.api.PressureRegistry;
import com.minerarcana.occult.api.pressuretypes.InfernalPressure;
import com.minerarcana.occult.api.pressuretypes.NaturalPressure;
import com.minerarcana.occult.api.pressuretypes.SpiritualPressure;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class PressureRegistryEvent {

    private static final DeferredRegister<Pressure> PRESSURE = new DeferredRegister<>(PressureRegistry.pressureTypes, Occult.MOD_ID);

    public static final RegistryObject<Pressure> INFERNAL = PRESSURE.register("infernal",
            () -> new Pressure(InfernalPressure::new));

    public static final RegistryObject<Pressure> SPIRITUAL = PRESSURE.register("spiritual",
            () -> new Pressure(SpiritualPressure::new));

    public static final RegistryObject<Pressure> NATURAL = PRESSURE.register("natural",
            () -> new Pressure(NaturalPressure::new));


    public static void register(IEventBus eventBus) {
        PRESSURE.register(eventBus);
    }

}
