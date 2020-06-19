package minerarcana.occult.api.worldpressure;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static minerarcana.occult.api.chunkpressure.ChunkPressureCap.PRESSURE_STORAGE_CAPABILITY;

public class WorldPressureStorageProvider implements ICapabilitySerializable<CompoundNBT> {
    private final IWorldPressure pressure;
    private final LazyOptional<IWorldPressure> pressureStorage;

    public WorldPressureStorageProvider() {
        this(new WorldPressureStorage());
    }

    public WorldPressureStorageProvider(IWorldPressure pressure) {
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