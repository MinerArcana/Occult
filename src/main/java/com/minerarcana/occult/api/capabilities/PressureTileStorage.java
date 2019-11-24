package com.minerarcana.occult.api.capabilities;

import com.google.common.collect.Maps;
import com.minerarcana.occult.Occult;
import com.minerarcana.occult.api.pressure.IChunkPressure;
import com.minerarcana.occult.api.pressure.PressureStorage;
import com.minerarcana.occult.api.pressure.PressureType;
import com.minerarcana.occult.util.network.UpdateChunkPressureValueMessage;
import net.minecraft.nbt.IntNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.Map;

public class PressureTileStorage extends PressureStorage implements IChunkPressure, INBTSerializable<IntNBT> {

    public final Map<PressureType, Integer> pressures;

    private final World world;
    private final BlockPos pos;

    public PressureTileStorage( final World world, final int capacity, final PressureType type, final TileEntity tile, final BlockPos pos) {
        super(capacity, type);
        this.world = world;
        this.pos = pos;
        pressure = capacity;
        this.pressures = Maps.newHashMap();
    }

    @Override
    public World getWorld() {
        return world;
    }

    public BlockPos getBlockPos() {
        return pos;
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
    public int addPressure(final int maxAdd, PressureType type, final boolean simulate) {
        final int pressureAdded = super.addPressure(maxAdd, type, simulate);
        if (!simulate && pressureAdded != 0) {
            onPressureChanged();
        }

        return pressureAdded;
    }

    public void setPressure(final int pressure, PressureType type) {
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
            Occult.network.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), new UpdateChunkPressureValueMessage(this));
        }
    }
}
