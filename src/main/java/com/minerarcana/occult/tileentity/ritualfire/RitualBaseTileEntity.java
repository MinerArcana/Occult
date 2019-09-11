package com.minerarcana.occult.tileentity.ritualfire;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.minerarcana.occult.tileentity.OccultTileEntities.RITUALBASETILE;

public class RitualBaseTileEntity extends TileEntity {

    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);

    private RitualBaseTileEntity master;

    private int counter;

    private boolean isOn;
    private boolean isMultiBlock;
    private boolean isActive;
    private boolean isCenter;
    private boolean isMaster;
    private boolean isSlave;

    public RitualBaseTileEntity()  {
        super(RITUALBASETILE);
    }



    public boolean isActive()
    {
        if(isMultiBlock = true){
            if(isOn = true) {
                this.isActive = true;
                return this.isActive;
            }
        }
        else{
            this.isActive = false;
        }
        return this.isActive;
    }

    public boolean isMultiblock()
    {
        return this.isMultiBlock;
    }

    public boolean isOn()
    {
        if()
        return this.isOn;
    }

    public boolean isCenter(BlockPos pos)
    {

        return this.isCenter;
    }

    public boolean isMaster()
    {
        if(this.isCenter = true){
            this.isMaster = true;
            return this.isMaster;
        }
        else{ this.isMaster = false; }

        return this.isSlave;
    }

    public boolean isSlave() {
        if(this.isMaster = false){
            isSlave = true;
            return this.isSlave;
        }
        if(this.isMaster = true);{
            isSlave = false;
        }
        return this.isMaster;
    }

    public RitualBaseTileEntity getMaster()
    {
        if(this.isMaster = true) {
            return this.master;
        }
        return this.master;
    }

    @Override
    public void read(CompoundNBT nbt) {
        CompoundNBT invTag = nbt.getCompound("inv");
        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(invTag));

        super.read(nbt);

        if (nbt.hasUniqueId("isMaster"))
        {
            this.isMaster = nbt.getBoolean("isMaster");
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        handler.ifPresent(h -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
            nbt.put("inv", compound);
        });
        nbt.putBoolean("isMaster", this.isMaster);
        nbt.putBoolean("isSlave", this.isSlave);
        nbt.putInt("counter", counter);

        return super.write(nbt);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(isActive) {
            if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
                return handler.cast();
            }
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

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        CompoundNBT nbt = new CompoundNBT();
        write(nbt);
        int metadata = get();
        return new SUpdateTileEntityPacket(this.pos, metadata, nbt);
    }


}

