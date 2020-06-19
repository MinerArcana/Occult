package minerarcana.occult.api.worldpressure;

import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import minerarcana.occult.api.PressureType;
import minerarcana.occult.content.OccultRegistries;
import minerarcana.occult.util.NBTHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.math.ChunkPos;

import java.util.Map;


public class WorldPressureStorage implements IWorldPressure {

    public int capacity;
    private final Map<ChunkPos, Object2IntMap<PressureType>> worldPressureMap;

    public WorldPressureStorage() {
        this(1000);
    }

    public WorldPressureStorage(int capacity) {
        this.capacity = capacity;
        this.worldPressureMap = Maps.newHashMap();
    }

    @Override
    public int addToChunk(ChunkPos chunk, PressureType pressureType, int amount) {
        ensureExists(chunk);
        Object2IntMap<PressureType> pressureMap = getAllPressureInChunk(chunk);
        int amountAdded;
        int cp = pressureMap.get(pressureType);
        int add = amount + cp;
        if (add <= capacity) {
            pressureMap.put(pressureType, add);
            amountAdded = add;
        } else {
            fillSinglePressure(chunk, pressureType);
            amountAdded = capacity;
        }
        setAllPressureInChunk(chunk, pressureMap);
        return amountAdded;
    }

    @Override
    public int removeFromChunk(ChunkPos chunk, PressureType pressureType, int amount) {
        ensureExists(chunk);
        Object2IntMap<PressureType> pressureMap = getAllPressureInChunk(chunk);
        int amountRemoved;
        int cp = pressureMap.get(pressureType);
        int remove = cp - amount;
        if (remove >= 0) {
            pressureMap.put(pressureType, remove);
            amountRemoved = remove;
        } else {
            emptySinglePressure(chunk, pressureType);
            amountRemoved = 0;
        }
        setAllPressureInChunk(chunk, pressureMap);
        return amountRemoved;
    }

    @Override
    public void emptySinglePressure(ChunkPos chunk, PressureType pressureType) {
        ensureExists(chunk);
        Object2IntMap<PressureType> pressureMap = getAllPressureInChunk(chunk);
        pressureMap.put(pressureType, 0);
        setAllPressureInChunk(chunk, pressureMap);
    }

    @Override
    public void fillSinglePressure(ChunkPos chunk, PressureType pressureType) {
        ensureExists(chunk);
        Object2IntMap<PressureType> pressureMap = getAllPressureInChunk(chunk);
        pressureMap.put(pressureType, capacity);
        setAllPressureInChunk(chunk, pressureMap);
    }

    @Override
    public void emptyChunk(ChunkPos chunk) {
        Object2IntMap<PressureType> pressureMap = getAllPressureInChunk(chunk);
        for (PressureType type : OccultRegistries.PRESSURE.getValues()) {
            pressureMap.put(type, 0);
        }
        setAllPressureInChunk(chunk, pressureMap);
    }

    @Override
    public void fillChunk(ChunkPos chunk) {
        Object2IntMap<PressureType> pressureMap = getAllPressureInChunk(chunk);
        for (PressureType type : OccultRegistries.PRESSURE.getValues()) {
            pressureMap.put(type, capacity);
        }
        setAllPressureInChunk(chunk, pressureMap);
    }

    @Override
    public Object2IntMap<PressureType> getAllPressureInChunk(ChunkPos chunk) {
        ensureExists(chunk);
        return worldPressureMap.get(chunk);
    }

    public int setSinglePressureInChunk(ChunkPos chunk, PressureType type, int amount) {
        ensureExists(chunk);
        Object2IntMap<PressureType> pressureMap = getAllPressureInChunk(chunk);
        pressureMap.put(type, Math.min(amount, capacity));
        setAllPressureInChunk(chunk, pressureMap);
        return Math.min(amount, capacity);
    }

    public void setAllPressureInChunk(ChunkPos chunk, Object2IntMap<PressureType> pressureMap) {
        worldPressureMap.put(chunk, pressureMap);
    }

    @Override
    public int getChunkPressureFromType(ChunkPos chunk, PressureType pressureType) {
        ensureExists(chunk);
        return getAllPressureInChunk(chunk).get(pressureType);
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT compound = new CompoundNBT();
        ListNBT chunkPressuresNBT = new ListNBT();
        for (Map.Entry<ChunkPos, Object2IntMap<PressureType>> pressureEntry : worldPressureMap.entrySet()) {
            CompoundNBT pressureCompound = new CompoundNBT();
            Object2IntMap<PressureType> pressureMap = getAllPressureInChunk(pressureEntry.getKey());
            pressureCompound.putLong("chunkPos", pressureEntry.getKey().asLong());
            for (PressureType type : pressureMap.keySet()) {
                pressureCompound.putInt(type.getRegistryName().getPath() + "Pressure", pressureMap.get(type));
            }
            chunkPressuresNBT.add(pressureCompound);
        }
        compound.put("chunkPressureNBT", chunkPressuresNBT);
        return compound;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        NBTHelper.fromListNBT(nbt, "chunkPressureNBT", this::pressureMapParsing);
    }

    private Object2IntMap<PressureType> pressureMapParsing(CompoundNBT nbt) {
        ChunkPos chunkPos = new ChunkPos(nbt.getLong("chunkPos"));
        for (PressureType type : OccultRegistries.PRESSURE) {
            setSinglePressureInChunk(chunkPos, type, nbt.getInt(type.getRegistryName().getPath()));
        }
        return getAllPressureInChunk(chunkPos);
    }

    private void ensureExists(ChunkPos chunk) {
        if (!worldPressureMap.containsKey(chunk)) {
            emptyChunk(chunk);
        }
    }

}