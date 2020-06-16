package minerarcana.occult.api;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import minerarcana.occult.content.OccultRegistries;
import net.minecraft.nbt.CompoundNBT;

public class PressureStorage implements IPressure {

    public Object2IntMap<PressureType> pressures = new Object2IntOpenHashMap<>();
    public PressureType type;
    public int capacity;

    public PressureStorage() {
        this(1000);
    }

    public PressureStorage(int capacity) {
        this.capacity = capacity;
        for (PressureType type : OccultRegistries.PRESSURE.getValues()) {
            set(type, 0);
        }
    }

    public int set(PressureType pressureType, int amount) {
        if (amount <= capacity) {
            pressures.put(pressureType, amount);
            return amount;
        } else {
            fill(pressureType);
            return capacity;
        }

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
        for (PressureType pressureType : pressures.keySet()) {
            compound.putInt(pressureType.toString(), getPressureFromType(pressureType));
        }
        return compound;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        for (String pressureType : nbt.keySet()) {
            if (!pressureType.isEmpty()) {
                pressures.put(type.getTypeFromName(pressureType), nbt.getInt(pressureType));
            }
        }
    }

    private void ensureExists(PressureType pressureType) {
        if (!pressures.containsKey(pressureType)) {
            set(pressureType, 0);
        }
    }
}