package com.minerarcana.occult.api.pressure;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

public interface IChunkPressure
{
    World getWorld();

    ChunkPos getChunkPos();
}
