package minerarcana.occult.recipe.crucible.recipe;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import minerarcana.occult.api.pressure.PressureType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import static minerarcana.occult.content.OccultRecipeSerializers.CRUCIBLE_MELTING_SERIALIZER;
import static minerarcana.occult.recipe.OccultRecipeTypes.CRUCIBLE_MELTING;

public class CrucibleMeltingRecipe extends AbstractCrucibleRecipe {

    private final NonNullList<ItemStack> itemsIn;
    private final FluidStack fluidOut;

    public CrucibleMeltingRecipe(ResourceLocation id, NonNullList<ItemStack> itemsIn, FluidStack fluidOut, int maxTemp, int minTemp, int cookTime, Object2IntMap<PressureType> pressureCreated, float experience) {
        super(CRUCIBLE_MELTING, id, maxTemp, minTemp, cookTime, pressureCreated, experience);
        this.itemsIn = itemsIn;
        this.fluidOut = fluidOut;
    }

    public NonNullList<ItemStack> getItemsIn() {
        return itemsIn;
    }

    public FluidStack getFluidOut() {
        return fluidOut;
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
        return CRUCIBLE_MELTING_SERIALIZER.get();
    }
}
