package com.minerarcana.occult.api.recipes.machines;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.minerarcana.occult.api.recipes.OccultRecipeTypes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CrucibleRecipes implements IRecipe<IInventory> {
    private final ResourceLocation id;
    private final List<ItemStack> outputs;
    private final ImmutableList<Ingredient> inputs;
    private final int maxtemp;
    private final int mintemp;
    private final int meltTime;
    private final int experience;


    public CrucibleRecipes(ResourceLocation id, List<ItemStack> outputs, int maxTemp, int minTemp, int meltTime, int experience, Ingredient... inputs) {
        Preconditions.checkArgument(inputs.length <= 3);
        Preconditions.checkArgument(maxTemp <= 3000);
        Preconditions.checkArgument(minTemp >= 150);
        this.id = id;
        this.inputs = ImmutableList.copyOf(inputs);
        this.outputs = outputs;
        this.mintemp = minTemp;
        this.maxtemp = maxTemp;
        this.meltTime = meltTime;
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


    public ItemStack getAlternateOutput() {
        return Items.COAL.getDefaultInstance();
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

    public int getMaxtemp() {
        return this.maxtemp;
    }

    public int getMintemp() {
        return this.mintemp;
    }

    public int getMeltTime() {
        return this.meltTime;
    }


    public void write(PacketBuffer buf) {
        buf.writeString("CrucibleRecipes");
        buf.writeResourceLocation(id);
        buf.writeVarInt(inputs.size());
        for (Ingredient input : inputs) {
            input.write(buf);
        }
        buf.writeVarInt(outputs.size());
        for (ItemStack output : outputs) {
            buf.writeItemStack(output, false);
        }
        buf.writeVarInt(maxtemp);
        buf.writeVarInt(mintemp);
        buf.writeVarInt(meltTime);
        buf.writeVarInt(experience);
    }

    public static CrucibleRecipes read(PacketBuffer buf) {
        ResourceLocation id = buf.readResourceLocation();
        Ingredient[] inputs = new Ingredient[buf.readVarInt()];
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = Ingredient.read(buf);
        }
        List<ItemStack> outputs = null;
        int resultCount = buf.readVarInt();
        if (resultCount > 1) {
            outputs = new ArrayList<>(resultCount);
            for (int i = 0; i < resultCount; i++) {
                outputs.add(buf.readItemStack());
            }
        } else {
            outputs = Collections.singletonList(buf.readItemStack());
        }
        int maxtemp = buf.readVarInt();
        int mintemp = buf.readVarInt();
        int melttime = buf.readVarInt();
        int experience = buf.readVarInt();

        return new CrucibleRecipes(id, outputs, maxtemp, mintemp, melttime, experience, inputs);
    }


}

