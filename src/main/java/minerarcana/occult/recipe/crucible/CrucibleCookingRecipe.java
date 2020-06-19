package minerarcana.occult.recipe.crucible;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import minerarcana.occult.api.PressureType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import static minerarcana.occult.recipe.OccultRecipeTypes.CRUCIBLE_COOKING;

public class CrucibleCookingRecipe extends AbstractCrucibleRecipe {

    protected CrucibleCookingRecipe(ResourceLocation id, int maxTemp, int minTemp, int cookTime, Object2IntMap<PressureType> pressureCreated, float experience) {
        super(CRUCIBLE_COOKING, id, maxTemp, minTemp, cookTime, pressureCreated, experience);
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return null;
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return null;
    }
}
