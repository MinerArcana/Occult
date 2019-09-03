package com.minerarcana.occult.capabilities.nature;

import net.minecraft.nbt.CompoundNBT;

public class NatureStorage implements NaturePressure {

    protected int energy,capacity,maxInsert,maxExtract;



    public NatureStorage(int capacity, int maxInsert, int maxExtract)
    {
        this.capacity = capacity;
        this.maxInsert = maxInsert;
        this.maxExtract = maxExtract;

    }


    @Override
    public int insertNaturalPressure(int maxInsert, boolean simulate) {
        if(!canReceiveNaturalPressure()) return 0;
        int received = Math.min(capacity - energy, Math.min(this.maxInsert, maxInsert));
        if(!simulate) energy += received;
        return received;
    }

    @Override
    public int extractNaturalPressure(int maxExtract, boolean simulate) {
        if(!canExtractNaturalPressure()) return 0;
        int extracted = Math.min(capacity, Math.min(this.maxExtract, maxExtract));
        if(!simulate) energy -= extracted;
        return extracted;
    }

    @Override
    public int getNaturalPressure() {
        return energy;
    }

    @Override
    public int getMaxNaturalPressure() {
        return capacity;
    }

    @Override
    public boolean canExtractNaturalPressure() {
        return this.maxExtract > 0;
    }

    @Override
    public boolean canReceiveNaturalPressure() {
        return this.maxInsert > 0;
    }

    public void setEnergy(final int energy) {
        this.energy = energy;
    }

    public void readNBT(CompoundNBT nbt)
    {
        energy = nbt.getInt("energy");
        capacity = nbt.getInt("capacity");
        maxInsert = nbt.getInt("maxinsert");
        maxExtract = nbt.getInt("maxextract");
    }


    public CompoundNBT serializeNBT()
    {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("energy", energy);
        nbt.putInt("capacity", capacity);
        nbt.putInt("maxinsert", maxInsert);
        nbt.putInt("maxextract", maxExtract);
        return nbt;
    }
}
