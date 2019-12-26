package com.minerarcana.occult.api.recipes.machines;

import com.minerarcana.occult.api.pressure.PressureType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

import static com.minerarcana.occult.api.recipes.OccultRecipeTypes.CRUCIBLE_FLUID_TO_ITEM;
import static com.minerarcana.occult.api.recipes.OccultRecipeTypes.CRUCIBLE_FLUID_TO_ITEM_SERIALIZER;

public class ItemNFluidToItemRecipe implements IRecipe<IInventory> {
    private final ResourceLocation id;
    private final FluidStack fluidStackIn;
    private final PressureType pressureType;
    private final List<ItemStack> inputs;
    private final List<ItemStack> outputs;
    private final int maxTempBeforeBrittle;
    private final int pressureAmount;
    private final int minHoldingTemp;
    private final int coolTime;
    private final int experience;

    public ItemNFluidToItemRecipe(ResourceLocation id, FluidStack fluidStackIn,List<ItemStack> inputs, List<ItemStack> outputs, int maxTempBeforeBrittle, int minHoldingTemp, int coolTime, int experience, PressureType pressureType, int pressureAmount) {
        this.id = id;
        this.fluidStackIn = fluidStackIn;
        this.outputs = outputs;
        this.inputs = inputs;
        this.minHoldingTemp = minHoldingTemp;
        this.maxTempBeforeBrittle = maxTempBeforeBrittle;
        this.coolTime = coolTime;
        this.pressureType = pressureType;
        this.pressureAmount = pressureAmount;
        this.experience = experience;
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
