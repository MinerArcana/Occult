package com.minerarcana.occult.api.recipes;

import com.google.common.collect.ImmutableList;
import com.hrznstudio.titanium.recipe.serializer.GenericSerializer;
import com.hrznstudio.titanium.recipe.serializer.SerializableRecipe;
import com.minerarcana.occult.api.pressure.PressureType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

import static com.minerarcana.occult.Occult.MOD_ID;

public class ItemToFluidRecipe extends SerializableRecipe {

    public static GenericSerializer<ItemToFluidRecipe> SERIALIZER = new GenericSerializer<>(new ResourceLocation(MOD_ID, "itemtofluid"), ItemToFluidRecipe.class);
    public static List<ItemToFluidRecipe> RECIPES = new ArrayList<>();

    private FluidStack fluidStackOut;
    private FluidStack alternateOut;
    private ImmutableList<Ingredient> inputs;
    private PressureType pressureType;
    private int maxtemp;
    private int pressureAmount;
    private int mintemp;
    private int meltTime;
    private int experience;

    public ItemToFluidRecipe(ResourceLocation resourceLocation) {
        super(resourceLocation);
    }

    public ItemToFluidRecipe(ResourceLocation id, FluidStack fluidStackOut, FluidStack alternateOut, int maxTemp, int minTemp, int meltTime, int experience, PressureType pressureType, int pressureAmount, Ingredient... inputs) {
        super(id);
        this.inputs = ImmutableList.copyOf(inputs);
        this.fluidStackOut = fluidStackOut;
        this.alternateOut = alternateOut;
        this.mintemp = minTemp;
        this.maxtemp = maxTemp;
        this.meltTime = meltTime;
        this.pressureType = pressureType;
        this.pressureAmount = pressureAmount;
        this.experience = experience;
        RECIPES.add(this);
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
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    @Override
    public GenericSerializer<? extends SerializableRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public IRecipeType<?> getType() {
        return SERIALIZER.getRecipeType();
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
