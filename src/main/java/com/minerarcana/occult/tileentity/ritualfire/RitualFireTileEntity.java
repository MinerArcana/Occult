package com.minerarcana.occult.tileentity.ritualfire;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.minerarcana.occult.tileentity.OccultTileEntities.RITUALBASETILE;
import static com.minerarcana.occult.tileentity.OccultTileEntities.RITUALFIRETILE;

public class RitualFireTileEntity extends TileEntity {

    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);

    private int counter;

    public RitualFireTileEntity() {
        super(RITUALFIRETILE);
    }

    @Override
    public void read(CompoundNBT nbt) {
        CompoundNBT invTag = nbt.getCompound("inv");
        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(invTag));
        super.read(nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        handler.ifPresent(h -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
            nbt.put("inv", compound);
        });
        nbt.putInt("counter", counter);
        return super.write(nbt);
    }



    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    private IItemHandler createHandler() {
        return new ItemStackHandler(1) {

            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }


        };
    }


}
