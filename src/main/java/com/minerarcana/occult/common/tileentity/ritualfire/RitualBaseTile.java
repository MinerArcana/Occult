package com.minerarcana.occult.common.tileentity.ritualfire;

import com.google.common.collect.Maps;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tags.Tag;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Map;

import static com.minerarcana.occult.common.tileentity.OccultTileEntities.RITUALBASETILE;
import static net.minecraft.tags.ItemTags.LOGS;

public class RitualBaseTile extends TileEntity implements INamedContainerProvider {

    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);

    private RitualBaseTile master;

    private int counter;

    private int burnTime;

    private boolean isOn;
    private boolean isMultiBlock;
    private boolean isActive;
    private boolean isCenter;
    private boolean isMaster;
    private boolean isSlave;

    public RitualBaseTile() {
        super(RITUALBASETILE);
    }


    public boolean isActive() {
        if (isMultiBlock = true) {
            if (isOn = true) {
                isActive = true;
            }
        } else {
            isActive = false;
        }
        return isActive;
    }

    public boolean isMultiblock() {
        return this.isMultiBlock;
    }

    public boolean isOn() {
        return this.burnTime > 0;
    }

    public boolean isCenter(BlockState state, World world, BlockPos pos) {
        BlockPos east = pos.east();
        BlockPos west = pos.west();
        BlockPos north = pos.north();
        BlockPos south = pos.south();
        BlockState state1 = world.getBlockState(east);
        BlockState state2 = world.getBlockState(west);
        BlockState state3 = world.getBlockState(north);
        BlockState state4 = world.getBlockState(south);


        if(state1.getBlock() instanceof RitualBase)
        {
            if(state2.getBlock() instanceof RitualBase)
            {
                if(state3.getBlock() instanceof RitualBase)
                {
                    if(state4.getBlock() instanceof RitualBase)
                    {

                    }
                }
            }
        }
        else{isCenter = false;}
            return this.isCenter;
    }

    public boolean isMaster() {
        if(this.isMultiBlock){
        if (this.isCenter) {
            isMaster = true;
            }
        }
        else {
            isMaster = false;
        }
        return isMaster;
    }

    public boolean isSlave() {
        if (!this.isMaster) {
            isSlave = true;
        }
        else{
            isSlave = false;
        }
        return isSlave;
    }

    public RitualBaseTile getMaster() {
        if (this.isMaster = true) {
            return this.master;
        }
        return this.master;
    }

    @Override
    public void read(CompoundNBT nbt) {
        CompoundNBT invTag = nbt.getCompound("inv");
        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(invTag));

        super.read(nbt);

        if (nbt.hasUniqueId("isMaster")) {
            this.isMaster = nbt.getBoolean("isMaster");
            this.burnTime = nbt.getInt("BurnTime");
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
        nbt.putInt("BurnTime", this.burnTime);

        return super.write(nbt);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            if (isMaster) {
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

            @Override
            public int getSlotLimit(int slot)
            {
                return 9;
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return stack.getItem().getTags() == LOGS;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!stack.getItem().getTags().contains(LOGS)) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };


    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        write(nbt);
        return new SUpdateTileEntityPacket(this.pos, 1, nbt);
    }

    public static Map<Item, Integer> getBurnTimes() {
        Map<Item, Integer> map = Maps.newLinkedHashMap();
        addItemTagBurnTime(map, LOGS, 300);
        return map;
    }

    private static void addItemTagBurnTime(Map<Item, Integer> map, Tag<Item> itemTag, int burntime) {
        for (Item item : itemTag.getAllElements()) {
            map.put(item, burntime);
        }
    }


    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(getType().getRegistryName().getPath());
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new RitualBaseContainer(i, world, pos, playerInventory);
    }
}

