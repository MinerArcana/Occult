package com.minerarcana.occult.capabilities.chunkpressure;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.minerarcana.occult.api.pressure.IPressure;
import com.minerarcana.occult.api.pressure.Pressure;
import net.minecraft.nbt.CompoundNBT;

import java.util.Map;

public class PressureStorage implements IPressure {


    private final Table<String, Pressure, Long> pressures;

    public PressureStorage() {
        this.pressures = HashBasedTable.create();
    }

    @Override
    public long addPressure(String name, Pressure pressure, long amount, boolean commit) {
        long pressureAdded = 0;
        if (amount > 0) {
            long currentPressure = this.getPressure(name, pressure);

            if (currentPressure < pressure.getMaxPressure()){
                pressureAdded = Math.min(amount, pressure.getMaxPressure() - currentPressure);
                if (commit) {
                    pressures.put(name, pressure, currentPressure + pressureAdded);
                }
            }
        }
        return pressureAdded;
    }

    @Override
    public long removePressure(String name, Pressure pressure, long amount, boolean commit) {
        long amountRemoved = 0;
        if (amount > 0) {
            long currentAmount = this.getPressure(name, pressure);
            if (currentAmount > pressure.getMinPressure()) {
                amountRemoved = Math.min(amount, currentAmount - pressure.getMinPressure());
                if (commit) {
                    pressures.put(name, pressure, currentAmount - amountRemoved);
                }
            }
        }

        return amountRemoved;
    }

    @Override
    public Map<Pressure, Long> getPressuresFor(String name) {
        return pressures.row(name);
    }

    @Override
    public long getPressure(String name, Pressure pressure) {
        return pressures.get(name, pressure);
    }

    @Override
    public void emptyPressure(String name, Pressure pressure) {
        pressures.put(name, pressure, 0L);
    }

    @Override
    public CompoundNBT serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {

    }


}
