package com.minerarcana.occult.api;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;

public class PressureType extends ForgeRegistryEntry<PressureType> {

    private final int colour;

    public PressureType(int colour) {
        this.colour = colour;
    }

    public int getPressureColour() {
        return colour;
    }

}