package com.minerarcana.occult.api.pressure;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistryEntry;

import static com.minerarcana.occult.content.OccultPressure.PRESSURE;

public class PressureType extends ForgeRegistryEntry<PressureType> {
    private final int colour;

    public PressureType(int colour) {
        this.colour = colour;
    }

    public int getPressureColour() {
        return colour;
    }

    public PressureType getTypeFromName(String name) {
        PressureType pressureType = null;

        for(RegistryObject<PressureType> type: PRESSURE.getEntries()) {
            if (type.getId().toString().equals(name)) {
                pressureType = type.get();
            }
        }
        return pressureType;
    }
}