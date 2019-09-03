package com.minerarcana.occult.tileentity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.minerarcana.occult.tileentity.OccultTileEntities.RITUAL_TILE;

public class FireRitualTileEntity extends TileEntity {

    private ItemStackHandler handler;

    public FireRitualTileEntity() {
        super(RITUAL_TILE);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return LazyOptional.of(() -> (T) new ItemStackHandler(1));
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void read(CompoundNBT nbt) {
        CompoundNBT invTag = nbt.getCompound("inf");
        getHandler().deserializeNBT(invTag);
        super.read(nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        CompoundNBT compound = getHandler().serializeNBT();
        nbt.put("inv", compound);

        return super.write(nbt);
    }

    private ItemStackHandler getHandler(){
        if(handler == null){
            handler = new ItemStackHandler(1);
        }
        return handler;
    }

    public boolean isActive(boolean active)
    {
        return false;
    }



}
