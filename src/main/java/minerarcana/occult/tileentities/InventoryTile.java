package minerarcana.occult.tileentities;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class InventoryTile extends TileEntity implements ITickableTileEntity {

    private final int size;
    private int timer;
    private boolean requiresUpdate = true;

    protected LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);

    public InventoryTile(TileEntityType<?> tileEntityTypeIn, int size) {
        super(tileEntityTypeIn);
        this.size = size;
    }

    @Override
    public void tick() {
        if(world != null) {
            if (requiresUpdate) {
                updateTile();
                requiresUpdate = false;
            }
        }
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    public LazyOptional<IItemHandler> getHandler() {
        return handler;
    }

    private IItemHandler createHandler() {
        return new ItemStackHandler(size) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }
        };
    }

    public ItemStack getItemInSlot(int slot) {
        return handler.map(inventory -> inventory.getStackInSlot(slot)).orElse(ItemStack.EMPTY);
    }

    public ItemStack insertItem(int slot, ItemStack stack) {
        ItemStack itemIn = stack.copy();
        stack.shrink(itemIn.getCount());
        requiresUpdate = true;
        return handler.map(inventory -> inventory.insertItem(slot, itemIn, false)).orElse(ItemStack.EMPTY);
    }

    public ItemStack extractItem(int slot) {
        int count = getItemInSlot(slot).getCount();
        requiresUpdate = true;
        return handler.map(inventory -> inventory.extractItem(slot, count, false)).orElse(ItemStack.EMPTY);
    }

    public int getSize() {
        return size;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        ListNBT list = compound.getList("Items", 10);
        for (int x = 0; x < list.size(); ++x) {
            CompoundNBT nbt = list.getCompound(x);
            int r = nbt.getByte("Slot") & 255;
            handler.ifPresent(inventory -> {
                int invslots = inventory.getSlots();
                if (r >= 0 && r < invslots) {
                    inventory.insertItem(r, ItemStack.read(nbt), false);
                }
            });
        }
        requiresUpdate = true;
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
                    nbt.putByte("Slot", (byte) x);
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

    public void updateTile() {
        requestModelDataUpdate();
        this.markDirty();
        if (this.getWorld() != null) {
            this.getWorld().notifyBlockUpdate(pos, this.getBlockState(), this.getBlockState(), 3);
        }
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.getPos(), -1, this.getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        handleUpdateTag(pkt.getNbtCompound());
    }

    @Override
    @Nonnull
    public CompoundNBT getUpdateTag() {
        return this.serializeNBT();
    }

    @Override
    public void handleUpdateTag(CompoundNBT tag) {
        deserializeNBT(tag);
    }

}
