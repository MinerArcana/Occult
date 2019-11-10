package com.minerarcana.occult.multiblocksystem;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;



/**
 * @author SkySom
 */
public abstract class TileEntityBase extends TileEntity {

    public TileEntityBase(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void read(CompoundNBT compound) {
        this.readFromDisk(compound);
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound = this.writeToDisk(compound);
        return super.write(compound);
    }

    protected abstract void readFromDisk(CompoundNBT data);

    protected abstract CompoundNBT writeToDisk(CompoundNBT data);

    public void sendBlockUpdate() {
        if (!this.getWorld().isRemote) {
            this.getWorld().notifyBlockUpdate(getPos(), getWorld().getBlockState(pos), getWorld().getBlockState(pos), 3);
        }
    }

}
