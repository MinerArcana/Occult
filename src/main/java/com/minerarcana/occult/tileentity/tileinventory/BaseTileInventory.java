package com.minerarcana.occult.tileentity.tileinventory;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public abstract class BaseTileInventory extends BaseTileEntity {

	protected SimpleItemStackHandler itemHandler = createItemHandler();
	private final LazyOptional<IItemHandler> automationItemHandler = LazyOptional.of(() -> itemHandler);

	public BaseTileInventory(TileEntityType<?> type) {
		super(type);
	}

	@Override
	public void readPacketNBT(CompoundNBT par1NBTTagCompound) {
		itemHandler = createItemHandler();
		itemHandler.deserializeNBT(par1NBTTagCompound);
	}

	@Override
	public void writePacketNBT(CompoundNBT par1NBTTagCompound) {
		par1NBTTagCompound.merge(itemHandler.serializeNBT());
	}

	public abstract int getSizeInventory();

	protected SimpleItemStackHandler createItemHandler() {
		return new SimpleItemStackHandler(this, true);
	}

	public IItemHandlerModifiable getItemHandler() {
		return itemHandler;
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
		return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, automationItemHandler);
	}


	protected static class SimpleItemStackHandler extends ItemStackHandler {

		private final boolean allowWrite;
		private final BaseTileInventory tile;

		public SimpleItemStackHandler(BaseTileInventory inv, boolean allowWrite) {
			super(inv.getSizeInventory());
			this.allowWrite = allowWrite;
			tile = inv;
		}

		@Nonnull
		@Override
		public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
			if(allowWrite) {
				return super.insertItem(slot, stack, simulate);
			} else return stack;
		}

		@Nonnull
		@Override
		public ItemStack extractItem(int slot, int amount, boolean simulate) {
			if(allowWrite) {
				return super.extractItem(slot, amount, simulate);
			} else return ItemStack.EMPTY;
		}

		@Override
		public void onContentsChanged(int slot) {
			tile.markDirty();
		}
	}
}