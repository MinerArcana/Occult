package com.minerarcana.occult.recipes;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;

public class CrucibleRecipes implements IRecipe {
    private final ResourceLocation id;
    private final ResourceLocation animation;
    private final ImmutableList<ItemStack> output;
    private final ImmutableList<Ingredient> inputs;
    private final int maxtemp;
    private final int mintemp;

    public CrucibleRecipes (ResourceLocation id, ResourceLocation animation, ItemStack[] output, int maxTemp, int minTemp, Ingredient... inputs)
    {
        Preconditions.checkArgument(inputs.length <= 3);
        Preconditions.checkArgument(output.length <= 3);
        Preconditions.checkArgument(maxTemp <= 5000);
        Preconditions.checkArgument(minTemp >= 500);
        this.id = id;
        this.animation = animation;
        this.inputs = ImmutableList.copyOf(inputs);
        this.output = ImmutableList.copyOf(output);
        this.mintemp = minTemp;
        this.maxtemp = maxTemp;

    }

    public boolean matches(IItemHandler itemHandler) {
        List<Ingredient> ingredientsMissing = new ArrayList<>(inputs);

        for (int i = 0; i < itemHandler.getSlots(); i++) {
            ItemStack input = itemHandler.getStackInSlot(i);
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

    public ResourceLocation getId() {
        return id;
    }

    public ResourceLocation getAnimation() {
        return animation;
    }


    @Override
    public List<Ingredient> getInputs() {
        return inputs;
    }

    @Override
    public List<ItemStack> getOutput() {
        return output;
    }

    public int getMaxtemp() {
        return maxtemp;
    }

    public int getMintemp() {
        return mintemp;
    }

    public void write(PacketBuffer buf) {
        buf.writeResourceLocation(id);
        buf.writeResourceLocation(animation);
        buf.writeVarInt(inputs.size());
        for (Ingredient input : inputs) {
            input.write(buf);
        }
        buf.writeVarInt(output.size());
        for(ItemStack stack : output){
            buf.writeItemStack(stack, false);
        }
        buf.writeVarInt(maxtemp);
        buf.writeVarInt(mintemp);
    }

    public static CrucibleRecipes read(PacketBuffer buf) {
        ResourceLocation id = buf.readResourceLocation();
        ResourceLocation animation = buf.readResourceLocation();
        Ingredient[] inputs = new Ingredient[buf.readVarInt()];
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = Ingredient.read(buf);
        }
        ItemStack[] output = new ItemStack[buf.readVarInt()];
        for (int i = 0; i < output.length; i++)
        {
            output[i] = buf.readItemStack();
        }
        int maxtemp = buf.readVarInt();
        int mintemp = buf.readVarInt();

        return new CrucibleRecipes(id, animation, output, maxtemp, mintemp, inputs);
    }



}
