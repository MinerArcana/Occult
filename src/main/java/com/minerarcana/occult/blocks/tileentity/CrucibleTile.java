package com.minerarcana.occult.blocks.tileentity;

import com.minerarcana.occult.Occult;
import com.minerarcana.occult.api.pressure.PressureType;
import com.minerarcana.occult.api.pressure.pressure.PressureCap;
import com.minerarcana.occult.api.recipes.OccultRecipeTypes;
import com.minerarcana.occult.api.recipes.machines.CrucibleRecipes;
import com.minerarcana.occult.util.lib.OccultTagLib;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
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

public class CrucibleTile extends TileEntity implements ITickableTileEntity, IInventory {

    //Inventory Cap Optional!
    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);

    private final IRecipeType<CrucibleRecipes> recipeType;

    private int maxTemp;
    private int minTemp;
    private int meltTime;
    private int maxMeltTime;
    private int fuelTemp = 50;

    public CrucibleTile() {
        super(CRUCIBLE_TYPE.get());
        recipeType = OccultRecipeTypes.CRUCIBLETYPE;
    }

    @Override
    public void tick() {
        setFuelTemp();
        if (hasFuel()) {
            List<ItemStack> itemList = getAllItems();
            Occult.LOGGER.info(itemList.get(0).getItem());
            if (!itemList.isEmpty()) {
                Occult.LOGGER.info("itemList is not Empty");
                IRecipe<?> irecipe = this.world.getRecipeManager().getRecipe(this.recipeType, this, this.world).orElse(null);
                if (canSmelt(irecipe, itemList) && hotEnough()) {
                    if(getMeltTime() == 0){
                        getRecipeThings(irecipe);
                    }
                    else if (getMeltTime() == getMaxMeltTime()) {
                        this.meltTime = 0;
                        this.smeltRecipe(irecipe);
                    }else {
                        ++meltTime;
                    }
                }
            }
        } else if (!this.hasFuel() && this.meltTime > 0) {
            this.meltTime = MathHelper.clamp(getMeltTime() - 2, 0, getMaxMeltTime());
        }
    }

    private void smeltRecipe(@Nullable IRecipe<?> iRecipe) {
        if (!(iRecipe instanceof CrucibleRecipes))
            return;
        CrucibleRecipes recipe = (CrucibleRecipes) iRecipe;
        ItemStack alternateOut = recipe.getAlternateOut();
        PressureType type = recipe.getPressureType();
        int pressureIn = recipe.getPressureAmount();
        if(tooHot()){
            clear();
            if(alternateOut == null){
                alternateOut = Blocks.COAL_ORE.asItem().getDefaultInstance();
            }
            setInventorySlotContents(0, alternateOut);
            if(pressureIn !=0) {
                PressureCap.addChunkPressure(world.getChunkAt(pos), type, pressureIn);
            }
        }
        else if(hotEnough()){
            clear();
            int size = recipe.getOutputs().size();
            for(int x = 0; x < size; ++x){
                setInventorySlotContents(x,recipe.getOutputs().get(x));
            }
            if(pressureIn !=0) {
                PressureCap.addChunkPressure(world.getChunkAt(pos), type, pressureIn);
            }
        }
    }


    private void getRecipeThings(@Nullable IRecipe<?> iRecipe){
        if (iRecipe instanceof CrucibleRecipes){
            CrucibleRecipes recipe = (CrucibleRecipes) iRecipe;
            maxTemp = recipe.getMaxtemp();
            minTemp = recipe.getMintemp();
            maxMeltTime = recipe.getMeltTime();
        }
    }

    private boolean canSmelt(@Nullable IRecipe<?> iRecipe, List<ItemStack> list) {
        if (!(iRecipe instanceof CrucibleRecipes))
            return false;
        if (!list.isEmpty()) {
            CrucibleRecipes recipe = (CrucibleRecipes) iRecipe;
            if(recipe.matches(list)) {
                ItemStack outStack = recipe.getRecipeOutput();
                if (outStack.isEmpty())
                    return false;
                else {
                    return list.isEmpty();
                }
            }
        }
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
        handler.ifPresent(inventory -> {
            for(int x = 0; x < 2; ++x) {
                itemList.set(x,inventory.getStackInSlot(1));
            }
        });
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

    @Override
    public int getSizeInventory() {
        handler.map(inventory -> {
            int slots = inventory.getSlots();
            return slots;
        }).orElse(0);
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        handler.map(inventory -> {
            return inventory.getStackInSlot(i);
        }).orElse(ItemStack.EMPTY);
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack decrStackSize(int i, int i1) {
        return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int i) {
        handler.map(inventory -> {
            ItemStack stack = inventory.extractItem(i, 1, false);
            return stack;
        }).orElse(ItemStack.EMPTY);
        return ItemStack.EMPTY;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        handler.ifPresent(inventory -> {
            inventory.insertItem(i, itemStack.copy(), false);
        });
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity playerEntity) {
        return true;
    }

    @Override
    public void clear() {
        handler.ifPresent(inventory -> {
            for(int x = 0; x < 2; ++x){
                inventory.extractItem(x,1,false);
            }
        });
    }
}
