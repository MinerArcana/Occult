package com.minerarcana.occult.api.capabilities;

import com.google.common.collect.Maps;
import com.minerarcana.occult.Occult;
import com.minerarcana.occult.api.PressureType;
import com.minerarcana.occult.api.pressure.PressureStorage;
import com.minerarcana.occult.util.network.UpdateChunkPressureValueMessage;
import net.minecraft.nbt.IntNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.Map;

public class PressureTileStorage extends PressureStorage implements INBTSerializable<IntNBT>{

    public final Map<PressureType, Integer> pressures;

    private final World world;
    private final TileEntity tile;

    public PressureTileStorage(final int capacity, final PressureType type, final World world, final TileEntity tile) {
        super(capacity, type);
        this.world = world;
        this.tile = tile;
        pressure = capacity;
        this.pressures = Maps.newHashMap();
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public TileEntity getTileEntity() {
        return tile;
    }

    @Override
    public BlockPos getTilePos() {
        return tile.getPos();
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
        final BlockPos pos = getTilePos();
        final Chunk chunk = world.getChunk(pos.getX(),  pos.getZ());

        if (world.isRemote) return;
        if (world.getChunkProvider().isChunkLoaded(chunk.getPos())) {
            final TileEntity tile = world.getTileEntity(pos);
            tile.markDirty();
        }
    }
}
