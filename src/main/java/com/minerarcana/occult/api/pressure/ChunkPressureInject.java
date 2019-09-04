package com.minerarcana.occult.api.pressure;


import com.google.common.collect.Maps;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

import java.util.Map;

public class ChunkPressureInject {

    public static final Map<ResourceLocation, Pressure> pressures = Maps.newHashMap();


    @CapabilityInject(IPressure.class)
    public static final Capability<IPressure> PRESSURE_CAPABILITY = null;

}
