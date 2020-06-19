package minerarcana.occult.recipe.crucible;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import minerarcana.occult.api.PressureType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractCrucibleRecipe implements IRecipe<IInventory> {

    protected final IRecipeType<?> type;
    protected final ResourceLocation id;
    protected final float experience;
    protected final int maxTemp;
    protected final int minTemp;
    protected final int cookTime;
    protected final Object2IntMap<PressureType> pressureCreated;


    protected AbstractCrucibleRecipe(IRecipeType<?> type, ResourceLocation id, int maxTemp, int minTemp ,int cookTime, Object2IntMap<PressureType> pressureCreated, float experience) {
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
