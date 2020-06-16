package minerarcana.occult.api;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static minerarcana.occult.api.PressureCap.PRESSURE_STORAGE_CAPABILITY;

public class PressureStorageProvider implements ICapabilitySerializable<CompoundNBT> {
    private final IPressure pressure;
    private final LazyOptional<IPressure> pressureStorage;

    public PressureStorageProvider() {
        this(new PressureStorage());
    }

    public PressureStorageProvider(IPressure pressure) {
        this.pressure = pressure;
        this.pressureStorage = LazyOptional.of(() -> pressure);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == PRESSURE_STORAGE_CAPABILITY ? pressureStorage.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return pressure.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        pressure.deserializeNBT(nbt);
    }
}