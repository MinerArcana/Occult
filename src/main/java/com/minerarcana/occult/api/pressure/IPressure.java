package com.minerarcana.occult.api.pressure;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.Map;

public interface IPressure extends INBTSerializable<CompoundNBT>
{
    /**
     * @param pressure name of the pressure
     * @param amount   number of pressure to add
     * @param commit   true should commit the change
     * @return amount actually added
     */

    long addPressure(String name, Pressure pressure, long amount, boolean commit);

    /**
     * @param pressure name of the pressure
     * @param amount   number of pressure to remove
     * @param commit   true should commit the change
     * @return amount actually removed
     */
    long removePressure(String name, Pressure pressure, long amount, boolean commit);

    Map<Pressure, Long> getPressuresFor(String name);


    long getPressure(String name, Pressure pressure);

    void emptyPressure(String name, Pressure pressure);

    default long addPressure(TileEntity entity, String name, Pressure pressure, long amount, boolean commit) {
        return addPressure(entity.getTileEntity(), name, pressure, amount, commit);
    }

    default long addPressure(World world, BlockPos pos, String name, Pressure pressure, long amount, boolean commit) {
        return addPressure(world, pos, name, pressure, amount, commit);
    }

    default long removePressure(TileEntity entity, String name, Pressure pressure, long amount, boolean commit) {
        return removePressure(entity.getTileEntity(), name, pressure, amount, commit);
    }

    default long removePressure(World world, BlockPos pos, String name, Pressure pressure, long amount, boolean commit) {
        return removePressure(world, pos, name, pressure, amount, commit);
    }

    default long getPressure(TileEntity entity, String name, Pressure pressure) {
        return getPressure(entity.getTileEntity(), name, pressure);
    }

    default long getPressure(World world, BlockPos pos, String name, Pressure pressure) {
        return getPressure(world, pos, name, pressure);
    }

    default void emptyPressure(TileEntity entity, String name, Pressure pressure) {
        emptyPressure(entity.getTileEntity(), name, pressure);
    }


    default void emptyPressure(World world, BlockPos pos, String name, Pressure pressure)
    {
        emptyPressure(world, pos, name ,pressure);
    }
}
