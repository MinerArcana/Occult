package minerarcana.occult.recipe.crucible.recipe;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import minerarcana.occult.api.pressure.PressureType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

import static minerarcana.occult.content.OccultRecipeSerializers.CRUCIBLE_COOLING_SERIALIZER;
import static minerarcana.occult.recipe.OccultRecipeTypes.*;

public class CrucibleCoolingRecipe extends AbstractCrucibleRecipe {

    private final FluidStack fluidIn;
    private final ItemStack itemOut;

    public CrucibleCoolingRecipe(ResourceLocation id, FluidStack fluidIn, ItemStack itemOut, int maxTemp, int minTemp, int cookTime, Object2IntMap<PressureType> pressureCreated, float experience) {
        super(CRUCIBLE_COOLING, id, maxTemp, minTemp, cookTime, pressureCreated, experience);
        this.fluidIn = fluidIn;
        this.itemOut = itemOut;
    }

    public FluidStack getFluidIn() {
        return fluidIn;
    }

    public ItemStack getItemOut() {
        return itemOut;
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
        return CRUCIBLE_COOLING_SERIALIZER.get();
    }

    @Override
    public boolean matches(List<ItemStack> items, FluidStack fluid) {
        if(!fluid.isEmpty()){
            if(fluidIn.isFluidEqual(fluidIn)){
                return fluid.getAmount() >= fluidIn.getAmount();
            }
        }
        return false;
    }
}
