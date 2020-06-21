package minerarcana.occult.recipe.crucible.recipe;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import minerarcana.occult.api.pressure.PressureType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public abstract class AbstractCrucibleRecipe implements IRecipe<IInventory> {

    protected final IRecipeType<?> type;
    protected final ResourceLocation id;
    protected final float experience;
    protected final int maxTemp;
    protected final int minTemp;
    protected final int cookTime;
    protected final Object2IntMap<PressureType> pressureCreated;


    public AbstractCrucibleRecipe(IRecipeType<?> type, ResourceLocation id, int maxTemp, int minTemp ,int cookTime, Object2IntMap<PressureType> pressureCreated, float experience) {
        this.type = type;
        this.id = id;
        this.experience = experience;
        this.cookTime = cookTime;
        this.pressureCreated = pressureCreated;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public Object2IntMap<PressureType> getPressureCreated() {
        return pressureCreated;
    }

    public float getExperience() {
        return experience;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public abstract boolean matches(List<ItemStack> items, FluidStack fluid);

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeType<?> getType() {
        return type;
    }

    public int getCookTime() {
        return cookTime;
    }
}
