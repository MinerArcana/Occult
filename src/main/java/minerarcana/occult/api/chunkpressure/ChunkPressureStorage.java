package minerarcana.occult.api.chunkpressure;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import minerarcana.occult.api.PressureType;
import minerarcana.occult.api.worldpressure.IWorldPressure;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.LazyOptional;

import static minerarcana.occult.api.worldpressure.WorldPressureCap.getWorldPressure;

public class ChunkPressureStorage implements IChunkPressure {

    private final LazyOptional<IWorldPressure> lazyWorld;
    private final Chunk chunk;


    public ChunkPressureStorage(Chunk chunk) {
        this.chunk = chunk;
        this.lazyWorld = getWorldPressure(chunk.getWorld());
    }

    @Override
    public int set(PressureType pressureType, int amount) {
        return lazyWorld.map(world -> world.setSinglePressureInChunk(chunk.getPos(),pressureType,amount)).orElse(amount);
    }

    @Override
    public int add(PressureType pressureType, int amount) {
        return lazyWorld.map(world -> world.addToChunk(chunk.getPos(),pressureType,amount)).orElse(amount);
    }

    @Override
    public int remove(PressureType pressureType, int amount) {
        return lazyWorld.map(world -> world.removeFromChunk(chunk.getPos(),pressureType,amount)).orElse(amount);
    }

    @Override
    public void empty(PressureType pressureType) {
        lazyWorld.ifPresent(world -> world.emptyChunk(chunk.getPos()));
    }

    @Override
    public void fill(PressureType pressureType) {
        lazyWorld.ifPresent(world -> world.fillChunk(chunk.getPos()));
    }

    @Override
    public Object2IntMap<PressureType> getAllPressure() {
        return lazyWorld.map(world -> world.getAllPressureInChunk(chunk.getPos())).orElse(null);
    }

    @Override
    public int getPressureFromType(PressureType pressureType) {
        return lazyWorld.map(world -> world.getChunkPressureFromType(chunk.getPos(), pressureType)).orElse(0);
    }


}