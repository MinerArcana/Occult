package minerarcana.occult.api;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.LazyOptional;

public class PressureCap {

    @CapabilityInject(IPressure.class)
    public static Capability<IPressure> PRESSURE_STORAGE_CAPABILITY;

    public static final Direction DEFAULT_FACING = null;

    public static LazyOptional<IPressure> getChunkPressure(final World world, final ChunkPos chunkPos) {
        return getChunkPressure(world.getChunk(chunkPos.x, chunkPos.z));
    }

    /**
     * Get the {@link IPressure} for the chunk.
     *
     * @param chunk The chunk
     * @return A lazy optional containing the IPressure, if any
     */
    public static LazyOptional<IPressure> getChunkPressure(final Chunk chunk) {
        return chunk.getCapability(PRESSURE_STORAGE_CAPABILITY, DEFAULT_FACING);
    }
    /**
     * Get the {@link IPressure} for the chunk.
     *
     * @param tileEntity The chunk
     * @return A lazy optional containing the IPressure, if any
     */
    public static LazyOptional<IPressure> getTileEntityPressure(final TileEntity tileEntity) {
        return tileEntity.getCapability(PRESSURE_STORAGE_CAPABILITY, DEFAULT_FACING);
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
                .map(chunkPressure -> {
                    for (PressureType type : chunkPressure.getAllPressure().keySet()) {
                        pressure.put(type,chunkPressure.getPressureFromType(type));
                    }
                    return pressure;
                }).orElse(pressure);
    }

    public static void addTilePressure(final TileEntity entity, final PressureType type, final int amount) {
        getTileEntityPressure(entity)
                .ifPresent(entityPressure -> {
                    entityPressure.add(type, amount);
                });
    }

    public static void removeTilePressure(final TileEntity entity, final PressureType type, final int amount) {
        getTileEntityPressure(entity)
                .ifPresent(entityPressure -> {
                    entityPressure.remove(type, amount);
                });
    }

    public static int getTilePressureForType(final TileEntity entity, final PressureType type) {
        getTileEntityPressure(entity)
                .map(entityPressure -> {
                    int pressure = entityPressure.getPressureFromType(type);
                    return pressure;
                }).orElse(0);
        return 0;
    }

    public static Object2IntMap<PressureType> getAllPressureFromTile(final TileEntity entity){
        Object2IntMap<PressureType> pressure = new Object2IntOpenHashMap<>();
        getTileEntityPressure(entity)
                .map(entityPressure -> {
                    for (PressureType type : entityPressure.getAllPressure().keySet()) {
                        pressure.put(type, entityPressure.getPressureFromType(type));
                    }
                    return pressure;
                }).orElse(pressure);
        return pressure;
    }

}