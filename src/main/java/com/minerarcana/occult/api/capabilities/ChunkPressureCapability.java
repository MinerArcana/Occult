package com.minerarcana.occult.api.capabilities;

import com.google.common.collect.Maps;
import com.minerarcana.occult.api.pressure.IPressure;
import com.minerarcana.occult.api.pressure.PressureType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.LazyOptional;

import java.util.Map;

public class ChunkPressureCapability{

    public static final Map<ResourceLocation, PressureType> resources = Maps.newHashMap();

    @CapabilityInject(IPressure.class)
    public static final Capability<IPressure> CHUNKPRESSURECAPABILITY = null;

    public static final Direction DEFAULT_FACING = null;

    public static final int DEFAULT_CAPACITY = 1000;

    public static LazyOptional<IPressure> getChunkEnergy(final World world, final ChunkPos chunkPos) {
        return getChunkPressure(world.getChunk(chunkPos.x, chunkPos.z));
    }

    public static LazyOptional<IPressure> getChunkPressure(final Chunk chunk) {
        return chunk.getCapability(CHUNKPRESSURECAPABILITY, DEFAULT_FACING);
    }



}
