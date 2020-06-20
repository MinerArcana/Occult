package minerarcana.occult.api.pressure.chunkpressure;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import minerarcana.occult.api.pressure.PressureType;
import net.minecraft.util.Direction;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.LazyOptional;

public class ChunkPressureCap {

    @CapabilityInject(IChunkPressure.class)
    public static Capability<IChunkPressure> PRESSURE_STORAGE_CAPABILITY;

    public static final Direction DEFAULT_FACING = null;

    /**
     * Get the {@link IChunkPressure} for the chunk.
     *
     * @param chunk The chunk
     * @return A lazy optional containing the IChunkPressure, if any
     */
    public static LazyOptional<IChunkPressure> getChunkPressure(final Chunk chunk) {
        return chunk.getCapability(PRESSURE_STORAGE_CAPABILITY, DEFAULT_FACING);
    }

    public static void addChunkPressure(final Chunk chunk, final PressureType type, final int amount) {
        getChunkPressure(chunk)
                .ifPresent(chunkPressure -> {
                    chunkPressure.add(type, amount);
                });
    }
    public static void removeChunkPressure(final Chunk chunk, final PressureType type, final int amount) {
        getChunkPressure(chunk)
                .ifPresent(chunkPressure -> {
                    chunkPressure.remove(type, amount);
                });
    }

    public static int getChunkPressureForType(final Chunk chunk, final PressureType type) {
                getChunkPressure(chunk)
                .map(chunkPressure -> chunkPressure.getPressureFromType(type)).orElse(0);
        return 0;
    }

    public static Object2IntMap<PressureType> getAllPressureFromChunk(final Chunk chunk){
        Object2IntMap<PressureType> pressure = new Object2IntOpenHashMap<>();
        return getChunkPressure(chunk)
                .map(IChunkPressure::getAllPressure).orElse(pressure);
    }

}