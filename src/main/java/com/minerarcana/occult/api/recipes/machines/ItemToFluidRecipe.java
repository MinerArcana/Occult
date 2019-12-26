package com.minerarcana.occult.api.recipes.machines;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.minerarcana.occult.api.pressure.PressureType;
import com.minerarcana.occult.api.recipes.OccultRecipeTypes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class ItemToFluidRecipe implements IRecipe<IInventory> {
    private final ResourceLocation id;
    private final FluidStack fluidStackOut;
    private final FluidStack alternateOut;
    private final ImmutableList<Ingredient> inputs;
    private final PressureType pressureType;
    private final int maxtemp;
    private final int pressureAmount;
    private final int mintemp;
    private final int meltTime;
    private final int experience;


    public ItemToFluidRecipe(ResourceLocation id, FluidStack fluidStackOut, FluidStack alternateOut, int maxTemp, int minTemp, int meltTime, int experience, PressureType pressureType, int pressureAmount, Ingredient... inputs) {
        this.id = id;
        this.inputs = ImmutableList.copyOf(inputs);
        this.fluidStackOut = fluidStackOut;
        this.alternateOut = alternateOut;
        this.mintemp = minTemp;
        this.maxtemp = maxTemp;
        this.meltTime = meltTime;
        this.pressureType = pressureType;
        this.pressureAmount = pressureAmount;
        this.experience = experience;
    }

    public boolean matches(List<ItemStack> inputList) {
        List<Ingredient> ingredientsMissing = new ArrayList<>(getItemsIn());
        for (ItemStack input : inputList) {
            if (input.isEmpty())
                break;

            int stackIndex = -1;

            for (int j = 0; j < ingredientsMissing.size(); j++) {
                Ingredient ingr = ingredientsMissing.get(j);
                if (ingr.test(input)) {
                    stackIndex = j;
                    break;
                }
            }
            if (stackIndex != -1)
                ingredientsMissing.remove(stackIndex);
            else return false;
        }

        return ingredientsMissing.isEmpty();
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }


    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return null;
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return OccultRecipeTypes.CRUCIBLE_ITEM_TO_FLUID_SERIALIZER;
    }

    @Override
    public IRecipeType<?> getType() {
        return OccultRecipeTypes.CRUCIBLE_ITEM_TO_FLUID;
    }

    public float getExperience() {
        return this.experience;
    }

    public List<Ingredient> getItemsIn() {
        return this.inputs;
    }

    public FluidStack getFluidOut() {
        return this.fluidStackOut;
    }

    public FluidStack getAlternateOut() {
        return alternateOut;
    }

    public PressureType getPressureType() {
        return pressureType;
    }

    public int getMaxtemp() {
        return this.maxtemp;
    }

    public int getMintemp() {
        return this.mintemp;
    }

    public int getMeltTime() {
        return this.meltTime;
    }

    public int getPressureAmount() {
        return this.pressureAmount;
    }

}

