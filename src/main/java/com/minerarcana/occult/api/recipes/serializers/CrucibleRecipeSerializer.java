package com.minerarcana.occult.api.recipes.serializers;


import com.google.common.collect.Streams;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.minerarcana.occult.api.recipes.machines.CrucibleRecipes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class CrucibleRecipeSerializer<T extends CrucibleRecipes> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    private final IFactory<T> factory;


    public CrucibleRecipeSerializer(IFactory<T> factory) {
        this.factory = factory;

    }

    @Override
    public T read(ResourceLocation id, JsonObject json) {

        //input
        JsonElement ingredientJson = JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient");
        Ingredient ingredient = Ingredient.deserialize(ingredientJson);

        //output
        List<ItemStack> outputs = JSONUtils.isJsonArray(json, "outputs") ? Streams.stream(JSONUtils.getJsonArray(json, "outputs")).flatMap(element -> stacksFromJson(element).stream()).collect(Collectors.toList()) : stacksFromJson(json.get("outputs"));

        //int
        int meltTime = JSONUtils.getInt(json, "meltTime", 150);
        int minTemp = JSONUtils.getInt(json, "minimumTemp", 151);
        int maxTemp = JSONUtils.getInt(json, "maximumTemp", 500);
        int experience = JSONUtils.getInt(json, "experience", 0);

        return this.factory.create(id, outputs, meltTime, minTemp, maxTemp, experience, ingredient);



    }

    public static List<ItemStack> stacksFromJson(JsonElement element) {
        if (JSONUtils.isString(element)) {
            String name = JSONUtils.getString(element, "");
            ResourceLocation id = new ResourceLocation(name);
            if (!ForgeRegistries.ITEMS.containsKey(id)) {
                throw new IllegalStateException("Item: " + name + " does not exist");
            }
            ItemStack stack = new ItemStack(ForgeRegistries.ITEMS.getValue(id));
            return Collections.singletonList(stack);
        } else {
            JsonObject json = element.getAsJsonObject();
            String name = JSONUtils.getString(json, "item");
            int count = JSONUtils.getInt(json, "count", 1);
            ResourceLocation id = new ResourceLocation(name);
            if (!ForgeRegistries.ITEMS.containsKey(id)) {
                throw new IllegalStateException("Item: " + name + " does not exist");
            }
            ItemStack stack = new ItemStack(ForgeRegistries.ITEMS.getValue(id), count);
            return Collections.singletonList(stack);
        }
    }

    @Nullable
    @Override
    public T read(ResourceLocation id, PacketBuffer buffer) {
        Ingredient ingredient = Ingredient.read(buffer);

        Item item = ForgeRegistries.ITEMS.getValue(buffer.readResourceLocation());
        if (item == null)
            throw new com.google.gson.JsonSyntaxException("Crucible recipe result is null! Recipe is: " + id.toString());


        List<ItemStack> outputs = null;
        int resultCount = buffer.readVarInt();
        if (resultCount > 1) {
            outputs = new ArrayList<>(resultCount);
            for (int i = 0; i < resultCount; i++) {
                outputs.add(buffer.readItemStack());
            }
        } else {
            outputs = Collections.singletonList(buffer.readItemStack());
        }

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
        T create(ResourceLocation id, List<ItemStack> output, int meltTime, int maxTemp, int minTemp, int experience, Ingredient... inputs);
    }


}

