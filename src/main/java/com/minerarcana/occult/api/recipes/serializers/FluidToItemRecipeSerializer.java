package com.minerarcana.occult.api.recipes.serializers;


import com.google.common.collect.Streams;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.minerarcana.occult.api.pressure.PressureType;
import com.minerarcana.occult.api.recipes.machines.ItemToFluidRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class FluidToItemRecipeSerializer<T extends ItemToFluidRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    private final IFactory<T> factory;


    public FluidToItemRecipeSerializer(IFactory<T> factory) {
        this.factory = factory;

    }

    @Override
    public T read(ResourceLocation id, JsonObject json) {

        //input
        JsonElement ingredientJson = JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient");
        Ingredient ingredient = Ingredient.deserialize(ingredientJson);

        //output
        List<ItemStack> outputs = JSONUtils.isJsonArray(json, "output") ? Streams.stream(JSONUtils.getJsonArray(json, "output")).flatMap(element -> stacksFromJson(element).stream()).collect(Collectors.toList()) : stacksFromJson(json.get("output"));

        ItemStack alternateOut = JSONUtils.getItem(json,"alternateOutput").getDefaultInstance();
        PressureType type = null;
        PressureType pressureType = type.getTypeFromName(JSONUtils.getString(json, "pressureType"));
        //int
        int meltTime = JSONUtils.getInt(json, "meltTime", 150);
        int minTemp = JSONUtils.getInt(json, "minimumTemp", 151);
        int maxTemp = JSONUtils.getInt(json, "maximumTemp", 500);
        int experience = JSONUtils.getInt(json, "experience", 0);
        int pressureAmount = JSONUtils.getInt(json, "pressureAmount", 0);

        return this.factory.create(id, outputs, alternateOut,meltTime, minTemp, maxTemp, experience, pressureType, pressureAmount,ingredient);



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
        PressureType type = null;
        PressureType pressureType = type.getTypeFromName(buffer.readString());
        Ingredient[] inputs = new Ingredient[buffer.readVarInt()];
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = Ingredient.read(buffer);
        }
        ItemStack alternateOut = buffer.readItemStack();
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
        int pressureAmount = buffer.readVarInt();
        int meltTime = buffer.readVarInt();
        int minTemp = buffer.readVarInt();
        int maxTemp = buffer.readVarInt();
        int experience = buffer.readVarInt();
        return this.factory.create(id, outputs, alternateOut,maxTemp, minTemp, meltTime, experience, pressureType,pressureAmount,inputs);
    }

    @Override
    public void write(PacketBuffer buf, T recipe) {
        buf.writeString("CrucibleRecipes");
        buf.writeResourceLocation(recipe.getId());
        buf.writeVarInt(recipe.getInputs().size());
        buf.writeString(recipe.getPressureType().toString());
        for (Ingredient input : recipe.getInputs()) {
            input.write(buf);
        }
        buf.writeVarInt(recipe.getOutputs().size());
        for (ItemStack output : recipe.getOutputs()) {
            buf.writeItemStack(output, false);
        }
        buf.writeItemStack(recipe.getAlternateOut());
        buf.writeVarInt(recipe.getPressureAmount());
        buf.writeVarInt(recipe.getMaxtemp());
        buf.writeVarInt(recipe.getMintemp());
        buf.writeVarInt(recipe.getMeltTime());
        buf.writeFloat(recipe.getExperience());
    }

    public interface IFactory<T extends ItemToFluidRecipe> {
        T create(ResourceLocation id, List<ItemStack> output, ItemStack alternateOut, int meltTime, int maxTemp, int minTemp, int experience, PressureType pressureType, int pressureAmount, Ingredient... inputs);
    }


}

