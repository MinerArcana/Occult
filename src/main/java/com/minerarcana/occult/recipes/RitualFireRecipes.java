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

public class RitualFireRecipes implements IRecipe {
	private final ResourceLocation id;
	private final ItemStack output;
	private final ImmutableList<Ingredient> inputs;
	private final int pressure;

	public RitualFireRecipes(ResourceLocation id, ItemStack output, int pressure, Ingredient... inputs) {
		Preconditions.checkArgument(inputs.length <= 16);
		Preconditions.checkArgument(pressure <= 100000);
		this.id = id;
		this.output = output;
		this.inputs = ImmutableList.copyOf(inputs);
		this.pressure = pressure;
	}

	public boolean matches(IItemHandler itemHandler) {
		List<Ingredient> ingredientsMissing = new ArrayList<>(inputs);

		for(int i = 0; i < itemHandler.getSlots(); i++) {
			ItemStack input = itemHandler.getStackInSlot(i);
			if(input.isEmpty())
				break;

			int stackIndex = -1;

			for(int j = 0; j < ingredientsMissing.size(); j++) {
				Ingredient ingr = ingredientsMissing.get(j);
				if(ingr.test(input)) {
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

	public List<Ingredient> getInputs() {
		return inputs;
	}

	public ItemStack getOutput() {
		return output;
	}

	public int getPressureUsage() {
		return pressure;
	}

	public void write(PacketBuffer buf) {
		buf.writeResourceLocation(id);
		buf.writeVarInt(inputs.size());
		for (Ingredient input : inputs) {
			input.write(buf);
		}
		buf.writeItemStack(output, false);
		buf.writeVarInt(pressure);
	}

	public static RitualFireRecipes read(PacketBuffer buf) {
		ResourceLocation id = buf.readResourceLocation();
		Ingredient[] inputs = new Ingredient[buf.readVarInt()];
		for (int i = 0; i < inputs.length; i++) {
			inputs[i] = Ingredient.read(buf);
		}
		ItemStack output = buf.readItemStack();
		int pressure = buf.readVarInt();
		return new RitualFireRecipes(id, output, pressure, inputs);
	}

}