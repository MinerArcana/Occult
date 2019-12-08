package com.minerarcana.occult.api.pressure;

import com.minerarcana.occult.api.PressureType;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.block.Block;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class PressureStorage implements IPressure {

    public Object2IntMap<PressureType> pressures;
    public int capacity;

    public PressureStorage(int capacity)
    {
        this.capacity = capacity;

    }

    @Override
    public int add(PressureType pressureType, int amount) {
        ensureExists(pressureType);
        int pressure = pressures.get(pressureType);
        if(pressure + amount >= capacity) {
            int add = pressure + amount;
            return pressures.put(pressureType, add);
        }
        else{return pressures.put(pressureType, capacity);}
    }

    @Override
    public int remove(PressureType pressureType, int amount) {
        ensureExists(pressureType);
        int pressure = pressures.get(pressureType);
        if(pressure + amount >= capacity) {
            int remove = pressure - amount;
            return pressures.put(pressureType, remove);
        }
        else{return pressures.put(pressureType, capacity);}

    }

    @Override
    public void empty(PressureType pressureType) {
        pressures.put(pressureType, 0);
    }

    @Override
    public void fill(PressureType pressureType) {
        pressures.put(pressureType, capacity);
    }

    @Override
    public int getPressureAmount(PressureType pressureType) {
        return pressures.get(pressureType);
    }

    @Override
    public CompoundNBT serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {

    }

    private void ensureExists(PressureType pressureType) {
        if (!pressures.containsKey(pressureType)) {
            pressures.put(pressureType, 0);
        }
    }
}
