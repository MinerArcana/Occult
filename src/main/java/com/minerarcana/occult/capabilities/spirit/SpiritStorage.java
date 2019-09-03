package com.minerarcana.occult.capabilities.spirit;

import net.minecraft.nbt.CompoundNBT;

public class SpiritStorage implements SpiritPressure {

    protected int energy,capacity,maxInsert,maxExtract;


    public SpiritStorage(int capacity, int maxInsert, int maxExtract)
    {
        this.capacity = capacity;
        this.maxInsert = maxInsert;
        this.maxExtract = maxExtract;

    }

    @Override
    public int insertSpiritualPressure(int maxInsert, boolean simulate) {
        if(!canReceiveSpiritualPressure()) return 0;
        int received = Math.min(capacity - energy, Math.min(this.maxInsert, maxInsert));
        if(!simulate) energy += received;
        return received;
    }

    @Override
    public int extractSpiritualPressure(int maxExtract, boolean simulate) {
        if(!canExtractSpiritualPressure()) return 0;
        int extracted = Math.min(capacity, Math.min(this.maxExtract, maxExtract));
        if(!simulate) energy -= extracted;
        return extracted;
    }

    @Override
    public int getSpiritualPressure() {
        return energy;
    }

    @Override
    public int getMaxSpiritualPressure() {
        return capacity;
    }

    @Override
    public boolean canExtractSpiritualPressure() {
        return this.maxExtract > 0;
    }

    @Override
    public boolean canReceiveSpiritualPressure() {
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
