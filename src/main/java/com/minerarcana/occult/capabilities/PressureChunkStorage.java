package com.minerarcana.occult.capabilities;

import com.minerarcana.occult.Occult;
import com.minerarcana.occult.api.pressure.IChunkPressure;
import com.minerarcana.occult.api.pressure.PressureStorage;
import com.minerarcana.occult.api.pressure.PressureTypes;
import com.minerarcana.occult.util.network.UpdateChunkEnergyValueMessage;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fml.network.PacketDistributor;

public class PressureChunkStorage extends PressureStorage implements IChunkPressure, INBTSerializable<IntNBT> {

    private final World world;

    private final ChunkPos chunkPos;

    public PressureChunkStorage(final int capacity, final PressureTypes type, final World world, final ChunkPos chunkPos) {
        super(capacity, type);
        this.world = world;
        this.chunkPos = chunkPos;
        pressure = capacity;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public ChunkPos getChunkPos() {
        return chunkPos;
    }

    @Override
    public IntNBT serializeNBT() {
        return new IntNBT(getPressure());
    }

    @Override
    public void deserializeNBT(IntNBT nbt) {
        pressure = nbt.getInt();
    }

    @Override
    public int addPressure(final int maxAdd, PressureTypes type, final boolean simulate) {
        final int pressureAdded = super.addPressure(maxAdd,type, simulate);
        if (!simulate && pressureAdded != 0) {
            onPressureChanged();
        }

        return pressureAdded;
    }

    public void setPressure(final int pressure) {
        this.pressure = pressure;
        onPressureChanged();
    }

    protected void onPressureChanged() {
        final World world = getWorld();
        final ChunkPos chunkPos = getChunkPos();

        if (world.isRemote) return;

        if (world.getChunkProvider().isChunkLoaded(chunkPos)) {  // Don't load the chunk when reading from NBT
            final Chunk chunk = world.getChunk(chunkPos.x, chunkPos.z);
            chunk.markDirty();
            Occult.network.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), new UpdateChunkEnergyValueMessage(this));
        }
    }
}
