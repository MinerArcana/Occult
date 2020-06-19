package minerarcana.occult.api.worldpressure;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import minerarcana.occult.api.PressureType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.common.util.INBTSerializable;

public interface IWorldPressure extends INBTSerializable<CompoundNBT> {

    int addToChunk(ChunkPos chunk, PressureType pressureType, int amount);
    int removeFromChunk(ChunkPos chunk, PressureType pressureType, int amount);
    void emptySinglePressure(ChunkPos chunk, PressureType pressureType);
    void fillSinglePressure(ChunkPos chunk, PressureType pressureType);
    void emptyChunk(ChunkPos chunk);
    int setSinglePressureInChunk(ChunkPos chunk, PressureType pressureType, int amount);
    void fillChunk(ChunkPos chunk);
    Object2IntMap<PressureType> getAllPressureInChunk(ChunkPos chunk);
    int getChunkPressureFromType(ChunkPos chunk, PressureType pressureType);

}
