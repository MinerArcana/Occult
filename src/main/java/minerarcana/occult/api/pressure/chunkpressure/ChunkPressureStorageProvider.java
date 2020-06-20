package minerarcana.occult.api.chunkpressure;

import net.minecraft.util.Direction;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static minerarcana.occult.api.chunkpressure.ChunkPressureCap.PRESSURE_STORAGE_CAPABILITY;

public class ChunkPressureStorageProvider implements ICapabilityProvider {

    private final IChunkPressure pressure;
    private final LazyOptional<IChunkPressure> pressureStorage;

    public ChunkPressureStorageProvider(Chunk chunk) {
        this(new ChunkPressureStorage(chunk));
    }

    public ChunkPressureStorageProvider(IChunkPressure pressure) {
        this.pressure = pressure;
        this.pressureStorage = LazyOptional.of(() -> pressure);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == PRESSURE_STORAGE_CAPABILITY ? pressureStorage.cast() : LazyOptional.empty();
    }

}