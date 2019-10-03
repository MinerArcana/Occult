package com.minerarcana.occult.api.recipes.serializers;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.minerarcana.occult.api.recipes.machines.CrucibleRecipes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.Objects;


public class CrucibleRecipeSerializer<T extends CrucibleRecipes> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    private final IFactory<T> factory;
    private final int meltTime = 0;
    private final int minTemp = 0;
    private final int maxTemp = 0;
    private final int experience = 0;

    public CrucibleRecipeSerializer(IFactory<T> factory) {
        this.factory = factory;

    }

    @Override
    public T read(ResourceLocation id, JsonObject json) {
        JsonElement ingredientJson = JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient");
        Ingredient ingredient = Ingredient.deserialize(ingredientJson);


        if (!json.has("output"))
            throw new com.google.gson.JsonSyntaxException("Missing output, expected to find a object");

        JsonElement outputJson = JSONUtils.isJsonArray(json, "output") ? JSONUtils.getJsonArray(json, "output") : JSONUtils.getJsonObject(json, "output");


        String itemKey = JSONUtils.getString(outputJson, "item");
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemKey));

        if (item == null)
            throw new com.google.gson.JsonSyntaxException("Crucible recipe output is null! Recipe is: " + id.toString());


            int meltTime = JSONUtils.getInt(json, "meltTime", this.meltTime);
            int minTemp = JSONUtils.getInt(json, "minimumTemp", this.minTemp);
            int maxTemp = JSONUtils.getInt(json, "maximumTemp", this.maxTemp);
            int experience = JSONUtils.getInt(json, "experience", this.experience);

        return this.factory.create(id, outputs, meltTime, minTemp, maxTemp, experience, ingredient);

    }

    @Nullable
    @Override
    public T read(ResourceLocation id, PacketBuffer buffer) {
        Ingredient ingredient = Ingredient.read(buffer);

        Item item = ForgeRegistries.ITEMS.getValue(buffer.readResourceLocation());
        if (item == null)
            throw new com.google.gson.JsonSyntaxException("Crucible recipe result is null! Recipe is: " + id.toString());


        int size = buffer.readVarInt();
        ItemStack[] outputs = new ItemStack[size];
        for(int i = 0; i < size; i++)
        {outputs[i] = buffer.readItemStack();}

        int meltTime = buffer.readVarInt();
        int minTemp = buffer.readVarInt();
        int maxTemp = buffer.readVarInt();
        int experience = buffer.readVarInt();
        return this.factory.create(id, outputs, meltTime, minTemp, maxTemp, experience, ingredient);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe) {
        buffer.writeResourceLocation(recipe.getId());
        buffer.writeVarInt(recipe.getInputs().size());
        for (Ingredient input : recipe.getInputs()) {
            input.write(buffer);
        }
        buffer.writeVarInt(recipe.getOutputs().size());
        for (ItemStack outputs : recipe.getOutputs()) {
            buffer.writeItemStack(outputs);
        }
        recipe.getIngredients().get(0).write(buffer);

        buffer.writeResourceLocation(Objects.requireNonNull(recipe.getRecipeOutput().getItem().getRegistryName()));

        buffer.writeVarInt(recipe.getMeltTime());
        buffer.writeVarInt(recipe.getMaxtemp());
        buffer.writeVarInt(recipe.getMintemp());
        buffer.writeFloat(recipe.getExperience());

    }

    public interface IFactory<T extends CrucibleRecipes> {
        T create(ResourceLocation id, ItemStack[] output, int meltTime, int maxTemp, int minTemp, int experience, Ingredient... inputs);
    }
}
