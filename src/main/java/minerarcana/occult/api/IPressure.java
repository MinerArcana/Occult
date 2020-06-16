package minerarcana.occult.api;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IPressure extends INBTSerializable<CompoundNBT> {

    int add(PressureType pressureType, int amount);
    int remove(PressureType pressureType, int amount);
    void empty(PressureType pressureType);
    void fill(PressureType pressureType);
    Object2IntMap<PressureType> getAllPressure();
    int getPressureFromType(PressureType pressureType);

}