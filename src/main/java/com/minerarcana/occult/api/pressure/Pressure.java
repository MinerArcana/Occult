package com.minerarcana.occult.api.pressure;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class Pressure {
    private final ResourceLocation registryName;
    private final long maxPressure;
    private final long minPressure;
    private final ITextComponent name;

    public Pressure(ResourceLocation registryName, long maxPressure, long minPressure, ITextComponent name) {
        this.registryName = registryName;
        this.maxPressure = maxPressure;
        this.minPressure = minPressure;
        this.name = name;
    }

    public ResourceLocation getRegistryName() {
        return registryName;
    }
    public long getMaxPressure() {
        return maxPressure;
    }

    public long getMinPressure() {
        return minPressure;
    }

    public ITextComponent getName() {
        return name;
    }
}
