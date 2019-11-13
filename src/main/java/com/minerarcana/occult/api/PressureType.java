package com.minerarcana.occult.api;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;

public class PressureType extends ForgeRegistryEntry<PressureType> {

    private final ResourceLocation id;
    private final int colour;

    public PressureType() {
        this.id = getID();
        this.colour = 0;
    }

    public PressureType(ResourceLocation id, int colour) {
        this.id = id;
        this.colour = colour;
    }

    public int getPressureColour() {
        return colour;
    }

    public ResourceLocation getID() {
        return id;
    }


}