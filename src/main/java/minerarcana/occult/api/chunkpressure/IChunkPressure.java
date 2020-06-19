package minerarcana.occult.api.chunkpressure;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import minerarcana.occult.api.PressureType;

import javax.annotation.Nonnull;

public interface IChunkPressure {

    int add(PressureType pressureType, int amount);
    int set(PressureType pressureType, int amount);
    int remove(PressureType pressureType, int amount);
    void empty(PressureType pressureType);
    void fill(PressureType pressureType);
    @Nonnull
    Object2IntMap<PressureType> getAllPressure();
    int getPressureFromType(PressureType pressureType);

}