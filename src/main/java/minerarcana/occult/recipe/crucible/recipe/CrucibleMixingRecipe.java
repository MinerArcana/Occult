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

import static minerarcana.occult.content.OccultRecipeSerializers.CRUCIBLE_MIXING_SERIALIZER;
import static minerarcana.occult.recipe.OccultRecipeTypes.CRUCIBLE_MIXING;

public class CrucibleMixingRecipe extends AbstractCrucibleRecipe {

    private final NonNullList<ItemStack> itemsIn;
    private final FluidStack fluidIn;
    private final FluidStack fluidOut;

    public CrucibleMixingRecipe(ResourceLocation id, NonNullList<ItemStack> itemsIn, FluidStack fluidIn, FluidStack fluidOut,int maxTemp, int minTemp, int cookTime, Object2IntMap<PressureType> pressureCreated, float experience) {
        super(CRUCIBLE_MIXING, id, maxTemp, minTemp, cookTime, pressureCreated, experience);
        this.itemsIn = itemsIn;
        this.fluidIn = fluidIn;
        this.fluidOut = fluidOut;
    }

    public NonNullList<ItemStack> getItemsIn() {
        return itemsIn;
    }

    public FluidStack getFluidIn() {
        return fluidIn;
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
        return CRUCIBLE_MIXING_SERIALIZER.get();
    }
}
