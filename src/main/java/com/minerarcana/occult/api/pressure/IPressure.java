package com.minerarcana.occult.api.pressure;

import com.minerarcana.occult.api.PressureType;
import net.minecraft.block.Block;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.Map;


public interface IPressure extends INBTSerializable<CompoundNBT> {

    int add(PressureType pressureType, int amount);
    int remove(PressureType pressureType, int amount);
    void empty(PressureType pressureType);
    void fill(PressureType pressureType);
    int getPressureAmount(PressureType pressureType);
}
