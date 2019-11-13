package com.minerarcana.occult.api;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class PressureType implements INBTSerializable<CompoundNBT> {

    private final Pressure pressure;
    private final int colour;

    public PressureType(Pressure pressure) {
        this.pressure = pressure;
        this.colour = 0;
    }

    public PressureType(Pressure pressure, int colour) {
        this.pressure = pressure;
        this.colour = colour;
    }

    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        return LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return new CompoundNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
    }

    public int getPressureColour() {
        return colour;
    }

    public Pressure getPressureType() {
        return pressure;
    }

    public ResourceLocation getID() {
        return pressure.getID();
    }


}