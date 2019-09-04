package com.minerarcana.occult.capabilities.chunkpressure;

import com.minerarcana.occult.api.pressure.ChunkPressureInject;
import com.minerarcana.occult.api.pressure.IPressure;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ChunkPressureProvider implements ICapabilitySerializable<CompoundNBT> {
    private final IPressure pressureStorage;
    private final LazyOptional<IPressure> pressureStorageOptional;

    public ChunkPressureProvider() {
        this(new PressureStorage());
    }

    public ChunkPressureProvider(IPressure pressureStorage) {
        this.pressureStorage = pressureStorage;
        this.pressureStorageOptional = LazyOptional.of(() -> pressureStorage);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == ChunkPressureInject.PRESSURE_CAPABILITY ? pressureStorageOptional.cast() : LazyOptional.empty();

    }

    @Override
    public CompoundNBT serializeNBT() {
        return pressureStorage.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        pressureStorage.deserializeNBT(nbt);
    }
}
