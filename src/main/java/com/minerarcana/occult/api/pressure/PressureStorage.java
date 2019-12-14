package com.minerarcana.occult.api.pressure;

import com.minerarcana.occult.api.PressureType;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.nbt.CompoundNBT;

public class PressureStorage implements IPressure {

    public Object2IntMap<PressureType> pressures;
    public PressureType type;
    public int capacity;

    public PressureStorage() {
        this.capacity = 1000;
    }

    public PressureStorage(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int add(PressureType pressureType, int amount) {
        int pressure = getPressureFromType(pressureType);
        if (pressure + amount <= capacity) {
            int add = pressure + amount;
            pressures.put(pressureType, add);
            return amount;
        } else {
            fill(pressureType);
            return capacity;
        }
    }

    @Override
    public int remove(PressureType pressureType, int amount) {
        int pressure = getPressureFromType(pressureType);
        if (pressure + amount >= 0) {
            int remove = pressure - amount;
            pressures.put(pressureType, remove);
            return amount;
        } else {
            empty(pressureType);
            return 0;
        }
    }

    @Override
    public void empty(PressureType pressureType) {
        pressures.put(pressureType, 0);
    }

    @Override
    public void fill(PressureType pressureType) {
        pressures.replace(pressureType, capacity);
    }

    @Override
    public Object2IntMap<PressureType> getAllPressure() {
        return pressures;
    }

    @Override
    public int getPressureFromType(PressureType pressureType) {
        ensureExists(pressureType);
        return pressures.get(pressureType);
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT compound = new CompoundNBT();
        if(!pressures.keySet().isEmpty()) {
            for (PressureType pressureType : pressures.keySet()) {
                compound.putInt(pressureType.toString(), getPressureFromType(pressureType));
            }
        }
        return compound;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if(!nbt.keySet().isEmpty()) {
            for (String pressureType : nbt.keySet()) {
                pressures.put(type.getTypeFromName(pressureType), nbt.getInt(pressureType));
            }
        }
    }

    private void ensureExists(PressureType pressureType) {
        if (!pressures.containsKey(pressureType)) {
            pressures.put(pressureType, 0);
        }
    }
}
