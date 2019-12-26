package com.minerarcana.occult.api.recipes;

import com.hrznstudio.titanium.recipe.serializer.GenericSerializer;
import com.hrznstudio.titanium.recipe.serializer.SerializableRecipe;
import com.minerarcana.occult.api.pressure.PressureType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

import static com.minerarcana.occult.Occult.MOD_ID;

public class FluidToItemRecipe extends SerializableRecipe {

    public static GenericSerializer<FluidToItemRecipe> SERIALIZER = new GenericSerializer<>(new ResourceLocation(MOD_ID, "fluidtoitem"), FluidToItemRecipe.class);
    public static List<FluidToItemRecipe> RECIPES = new ArrayList<>();

    private FluidStack fluidStackIn;
    private PressureType pressureType;
    private List<ItemStack> outputs;
    private ItemStack alternateOut;
    private int maxTempBeforeBrittle;
    private int pressureAmount;
    private int minHoldingTemp;
    private int coolTime;
    private int experience;

    public FluidToItemRecipe(ResourceLocation resourceLocation) {
        super(resourceLocation);
    }

    public FluidToItemRecipe(ResourceLocation id, FluidStack fluidStackIn, List<ItemStack> outputs, ItemStack alternateOut, int maxTempBeforeBrittle, int minHoldingTemp, int coolTime, int experience, PressureType pressureType, int pressureAmount) {
        super(id);
        this.fluidStackIn = fluidStackIn;
        this.outputs = outputs;
        this.alternateOut = alternateOut;
        this.minHoldingTemp = minHoldingTemp;
        this.maxTempBeforeBrittle = maxTempBeforeBrittle;
        this.coolTime = coolTime;
        this.pressureType = pressureType;
        this.pressureAmount = pressureAmount;
        this.experience = experience;
        RECIPES.add(this);
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

    public List<ItemStack> getOutputs() {
        return outputs;
    }

    public ItemStack getAlternateOutput() {
        return Items.COAL.getDefaultInstance();
    }

    public FluidStack getFluidStackIn() {
        return fluidStackIn;
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
