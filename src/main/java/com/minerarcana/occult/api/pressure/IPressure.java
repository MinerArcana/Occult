package com.minerarcana.occult.api.pressure;

import com.minerarcana.occult.api.PressureType;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;


public interface IPressure {

    World getWorld();

    ChunkPos getChunkPos();

    TileEntity getTileEntity();

    BlockPos getTilePos();

    Block getBlock();

    int addPressure(int added, PressureType type, boolean commit);

    int removePressure(int removed, PressureType type, boolean commit);

    int getPressure();

    int getMaxPressure();

    boolean canSendPressure();

    boolean canAcceptPressure();

    PressureType getType();




}
