package com.minerarcana.occult.tileentity.crucible;

import com.google.common.collect.Maps;
import com.minerarcana.occult.recipes.CrucibleRecipes;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Map;

import static com.minerarcana.occult.tileentity.OccultTileEntities.CRUCIBLETILE;

public class CrucibleTile extends TileEntity
{
    protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
    private int recipesUsed;
    private int minTemp;
    private int maxTemp;
    private int meltTime;
    private int meltTimeTotal;
    protected final IIntArray crucibleData = new IIntArray() {
        public int get(int index) {
            switch(index) {
                case 0:
                    return CrucibleTile.this.recipesUsed;
                case 1:
                    return CrucibleTile.this.meltTime;
                case 2:
                    return CrucibleTile.this.meltTimeTotal;
                case 3:
                    return CrucibleTile.this.minTemp;
                case 4:
                    return CrucibleTile.this.maxTemp;
                default:
                    return 0;
            }
        }

        public void set(int index, int value) {
            switch(index) {
                case 0:
                    CrucibleTile.this.recipesUsed = value;
                    break;
                case 1:
                    CrucibleTile.this.meltTime = value;
                    break;
                case 2:
                    CrucibleTile.this.meltTimeTotal = value;
                    break;
                case 3:
                    CrucibleTile.this.minTemp = value;
                    break;
                case 4:
                    CrucibleTile.this.maxTemp = value;
                    break;

            }

        }

        public int size() {
            return 5;
        }
    };

    private final Map<ResourceLocation, Integer> field_214022_n = Maps.newHashMap();

    protected final IRecipeType<? extends CrucibleRecipes> recipeType;

    protected CrucibleTile(IRecipeType<? extends CrucibleRecipes> recipeType) {
        super(CRUCIBLETILE);
        this.recipeType = recipeType;
    }

    public void read(CompoundNBT compound) {
        super.read(compound);
        this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.items);
        this.meltTime = compound.getInt("MeltTime");
        this.meltTimeTotal = compound.getInt("MeltTimeTotal");
        this.maxTemp = compound.getInt("MaxTemp");
        this.minTemp = compound.getInt("MinTemp");
        this.recipesUsed = this.getMeltTime(this.items.get(1));
        int i = compound.getShort("RecipesUsedSize");

        for(int j = 0; j < i; ++j) {
            ResourceLocation resourcelocation = new ResourceLocation(compound.getString("RecipeLocation" + j));
            int k = compound.getInt("RecipeAmount" + j);
            this.field_214022_n.put(resourcelocation, k);
        }

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



}
