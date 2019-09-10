package com.minerarcana.occult.api.pressure;

import com.minerarcana.occult.Occult;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class PressureType
{
    private final ResourceLocation registryName;
    private final int capacity;
    private final int pressure;
    private final ITextComponent type;


    public PressureType(ResourceLocation registryName, int capacity, int pressure, ITextComponent type) {
        this.registryName = registryName;
        this.capacity = capacity;
        this.pressure = pressure;
        this.type = type;
    }

    public ResourceLocation getRegistryName() {
        return registryName;
    }

    public int getMaxPressure() {
        return capacity;
    }

    public int getPressure() {
        return pressure;
    }

    public ITextComponent getType() {
        return type;
    }

}



