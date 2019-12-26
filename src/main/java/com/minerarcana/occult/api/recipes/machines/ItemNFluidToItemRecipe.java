package com.minerarcana.occult.api.recipes.machines;

import com.google.common.collect.ImmutableList;
import com.minerarcana.occult.api.pressure.PressureType;
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

import static com.minerarcana.occult.api.recipes.OccultRecipeTypes.CRUCIBLE_FLUID_TO_ITEM;
import static com.minerarcana.occult.api.recipes.OccultRecipeTypes.CRUCIBLE_FLUID_TO_ITEM_SERIALIZER;

public class ItemNFluidToItemRecipe implements IRecipe<IInventory> {
    private final ResourceLocation id;
    private final FluidStack fluidStackIn;
    private final PressureType pressureType;
    private final ImmutableList<Ingredient> inputs;
    private final List<ItemStack> outputs;
    private final ItemStack alternateOut;
    private final int maxTempBeforeBrittle;
    private final int pressureAmount;
    private final int minHoldingTemp;
    private final int coolTime;
    private final int experience;

    public ItemNFluidToItemRecipe(ResourceLocation id, FluidStack fluidStackIn, ImmutableList<Ingredient> inputs, List<ItemStack> outputs, ItemStack alternateOut,int maxTempBeforeBrittle, int minHoldingTemp, int coolTime, int experience, PressureType pressureType, int pressureAmount) {
        this.id = id;
        this.fluidStackIn = fluidStackIn;
        this.outputs = outputs;
        this.alternateOut = alternateOut;
        this.inputs = ImmutableList.copyOf(inputs);
        this.minHoldingTemp = minHoldingTemp;
        this.maxTempBeforeBrittle = maxTempBeforeBrittle;
        this.coolTime = coolTime;
        this.pressureType = pressureType;
        this.pressureAmount = pressureAmount;
        this.experience = experience;
    }

    public boolean matches(List<ItemStack> inputList, FluidStack stack) {
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

        return ingredientsMissing.isEmpty() && getFluidStackIn().equals(stack);
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
        return false;
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
        return CRUCIBLE_FLUID_TO_ITEM_SERIALIZER;
    }

    @Override
    public IRecipeType<?> getType() {
        return CRUCIBLE_FLUID_TO_ITEM;
    }

    public List<Ingredient> getItemsIn() {
        return this.inputs;
    }

    public List<ItemStack> getOutputs() {
        return outputs;
    }

    public FluidStack getFluidStackIn() {
        return fluidStackIn;
    }

    public ItemStack getAlternateOut() {
        return alternateOut;
    }

    public PressureType getPressureType() {
        return pressureType;
    }

    public int getMaxTempBeforeBrittle() {
        return this.maxTempBeforeBrittle;
    }

    public int getMinHoldingTemp() {
        return this.minHoldingTemp;
    }

    public int getCoolTime() {
        return this.coolTime;
    }

    public int getPressureAmount() {
        return this.pressureAmount;
    }

    public int getExperience() {
        return experience;
    }
}
