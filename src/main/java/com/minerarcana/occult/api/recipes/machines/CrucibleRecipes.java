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

import java.util.ArrayList;
import java.util.List;

public class CrucibleRecipes implements IRecipe<IInventory> {
    private final ResourceLocation id;
    private final List<ItemStack> outputs;
    private final ItemStack alternateOut;
    private final ImmutableList<Ingredient> inputs;
    private final PressureType pressureType;
    private final int maxtemp;
    private final int pressureAmount;
    private final int mintemp;
    private final int meltTime;
    private final int experience;


    public CrucibleRecipes(ResourceLocation id, List<ItemStack> outputs, ItemStack alternateOut, int maxTemp, int minTemp, int meltTime, int experience, PressureType pressureType, int pressureAmount, Ingredient... inputs) {
        Preconditions.checkArgument(inputs.length <= 3);
        Preconditions.checkArgument(maxTemp <= 3000);
        Preconditions.  checkArgument(minTemp >= 150);
        this.id = id;
        this.inputs = ImmutableList.copyOf(inputs);
        this.outputs = outputs;
        this.alternateOut = alternateOut;
        this.mintemp = minTemp;
        this.maxtemp = maxTemp;
        this.meltTime = meltTime;
        this.pressureType = pressureType;
        this.pressureAmount = pressureAmount;
        this.experience = experience;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        List<Ingredient> ingredientsMissing = new ArrayList<>(inputs);

        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack input = inv.getStackInSlot(i);
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
    public ItemStack getCraftingResult(IInventory inv) {
        return this.outputs.get(0).copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.outputs.get(0).copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return OccultRecipeTypes.CRUCIBLESERIALIZER;
    }

    @Override
    public IRecipeType<?> getType() {
        return OccultRecipeTypes.CRUCIBLETYPE;
    }

    public float getExperience() {
        return this.experience;
    }

    public List<Ingredient> getInputs() {
        return this.inputs;
    }

    public List<ItemStack> getOutputs() {
        return this.outputs;
    }

    public ItemStack getAlternateOut(){
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

