package com.minerarcana.occult.api.recipes.machines;

import com.minerarcana.occult.api.pressure.PressureType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

import static com.minerarcana.occult.api.recipes.OccultRecipeTypes.CRUCIBLE_FLUID_TO_ITEM;
import static com.minerarcana.occult.api.recipes.OccultRecipeTypes.CRUCIBLE_FLUID_TO_ITEM_SERIALIZER;

public class FluidToItemRecipe implements IRecipe<IInventory> {
    private final ResourceLocation id;
    private final FluidStack fluidStackIn;
    private final PressureType pressureType;
    private final List<ItemStack> outputs;
    private final ItemStack alternateOut;
    private final int maxTempBeforeBrittle;
    private final int pressureAmount;
    private final int minHoldingTemp;
    private final int coolTime;
    private final int experience;

    public FluidToItemRecipe(ResourceLocation id, FluidStack fluidStackIn, List<ItemStack> outputs, ItemStack alternateOut, int maxTempBeforeBrittle, int minHoldingTemp, int coolTime, int experience, PressureType pressureType, int pressureAmount) {
        this.id = id;
        this.fluidStackIn = fluidStackIn;
        this.outputs = outputs;
        this.alternateOut = alternateOut;
        this.minHoldingTemp = minHoldingTemp;
        this.maxTempBeforeBrittle = maxTempBeforeBrittle;
        this.coolTime = coolTime;
        this.pressureType = pressureType;
        this.pressureAmount = pressureAmount;
        this.experience = experience;
    }

    public boolean matches(FluidStack stack){
        return getFluidStackIn().equals(stack);
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

    public List<ItemStack> getOutputs() {
        return outputs;
    }

    public ItemStack getAlternateOutput() {
        return Items.COAL.getDefaultInstance();
    }

    public FluidStack getFluidStackIn() {
        return fluidStackIn;
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

    public ItemStack getAlternateOut() {
        return alternateOut;
    }

    public int getExperience() {
        return experience;
    }
}
