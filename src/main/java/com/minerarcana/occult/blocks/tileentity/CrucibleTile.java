package com.minerarcana.occult.blocks.tileentity;

import com.minerarcana.occult.Occult;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.concurrent.atomic.AtomicReference;

import static com.minerarcana.occult.content.OccultTileEntity.CRUCIBLE_TYPE;

public class CrucibleTile extends TileEntity {

    //Inventory Cap Optional!
    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);

    public CrucibleTile() {
        super(CRUCIBLE_TYPE.get());
    }

    

    //Inventory Handling + Serializing!
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    private IItemHandler createHandler() {
        return new ItemStackHandler(3) {
            @Override
            protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
                return 1;
            }

            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }
        };
    }

    public void insertItemMethod(int slot, ItemStack stack, boolean simulate) {
        handler.ifPresent(inventory -> {
            inventory.insertItem(slot, stack.copy(), simulate);
        });
    }

    public ItemStack extractItemMethod(int slot, int amount, boolean simulate) {
        AtomicReference<ItemStack> stack = null;
        handler.ifPresent(inventory -> {
            stack.set(inventory.extractItem(slot, amount, simulate));
        });
        return stack.get();
    }

    public void extractInsertItemMethod(PlayerEntity player, Hand hand) {
        handler.ifPresent(inventory -> {
            ItemStack held = player.getHeldItem(hand);
            boolean used = false;
            int i = inventory.getSlots();
            if (!held.isEmpty()) {
                for (int x = 0; x < i; ++x) {
                    if (!used) {
                        if (inventory.getStackInSlot(x).isEmpty()) {
                            ItemStack heldCopy = held.copy();
                            heldCopy.setCount(1);
                            inventory.insertItem(x, heldCopy, false);
                            held.shrink(1);
                            used = true;
                        }
                    }
                }
            } else {
                for (int x = 0; x < i; ++x) {
                    if (!used) {
                        if (!inventory.getStackInSlot(x).isEmpty()) {
                            ItemStack item = inventory.extractItem(x, 1, false);
                            player.addItemStackToInventory(item);
                            used = true;
                        }
                    }
                }
            }
        });
    }

    @Override
    public void read(CompoundNBT tag) {
        super.read(tag);
        ListNBT list = tag.getList("Items", 10);
        for (int x = 0; x < list.size(); ++x) {
            CompoundNBT nbt = list.getCompound(x);
            int r = nbt.getByte("Slot") & 255;
            handler.ifPresent(inventory -> {
                int invslots = inventory.getSlots();
                if(r >= 0 && r < invslots) {
                    inventory.insertItem(r, ItemStack.read(nbt), false);
                }
            });
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        ListNBT list = new ListNBT();
        handler.ifPresent(inventory -> {
            int slots = inventory.getSlots();
            for (int x = 0; x < slots; ++x) {
                ItemStack stack = inventory.getStackInSlot(x);
                if (!stack.isEmpty()) {
                    CompoundNBT nbt = new CompoundNBT();
                    nbt.putByte("Slot", (byte)x);
                    stack.write(nbt);
                    list.add(nbt);
                }
            }
        });
        if (!list.isEmpty()) {
            compound.put("Items", list);
        }
        return compound;
    }
}
