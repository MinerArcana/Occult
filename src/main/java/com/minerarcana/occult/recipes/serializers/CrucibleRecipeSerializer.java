package com.minerarcana.occult.recipes.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.minerarcana.occult.recipes.machines.CrucibleRecipes;
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

public class CrucibleRecipeSerializer<T extends CrucibleRecipes> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    private final CrucibleRecipeSerializer.IFactory<T> factory;
    private final int meltTime;
    private final int minTemp;
    private final int maxTemp;
    private final int experience;

    public CrucibleRecipeSerializer(CrucibleRecipeSerializer.IFactory<T> factory, int meltTime, int maxTemp, int minTemp, int experience) {
        this.factory = factory;
        this.meltTime = meltTime;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.experience = experience;

    }

    @Override
    public T read(ResourceLocation id, JsonObject json) {
        JsonElement ingredientJson = JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient");
        Ingredient ingredient = Ingredient.deserialize(ingredientJson);
        if (!json.has("output"))
            throw new com.google.gson.JsonSyntaxException("Missing output, expected to find a object");
        JsonObject outputJson = JSONUtils.getJsonObject(json, "output");
        String ItemKey = JSONUtils.getString(outputJson, "Item");

        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(ItemKey));

        if (item == null)
            throw new com.google.gson.JsonSyntaxException("Crucible recipe output is null! Recipe is: " + id.toString());
        ItemStack output = new ItemStack(item, JSONUtils.getInt(outputJson, "amount"));

        int meltTime = JSONUtils.getInt(json, "MeltTime", this.meltTime);
        int minTemp = JSONUtils.getInt(json, "MinimumTemp", this.minTemp);
        int maxTemp = JSONUtils.getInt(json, "MaximumTemp", this.maxTemp);
        int experience = JSONUtils.getInt(json, "Exeperience", this.experience);

        return this.factory.create(id, ingredient, output, meltTime, minTemp, maxTemp, experience);

    }

    @Nullable
    @Override
    public T read(ResourceLocation id, PacketBuffer buffer) {
        Ingredient ingredient = Ingredient.read(buffer);

        Item item = ForgeRegistries.ITEMS.getValue(buffer.readResourceLocation());
        if (item == null)
            throw new com.google.gson.JsonSyntaxException("Crucible recipe result is null! Recipe is: " + id.toString());
        int amount = buffer.readVarInt();
        ItemStack output = new ItemStack(item);

        int meltTime = buffer.readVarInt();
        int minTemp = buffer.readVarInt();
        int maxTemp = buffer.readVarInt();
        int experience = buffer.readVarInt();
        return this.factory.create(id, ingredient, output, meltTime, minTemp, maxTemp, experience);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe) {
        recipe.getIngredients().get(0).write(buffer);
        buffer.writeResourceLocation(recipe.getRecipeOutput().getItem().getRegistryName());
        buffer.writeVarInt(recipe.getMeltTime());
        buffer.writeVarInt(recipe.getMaxtemp());
        buffer.writeVarInt(recipe.getMintemp());
        buffer.writeFloat(recipe.getExperience());

    }

    public interface IFactory<T extends CrucibleRecipes> {
        T create(ResourceLocation id, Ingredient ingredient, ItemStack output, int meltTime, int maxTemp, int minTemp, int experience);
    }
}

