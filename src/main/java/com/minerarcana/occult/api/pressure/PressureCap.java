package com.minerarcana.occult.api.pressure;

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

    public static LazyOptional<IPressure> getChunkPressure(final World world, final ChunkPos chunkPos) {
        return getChunkPressure(world.getChunk(chunkPos.x, chunkPos.z));
    }

    public static final Direction DEFAULT_FACING = null;

    /**
     * Get the {@link IPressure} for the chunk.
     *
     * @param chunk The chunk
     * @return A lazy optional containing the IChunkEnergy, if any
     */
    public static LazyOptional<IPressure> getChunkPressure(final Chunk chunk) {
        return chunk.getCapability(PRESSURE_STORAGE_CAPABILITY, DEFAULT_FACING);
    }

}
