package com.minerarcana.occult.api.pressure;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;


public interface IPressure
{
    World getWorld();

    ChunkPos getChunkPos();

    int addPressure(int added, PressureTypes type, boolean commit);

    int removePressure(int removed, PressureTypes type, boolean commit);

    int getPressure();

    int getMaxPressure();

    boolean canRemovePressure();

    boolean canAddPressure();



}
