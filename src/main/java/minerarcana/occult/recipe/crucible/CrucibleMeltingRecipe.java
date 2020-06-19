package minerarcana.occult.recipe.crucible;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import minerarcana.occult.api.PressureType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import static minerarcana.occult.recipe.OccultRecipe.CRUCIBLE_MELTING;

public class CrucibleMeltingRecipe extends AbstractCrucibleRecipe {

    private final NonNullList<ItemStack> itemsIn;
    private final FluidStack fluidOut;

    protected CrucibleMeltingRecipe(ResourceLocation id, int maxTemp, int minTemp, int cookTime, Object2IntMap<PressureType> pressureCreated, NonNullList<ItemStack> itemsIn, FluidStack fluidOut, float experience) {
        super(CRUCIBLE_MELTING, id, maxTemp, minTemp, cookTime, pressureCreated, experience);
        this.itemsIn = itemsIn;
        this.fluidOut = fluidOut;
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
