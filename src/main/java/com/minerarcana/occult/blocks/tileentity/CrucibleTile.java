package com.minerarcana.occult.blocks.tileentity;

import com.minerarcana.occult.api.pressure.PressureType;
import com.minerarcana.occult.api.pressure.pressure.PressureCap;
import com.minerarcana.occult.api.recipes.OccultRecipeTypes;
import com.minerarcana.occult.api.recipes.machines.FluidToItemRecipe;
import com.minerarcana.occult.api.recipes.machines.ItemNFluidToItemRecipe;
import com.minerarcana.occult.api.recipes.machines.ItemToFluidRecipe;
import com.minerarcana.occult.util.lib.OccultTagLib;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.minerarcana.occult.content.OccultTileEntity.CRUCIBLE_TYPE;

public class CrucibleTile extends TileEntity implements ITickableTileEntity {

    //Inventory Cap Optional!
    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);

    private FluidToItemRecipe currentFluidToItemRecipe;
    private ItemToFluidRecipe currentItemToFluidRecipe;
    private ItemNFluidToItemRecipe currentItemNFluidToFluidRecipe;

    private int maxTemp;
    private int minTemp;
    private int meltTime;
    private int maxMeltTime;
    private int fuelTemp = 50;

    public CrucibleTile() {
        super(CRUCIBLE_TYPE.get());
    }

    @Override
    public void tick() {
        setFuelTemp();
        if (hasFuel()) {
            List<ItemStack> itemList = getAllItems();
            if (!itemList.isEmpty()) {
                currentItemToFluidRecipe = world.getRecipeManager()
                        .getRecipes()
                        .stream()
                        .filter(recipe -> recipe.getType().equals(OccultRecipeTypes.CRUCIBLETYPE))
                        .map(recipe -> (ItemToFluidRecipe) recipe)
                        .filter(this::matches)
                        .findFirst()
                        .orElse(null);
                if (currentItemToFluidRecipe != null) {
                    if (canSmelt(currentItemToFluidRecipe) && hotEnough()) {
                        if (getMeltTime() == 0) {
                            getRecipeThings(currentItemToFluidRecipe);
                        } else if (getMeltTime() == getMaxMeltTime()) {
                            this.meltTime = 0;
                            this.smeltRecipe(currentItemToFluidRecipe);
                        } else {
                            ++meltTime;
                        }
                    }
                }
            }
        } else if (!this.hasFuel() && this.meltTime > 0) {
            this.meltTime = MathHelper.clamp(getMeltTime() - 2, 0, getMaxMeltTime());
        }
    }

    private boolean matches(ItemToFluidRecipe crucibleRecipes) {
        return crucibleRecipes.matches(getAllItems());
    }

    private void smeltRecipe(ItemToFluidRecipe recipe) {
        ItemStack alternateOut = recipe.getAlternateOut();
        PressureType type = recipe.getPressureType();
        int pressureIn = recipe.getPressureAmount();
        if(tooHot()){
            clearInv();
            if(alternateOut == null){
                alternateOut = Blocks.COAL_ORE.asItem().getDefaultInstance();
            }
            insertSlotContents(0, alternateOut);
            if(pressureIn !=0) {
                PressureCap.addChunkPressure(world.getChunkAt(pos), type, pressureIn);
            }
        }
        else if(hotEnough()){
            clearInv();
            int size = recipe.getOutputs().size();
            for(int x = 0; x < size; ++x){
                insertSlotContents(x,recipe.getOutputs().get(x));
            }
            if(pressureIn !=0) {
                PressureCap.addChunkPressure(world.getChunkAt(pos), type, pressureIn);
            }
        }
    }


    private void getRecipeThings(ItemToFluidRecipe recipe){
            maxTemp = recipe.getMaxtemp();
            minTemp = recipe.getMintemp();
            maxMeltTime = recipe.getMeltTime();
    }

    private boolean canSmelt(ItemToFluidRecipe recipe) {
                ItemStack outStack = recipe.getRecipeOutput();
                if (outStack.isEmpty())
                    return false;
        return false;
    }

    public int getMeltTime() {
        return meltTime;
    }

    public int getMaxMeltTime() {
        return maxMeltTime;
    }

    private boolean tooHot() {
        if (getFuelTemp() > getMaxTemp()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean hotEnough() {
        if (getFuelTemp() > getMinTemp()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean hasFuel() {
        return getFuelTemp() >= 100;
    }

    public int getFuelTemp() {
        return fuelTemp;
    }

    private void setFuelTemp() {
        BlockPos pos1 = pos.down();
        World world = this.world.getWorld();
        BlockState state = world.getBlockState(pos1);
        Block block = state.getBlock();

        if (block.isIn(OccultTagLib.Blocks.LOWHEATSOURCE)) {
            fuelTemp = 250;
        } else if (block.isIn(OccultTagLib.Blocks.MEDIUMLOWHEATSOURCE)) {
            fuelTemp = 650;
        } else if (block.isIn(OccultTagLib.Blocks.MEDIUMHEATSOURCE)) {
            fuelTemp = 1000;
        } else if (block.isIn(OccultTagLib.Blocks.MEDIUMHIGHHEATSOURCE)) {
            fuelTemp = 1500;
        } else if (block.isIn(OccultTagLib.Blocks.HIGHHEATSOURCE)) {
            fuelTemp = 2000;
        } else if (block.isIn(OccultTagLib.Blocks.EXTREMELYHIGHHEATSOURCE)) {
            fuelTemp = 3000;
        } else {
            fuelTemp = 0;
        }
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

    public List<ItemStack> getAllItems(){
        List<ItemStack> itemList = new ArrayList<ItemStack>();
        for(int x = 0; x < 2; ++x) {
            ItemStack stack = getStackInSlot(x);
            if(stack != null) {
                itemList.add(x, stack);
            }
        }
        return itemList;
    }

    public void extractInsertItem(PlayerEntity player, Hand hand) {
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
                if (r >= 0 && r < invslots) {
                    inventory.insertItem(r, ItemStack.read(nbt), false);
                }
            });
        }
        this.fuelTemp = tag.getInt("fuelTemp");
        this.minTemp = tag.getInt("minTemp");
        this.maxTemp = tag.getInt("maxTemp");
        this.meltTime = tag.getInt("meltTime");
        this.maxMeltTime = tag.getInt("maxMeltTime");
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
        compound.putInt("meltTime", meltTime);
        compound.putInt("maxMeltTotal", maxMeltTime);
        compound.putInt("fuelTemp", fuelTemp);
        compound.putInt("minTemp", minTemp);
        compound.putInt("maxTemp", maxTemp);

        return compound;
    }

    protected int getMaxTemp() {
        return maxTemp;
    }

    protected int getMinTemp() {
        return minTemp;
    }

    public int getSlots(){
        handler.map(inventory -> {
            int slots = inventory.getSlots();
            return slots;
        }).orElse(0);
        return 0;
    }


    public ItemStack getStackInSlot(int slot) {
        handler.map(inventory -> {
            return inventory.getStackInSlot(slot);
        }).orElse(ItemStack.EMPTY);
        return ItemStack.EMPTY;
    }

    public ItemStack extractStackFromSlot(int slot) {
        handler.map(inventory -> {
            return inventory.extractItem(slot, 1, false);
        }).orElse(ItemStack.EMPTY);
        return ItemStack.EMPTY;
    }

    public void insertSlotContents(int slot, ItemStack itemStack) {
        handler.ifPresent(inventory -> {
            inventory.insertItem(slot, itemStack.copy(), false);
        });
    }


    public void clearInv() {
        handler.ifPresent(inventory -> {
            for(int x = 0; x < 2; ++x){
                inventory.extractItem(x,1,false);
            }
        });
    }
}
