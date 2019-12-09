package com.minerarcana.occult.api.pressure;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ChunkPressureStorage implements ICapabilitySerializable<CompoundNBT> {

    @CapabilityInject(IPressure.class)
    public static Capability<IPressure> PRESSURE_STORAGE_CAPABILITY;

    private final IPressure pressure;
    private final LazyOptional<IPressure> pressureOptional;

    public ChunkPressureStorage(int capacity) {
        this(new PressureStorage(capacity));
    }

    public ChunkPressureStorage(IPressure pressure) {
        this.pressure = pressure;
        this.pressureOptional = LazyOptional.of(() -> pressure);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == PRESSURE_STORAGE_CAPABILITY ? pressureOptional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {

    }
}
