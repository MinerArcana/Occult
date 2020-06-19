package minerarcana.occult.api.chunkpressure;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import minerarcana.occult.api.PressureType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import javax.annotation.Nonnull;

import static minerarcana.occult.api.worldpressure.WorldPressureCap.getWorldPressure;

public class ChunkPressureStorage implements IChunkPressure {

    private final Chunk chunk;

    public ChunkPressureStorage(Chunk chunk) {
        this.chunk = chunk;
    }

    @Override
    public int set(PressureType pressureType, int amount) {
        return getWorldPressure(chunk.getWorld()).map(world -> world.setSinglePressureInChunk(chunk.getPos(),pressureType,amount)).orElse(amount);
    }

    @Override
    public int add(PressureType pressureType, int amount) {
        return getWorldPressure(chunk.getWorld()).map(world -> world.addToChunk(chunk.getPos(),pressureType,amount)).orElse(amount);
    }

    @Override
    public int remove(PressureType pressureType, int amount) {
        return getWorldPressure(chunk.getWorld()).map(world -> world.removeFromChunk(chunk.getPos(),pressureType,amount)).orElse(amount);
    }

    @Override
    public void empty(PressureType pressureType) {
        getWorldPressure(chunk.getWorld()).ifPresent(world -> world.emptyChunk(chunk.getPos()));
    }

    @Override
    public void fill(PressureType pressureType) {
        getWorldPressure(chunk.getWorld()).ifPresent(world -> world.fillChunk(chunk.getPos()));
    }

    @Nonnull
    @Override
    public Object2IntMap<PressureType> getAllPressure() {
        Object2IntMap<PressureType> pressureMap = new Object2IntOpenHashMap<>();
        return getWorldPressure(chunk.getWorld()).map(world -> world.getAllPressureInChunk(chunk.getPos())).orElse(pressureMap);
    }

    @Override
    public int getPressureFromType(PressureType pressureType) {
        return getWorldPressure(chunk.getWorld()).map(world -> world.getChunkPressureFromType(chunk.getPos(), pressureType)).orElse(0);
    }


}