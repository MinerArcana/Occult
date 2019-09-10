package com.minerarcana.occult.api.pressure;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

public class PressureStorage implements IPressure {

    protected int pressure;
    protected int capacity;
    protected int maxAdd;
    protected int maxRemove;
    protected PressureTypes type;

    public PressureStorage(int capacity, PressureTypes type)
    {
        this(capacity, type, capacity, capacity, 0);
    }

    public PressureStorage(int capacity, PressureTypes type, int maxTransfer)
    {
        this(capacity, type, maxTransfer, maxTransfer, 0);
    }

    public PressureStorage(int capacity, PressureTypes type, int maxAdd, int maxRemove)
    {
        this(capacity, type, maxAdd, maxRemove, 0);
    }

    public PressureStorage(int capacity, PressureTypes type, int maxAdd, int maxRemove, int pressure)
    {
        this.capacity = capacity;
        this.maxAdd = maxAdd;
        this.maxRemove = maxRemove;
        this.pressure = Math.max(0 , Math.min(capacity, pressure));
        this.type = type;

    }

    @Override
    public int addPressure(int added, PressureTypes type, boolean commit) {
        if (!canAddPressure())
            return 0;

        int pressureAdded = Math.min(capacity - pressure, Math.min(this.maxAdd, maxAdd));
        if (!commit)
            pressure += pressureAdded;
        return pressureAdded;
    }

    @Override
    public int removePressure(int removed, PressureTypes type, boolean commit) {
        if (!canRemovePressure())
            return 0;

        int pressureRemoved = Math.min(pressure, Math.min(this.maxRemove, maxRemove));
        if (!commit)
            pressure -= pressureRemoved;
        return pressureRemoved;
    }

    @Override
    public int getPressure() {
        return pressure;
    }

    @Override
    public int getMaxPressure() {
        return capacity;
    }

    @Override
    public boolean canRemovePressure() {
        return this.maxRemove > 0;
    }

    @Override
    public boolean canAddPressure() {
        return this.maxAdd > 0;
    }

    @Override
    public World getWorld() {
        return null;
    }

    @Override
    public ChunkPos getChunkPos() {
        return null;
    }
}
