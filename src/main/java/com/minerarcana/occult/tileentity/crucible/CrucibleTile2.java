package com.minerarcana.occult.tileentity.crucible;

import com.google.common.collect.Maps;
import com.minerarcana.occult.recipes.machines.CrucibleRecipes;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Map;

import static com.minerarcana.occult.recipes.OccultRecipeTypes.CRUCIBLETYPE;
import static com.minerarcana.occult.tileentity.OccultTileEntities.CRUCIBLETILE;

public class CrucibleTile2 extends TileEntity
{
    protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
    private boolean hasFuel;
    private int minTemp;
    private int maxTemp;
    private int FuelTemp;
    private int meltTime;
    private int meltTimeTotal;
    protected final IIntArray crucibleData = new IIntArray() {
        public int get(int index) {
            switch(index) {
                case 0:
                    return CrucibleTile2.this.meltTime;
                case 1:
                    return CrucibleTile2.this.meltTimeTotal;
                case 2:
                    return CrucibleTile2.this.minTemp;
                case 3:
                    return CrucibleTile2.this.maxTemp;
                default:
                    return 0;
            }
        }

        public void set(int index, int value) {
            switch(index) {
                case 0:
                    CrucibleTile2.this.meltTime = value;
                    break;
                case 1:
                    CrucibleTile2.this.meltTimeTotal = value;
                    break;
                case 2:
                    CrucibleTile2.this.minTemp = value;
                    break;
                case 3:
                    CrucibleTile2.this.maxTemp = value;
                    break;

            }

        }

        public int size() {
            return 4;
        }
    };

    public int getFuelTemp() {
        return FuelTemp;
    }

    public int setFuelTemp(int temp) {
        return FuelTemp = temp;
    }

    public boolean hasFuel() {
        if(getFuelTemp() > 150) {
            return true;
        }
        return hasFuel;
    }

    private final Map<ResourceLocation, Integer> id = Maps.newHashMap();

    protected final IRecipeType<? extends CrucibleRecipes> recipeType;


    public CrucibleTile2() {
        super(CRUCIBLETILE);
        recipeType = CRUCIBLETYPE;
    }

    public void read(CompoundNBT nbt) {
        super.read(nbt);
        this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, this.items);
        this.meltTime = nbt.getInt("MeltTime");
        this.meltTimeTotal = nbt.getInt("MeltTimeTotal");
        this.maxTemp = nbt.getInt("MaxTemp");
        this.minTemp = nbt.getInt("MinTemp");
        int i = nbt.getShort("RecipesUsedSize");

        for(int j = 0; j < i; ++j) {
            ResourceLocation resourcelocation = new ResourceLocation(nbt.getString("RecipeLocation" + j));
            int k = nbt.getInt("RecipeAmount" + j);
            this.id.put(resourcelocation, k);
        }

    }

    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt);
        nbt.putInt("MeltTime", this.meltTime);
        nbt.putInt("MeltTimeTotal", this.meltTimeTotal);
        nbt.putInt("MaxTemp", this.maxTemp);
        nbt.putInt("MinTemp", this.minTemp);
        ItemStackHelper.saveAllItems(nbt, this.items);
        nbt.putShort("RecipesUsedSize", (short)this.id.size());
        int i = 0;

        for(Map.Entry<ResourceLocation, Integer> entry : this.id.entrySet()) {
            nbt.putString("RecipeLocation" + i, entry.getKey().toString());
            nbt.putInt("RecipeAmount" + i, entry.getValue());
            ++i;
        }

        return nbt;
    }



    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);


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
            protected int getStackLimit(int slot, @Nonnull ItemStack stack)
            {
                return Math.min(0, 1);
            }
            @Override
            public int getSlotLimit(int slot)
            {
                return 3;
            }

            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

        };
    }


    public void tick() {
        boolean flag = this.hasFuel();
        boolean flag1 = false;
        if (this.hasFuel()) {
            if (!this.world.isRemote) {
                ItemStack itemstack = this.items.get(1);
                if (!itemstack.isEmpty() && !this.items.get(0).isEmpty()) {
                    IRecipe<?> irecipe = this.world.getRecipeManager().getRecipe(CRUCIBLETYPE, this, this.world).orElse(null);
                    if (!this.hasFuel() && this.canSmelt(irecipe)) {
                        if (this.hasFuel()) {
                            flag1 = true;
                            if (itemstack.hasContainerItem())
                                this.items.set(1, itemstack.getContainerItem());
                            else if (!itemstack.isEmpty()) {
                                Item item = itemstack.getItem();
                                itemstack.shrink(1);
                                if (itemstack.isEmpty()) {
                                    this.items.set(1, itemstack.getContainerItem());
                                }
                            }
                        }
                    }

                    if (this.hasFuel() && this.canSmelt(irecipe)) {
                        ++this.meltTime;
                        if (this.meltTime == this.meltTimeTotal) {
                            this.meltTime = 0;
                            this.meltTimeTotal = this.setMeltTime();
                            this.setMeltTime(irecipe);
                            flag1 = true;
                        }
                    } else {
                        this.meltTime = 0;
                    }
                } else if (!this.hasFuel() && this.meltTime > 0) {
                    this.meltTime = MathHelper.clamp(this.meltTime - 2, 0, this.meltTimeTotal);
                }

                if (flag != this.hasFuel()) {
                    flag1 = true;
                    this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(AbstractFurnaceBlock.LIT, this.hasFuel()), 3);
                }
            }

            if (flag1) {
                this.markDirty();
            }

        }
    }
        public int getSizeInventory () {
            return this.items.size();
        }


        private static void addItemTemp (Map < Item, Integer > map, IItemProvider itemProvider,int temp){
            map.put(itemProvider.asItem(), temp);
        }


    protected boolean canSmelt(@Nullable IRecipe<?> recipeIn) {
        if (!this.items.get(0).isEmpty() && recipeIn != null) {
            ItemStack itemstack = recipeIn.getRecipeOutput();
            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack itemstack1 = this.items.get(2);
                if (itemstack1.isEmpty()) {
                    return true;
                } else if (!itemstack1.isItemEqual(itemstack)) {
                    return false;
                } else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) {
                    return true;
                } else {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize();
                }
            }
        } else {
            return false;
        }
    }

    private int getInventoryStackLimit() {
        return 1;
    }

    protected int setMeltTime() {
        return this.world.getRecipeManager().getRecipe(this.recipeType, this, this.world).map(CrucibleRecipes::getMeltTime).orElse(200);
    }

    private void smeltRecipe(@Nullable IRecipe<?> iRecipe) {
        if (!(iRecipe instanceof CrucibleRecipes))
            return;

        if (this.canSmelt(iRecipe)) {
            CrucibleRecipes recipe = (CrucibleRecipes) iRecipe;
            ItemStack ingredientStack = this.inventory.get(0);
            ItemStack recipeOutStack = recipe.getResult();
            ItemStack outStack = this.tank.getFluid();
            if (outStack.isEmpty())
                this.tank.setFluid(recipeOutStack.copy());
            else if (outStack.getFluid() == recipeOutStack.getFluid())
                this.tank.fill(recipeOutStack, IFluidHandler.FluidAction.EXECUTE);

            if (!this.world.isRemote)
                this.setRecipeUsed(recipe);

            ingredientStack.shrink(1);
        }
    }

}
