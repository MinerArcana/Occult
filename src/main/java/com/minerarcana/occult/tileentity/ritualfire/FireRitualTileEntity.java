package com.minerarcana.occult.tileentity.ritualfire;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.minerarcana.occult.tileentity.OccultTileEntities.RITUALTILE;

public class FireRitualTileEntity extends TileEntity {

    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);

    public FireRitualTileEntity() {
        super(RITUALTILE);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void read(CompoundNBT nbt) {
        CompoundNBT invTag = nbt.getCompound("inf");
        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(invTag));
        super.read(nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        handler.ifPresent(h -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
            nbt.put("inv", compound);
        });

        return super.write(nbt);
    }

    private IItemHandler createHandler(){

           return new ItemStackHandler(1);
    }

    public boolean isActive(boolean active)
    {
        return false;
    }



}
