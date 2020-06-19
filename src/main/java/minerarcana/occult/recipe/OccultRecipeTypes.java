package minerarcana.occult.recipe;

import minerarcana.occult.recipe.crucible.CrucibleCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;

public class OccultRecipeTypes {

    public static IRecipeType<CrucibleCookingRecipe> CRUCIBLE_COOKING = IRecipeType.register("crucible_cooking");
    public static IRecipeType<CrucibleCookingRecipe> CRUCIBLE_MELTING = IRecipeType.register("crucible_melting");
    public static IRecipeType<CrucibleCookingRecipe> CRUCIBLE_MIXING = IRecipeType.register("crucible_mixing");
    public static IRecipeType<CrucibleCookingRecipe> CRUCIBLE_COOLING = IRecipeType.register("crucible_cooling");

}
