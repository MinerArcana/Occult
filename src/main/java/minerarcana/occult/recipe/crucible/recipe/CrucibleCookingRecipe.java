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

import java.util.List;

import static minerarcana.occult.content.OccultRecipeSerializers.CRUCIBLE_COOKING_SERIALIZER;
import static minerarcana.occult.recipe.OccultRecipeTypes.CRUCIBLE_COOKING;

public class CrucibleCookingRecipe extends AbstractCrucibleRecipe {

    private final NonNullList<ItemStack> itemsIn;
    private final ItemStack itemOut;

    public CrucibleCookingRecipe(ResourceLocation id, NonNullList<ItemStack> itemsIn, ItemStack itemOut, int maxTemp, int minTemp, int cookTime, Object2IntMap<PressureType> pressureCreated, float experience) {
        super(CRUCIBLE_COOKING, id, maxTemp, minTemp, cookTime, pressureCreated, experience);
        this.itemsIn = itemsIn;
        this.itemOut = itemOut;
    }

    public ItemStack getItemOut() {
        return itemOut;
    }

    public NonNullList<ItemStack> getItemsIn() {
        return itemsIn;
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
        return CRUCIBLE_COOKING_SERIALIZER.get();
    }

    @Override
    public boolean matches(List<ItemStack> items, FluidStack fluid) {
        if(fluid.isEmpty()) {
            if (!items.isEmpty()) {
                int requiredItems = getItemsIn().size();
                for (ItemStack stack : getItemsIn()) {
                    for (ItemStack item : items) {
                        if (stack.getItem().equals(item.getItem())) {
                            if (item.getCount() >= stack.getCount()) {
                                --requiredItems;
                                break;
                            }
                        }
                    }
                }
                return requiredItems == 0;
            }
        }
        return false;
    }
}
