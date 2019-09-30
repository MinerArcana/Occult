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

import static net.minecraft.item.crafting.ShapedRecipe.deserializeItem;


public class CrucibleRecipeSerializer<T extends CrucibleRecipes> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    private final IFactory<T> factory;
    private final String group;
    private final int meltTime;
    private final int minTemp;
    private final int maxTemp;
    private final int experience;

    public CrucibleRecipeSerializer(IFactory<T> factory, int meltTime, int maxTemp, int minTemp, int experience, String group) {
        this.factory = factory;
        this.meltTime = meltTime;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.experience = experience;
        this.group = group;

    }


    @Override
    public T read(ResourceLocation id, JsonObject json) {
        JsonElement ingredientJson = JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient");
        Ingredient ingredient = Ingredient.deserialize(ingredientJson);
        if (!json.has("output"))
            throw new com.google.gson.JsonSyntaxException("Missing output, expected to find a object");
        JsonObject outputJson = JSONUtils.getJsonObject(json, "output");
        String ItemKey = JSONUtils.getString(outputJson, "Item");
        String AnimationKey = JSONUtils.getString(outputJson, "animation");
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(ItemKey));
        ResourceLocation animation = ResourceLocation.tryCreate(AnimationKey);
        if (item == null)
            throw new com.google.gson.JsonSyntaxException("Crucible recipe output is null! Recipe is: " + id.toString());
        ItemStack output = deserializeItem(JSONUtils.getJsonObject(json, "result"));
        String group = JSONUtils.getString(json, "Group", this.group);

        int meltTime = JSONUtils.getInt(json, "MeltTime", this.meltTime);
        int minTemp = JSONUtils.getInt(json, "MinimumTemp", this.minTemp);
        int maxTemp = JSONUtils.getInt(json, "MaximumTemp", this.maxTemp);
        int experience = JSONUtils.getInt(json, "Experience", this.experience);

        return this.factory.create(id, animation ,output, meltTime, minTemp, maxTemp, experience, group, ingredient);

    }

    @Nullable
    @Override
    public T read(ResourceLocation id, PacketBuffer buffer) {
        Ingredient ingredient = Ingredient.read(buffer);

        Item item = ForgeRegistries.ITEMS.getValue(buffer.readResourceLocation());
        ResourceLocation animation = buffer.readResourceLocation();
        if (item == null)
            throw new com.google.gson.JsonSyntaxException("Crucible recipe result is null! Recipe is: " + id.toString());
        int amount = buffer.readVarInt();
        ItemStack output = buffer.readItemStack();
        String group = buffer.readString();
        int meltTime = buffer.readVarInt();
        int minTemp = buffer.readVarInt();
        int maxTemp = buffer.readVarInt();
        int experience = buffer.readVarInt();
        return this.factory.create(id, animation ,output, meltTime, minTemp, maxTemp, experience, group, ingredient);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe) {
        buffer.writeResourceLocation(recipe.getId());
        buffer.writeResourceLocation(recipe.getAnimation());
        buffer.writeVarInt(recipe.getInputs().size());
        for (Ingredient input : recipe.getInputs()) {
            input.write(buffer);
        }
        recipe.getIngredients().get(0).write(buffer);

        buffer.writeResourceLocation(Objects.requireNonNull(recipe.getRecipeOutput().getItem().getRegistryName()));

        buffer.writeVarInt(recipe.getMeltTime());
        buffer.writeVarInt(recipe.getMaxtemp());
        buffer.writeVarInt(recipe.getMintemp());
        buffer.writeFloat(recipe.getExperience());

    }

    public interface IFactory<T extends CrucibleRecipes> {
        T create(ResourceLocation id, ResourceLocation animation, ItemStack output, int meltTime, int maxTemp, int minTemp, int experience, String group, Ingredient... inputs);
    }
}

