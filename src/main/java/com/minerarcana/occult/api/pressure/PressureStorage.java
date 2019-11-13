package com.minerarcana.occult.api.pressure;

import com.minerarcana.occult.api.PressureType;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

public class PressureStorage implements IPressure {

    protected int pressure;
    protected int capacity;
    protected int maxAdd;
    protected int maxRemove;
    protected PressureType type;

    public PressureStorage(int capacity, PressureType type)
    {
        this(capacity, type, capacity, capacity, 0);
    }

    public PressureStorage(int capacity, PressureType type, int maxTransfer)
    {
        this(capacity, type, maxTransfer, maxTransfer, 0);
    }

    public PressureStorage(int capacity, PressureType type, int maxAdd, int maxRemove)
    {
        this(capacity, type, maxAdd, maxRemove, 0);
    }

    public PressureStorage(int capacity, PressureType type, int maxAdd, int maxRemove, int pressure)
    {
        this.capacity = capacity;
        this.maxAdd = maxAdd;
        this.maxRemove = maxRemove;
        this.pressure = Math.max(0 , Math.min(capacity, pressure));
        this.type = type;

    }

    @Override
    public World getWorld() {
        return null;
    }

    @Override
    public ChunkPos getChunkPos() {
        return null;
    }

    @Override
    public TileEntity getTileEntity() {
        return null;
    }

    @Override
    public BlockPos getTilePos() {
        return null;
    }

    @Override
    public Block getBlock() {
        return null;
    }

    @Override
    public int addPressure(int added, PressureType type, boolean commit) {
        if (!canAcceptPressure())
            return 0;

        int pressureAdded = Math.min(capacity - pressure, Math.min(this.maxAdd, maxAdd));
        if (!commit)
            pressure += pressureAdded;
        return pressureAdded;
    }

    @Override
    public int removePressure(int removed, PressureType type, boolean commit) {
        if (!canSendPressure())
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
    public boolean canSendPressure() {
        return this.maxRemove > 0;
    }


    @Override
    public boolean canAcceptPressure() {
        return this.maxAdd > 0;
    }

    @Override
    public PressureType getType(){
        return type;
    }


}