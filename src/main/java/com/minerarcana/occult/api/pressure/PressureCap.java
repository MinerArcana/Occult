package com.minerarcana.occult.api.pressure;

import com.minerarcana.occult.api.PressureType;
import io.netty.util.collection.IntObjectMap;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.LazyOptional;

import java.util.concurrent.atomic.AtomicInteger;

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
                .map(chunkPressure -> {
                    chunkPressure.add(type, amount);
                    return true;
                });
    }
    public static void removeChunkPressure(final Chunk chunk, final PressureType type, final int amount) {
        getChunkPressure(chunk)
                .map(chunkPressure -> {
                    chunkPressure.remove(type, amount);
                    return true;
                });
    }

    public static int getChunkPressureForType(final Chunk chunk, final PressureType type) {
        AtomicInteger pressure = new AtomicInteger();
        getChunkPressure(chunk)
                .map(chunkPressure -> {
                    pressure.set(chunkPressure.getPressureFromType(type));
                    return pressure.get();
                });
        return pressure.get();
    }

    public static IntObjectMap<PressureType> getAllPressureFromChunk(final Chunk chunk){
        IntObjectMap<PressureType> pressure = null;
        getChunkPressure(chunk)
                .map(chunkPressure -> {
                    for (PressureType type : chunkPressure.getAllPressure().keySet()) {
                        pressure.put(chunkPressure.getPressureFromType(type),type);
                    }
                    return pressure;
                });
        return pressure;
    }

    public static void addTilePressure(final TileEntity entity, final PressureType type, final int amount) {
        getTileEntityPressure(entity)
                .map(entityPressure -> {
                    entityPressure.add(type, amount);
                    return true;
                });
    }

    public static void removeTilePressure(final TileEntity entity, final PressureType type, final int amount) {
        getTileEntityPressure(entity)
                .map(entityPressure -> {
                    entityPressure.remove(type, amount);
                    return true;
                });
    }

    public static int getTilePressureForType(final TileEntity entity, final PressureType type) {
        AtomicInteger pressure = new AtomicInteger();
        getTileEntityPressure(entity)
                .map(entityPressure -> {
                    pressure.set(entityPressure.getPressureFromType(type));
                    return pressure.get();
                });
        return pressure.get();
    }

    public static IntObjectMap<PressureType> getAllPressureFromTile(final TileEntity entity){
        IntObjectMap<PressureType> pressure = null;
        getTileEntityPressure(entity)
                .map(entityPressure -> {
                    for (PressureType type : entityPressure.getAllPressure().keySet()) {
                        pressure.put(entityPressure.getPressureFromType(type),type);
                    }
                    return pressure;
                });
        return pressure;
    }


}
