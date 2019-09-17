package com.minerarcana.occult.recipes.machines;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.minerarcana.occult.recipes.OccultRecipeTypes;
import com.minerarcana.occult.recipes.serializers.CrucibleRecipeSerializer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CrucibleRecipes implements IRecipe<IInventory> {
    private final ResourceLocation id;
    private final ResourceLocation animation;
    private final ItemStack output;
    private final ItemStack secondoutput;
    private final ItemStack thirdoutput;
    private final ImmutableList<Ingredient> inputs;
    private final String group;
    private final int maxtemp;
    private final int mintemp;
    private final int meltTime;
    private final int experience;

    public CrucibleRecipes(ResourceLocation id, ResourceLocation animation, ItemStack output, ItemStack secondoutput, ItemStack thirdoutput, int maxTemp, int minTemp, int meltTime, int experience, String group, Ingredient... inputs)
    {
        Preconditions.checkArgument(inputs.length <= 3);
        Preconditions.checkArgument(maxTemp <= 5000);
        Preconditions.checkArgument(minTemp >= 100);
        this.id = id;
        this.animation = animation;
        this.inputs = ImmutableList.copyOf(inputs);
        this.output = output;
        this.secondoutput = secondoutput;
        this.thirdoutput = thirdoutput;
        this.mintemp = minTemp;
        this.maxtemp = maxTemp;
        this.meltTime = meltTime;
        this.group = group;
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
            if(stackIndex != -1)
                ingredientsMissing.remove(stackIndex);
            else return false;
        }

        return ingredientsMissing.isEmpty();
    }

    public ItemStack getSecondOutput() {
        return secondoutput;
    }

    public ItemStack getThirdOutput() {
        return thirdoutput;
    }


    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return this.output.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.output;
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

    public ResourceLocation getAnimation() {
        return this.animation;
    }

    public String getGroup() {
        return this.group;
    }

    public List<Ingredient> getInputs() {
        return this.inputs;
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
        buf.writeResourceLocation(animation);
        buf.writeVarInt(inputs.size());
        for (Ingredient input : inputs) {
            input.write(buf);
        }
        buf.writeItemStack(output);
        buf.writeItemStack(secondoutput);
        buf.writeItemStack(thirdoutput);
        buf.writeVarInt(maxtemp);
        buf.writeVarInt(mintemp);
        buf.writeVarInt(meltTime);
        buf.writeVarInt(experience);
        buf.writeString(group);
    }

    public static CrucibleRecipes read(PacketBuffer buf) {
        ResourceLocation id = buf.readResourceLocation();
        ResourceLocation animation = buf.readResourceLocation();
        Ingredient[] inputs = new Ingredient[buf.readVarInt()];
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = Ingredient.read(buf);
        }
        ItemStack output = buf.readItemStack();
        ItemStack secondoutput = buf.readItemStack();
        ItemStack thirdoutput = buf.readItemStack();

        int maxtemp = buf.readVarInt();
        int mintemp = buf.readVarInt();
        int melttime = buf.readVarInt();
        int experience = buf.readVarInt();
        String group = buf.readString();

        return new CrucibleRecipes(id, animation, output, secondoutput, thirdoutput, maxtemp, mintemp, melttime, experience, group, inputs);
    }


}

