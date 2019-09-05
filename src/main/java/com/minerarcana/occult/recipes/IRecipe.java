package com.minerarcana.occult.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import java.util.List;

public interface IRecipe {
    List<Ingredient> getInputs();
    ItemStack getOutput();
}