package minerarcana.occult.recipe;

import minerarcana.occult.recipe.crucible.recipe.*;
import net.minecraft.item.crafting.IRecipeType;

public class  OccultRecipeTypes {

    public static IRecipeType<CrucibleCookingRecipe> CRUCIBLE_COOKING = IRecipeType.register("crucible_cooking");
    public static IRecipeType<CrucibleMeltingRecipe> CRUCIBLE_MELTING = IRecipeType.register("crucible_melting");
    public static IRecipeType<CrucibleMixingRecipe> CRUCIBLE_MIXING = IRecipeType.register("crucible_mixing");
    public static IRecipeType<CrucibleCoolingRecipe> CRUCIBLE_COOLING = IRecipeType.register("crucible_cooling");
    public static IRecipeType<CrucibleDippingRecipe> CRUCIBLE_DIPPING = IRecipeType.register("crucible_dipping");

}
