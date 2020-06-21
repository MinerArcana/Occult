package minerarcana.occult.content;

import minerarcana.occult.recipe.crucible.recipe.*;
import minerarcana.occult.recipe.crucible.serializers.*;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;

import static minerarcana.occult.content.OccultRegistryHandler.RECIPE_SERIALIZER;

public class OccultRecipeSerializers {

    public static RegistryObject<IRecipeSerializer<CrucibleCoolingRecipe>> CRUCIBLE_COOLING_SERIALIZER;
    public static RegistryObject<IRecipeSerializer<CrucibleDippingRecipe>> CRUCIBLE_DIPPING_SERIALIZER;
    public static RegistryObject<IRecipeSerializer<CrucibleCookingRecipe>> CRUCIBLE_COOKING_SERIALIZER;
    public static RegistryObject<IRecipeSerializer<CrucibleMixingRecipe>> CRUCIBLE_MIXING_SERIALIZER;
    public static RegistryObject<IRecipeSerializer<CrucibleMeltingRecipe>> CRUCIBLE_MELTING_SERIALIZER;

    public static IRecipeType<CrucibleCookingRecipe> CRUCIBLE_COOKING;
    public static IRecipeType<CrucibleMeltingRecipe> CRUCIBLE_MELTING;
    public static IRecipeType<CrucibleMixingRecipe> CRUCIBLE_MIXING;
    public static IRecipeType<CrucibleCoolingRecipe> CRUCIBLE_COOLING;
    public static IRecipeType<CrucibleDippingRecipe> CRUCIBLE_DIPPING;

    public static void init() {

        CRUCIBLE_COOKING = IRecipeType.register("crucible_cooking");
        CRUCIBLE_MELTING = IRecipeType.register("crucible_melting");
        CRUCIBLE_MIXING = IRecipeType.register("crucible_mixing");
        CRUCIBLE_COOLING = IRecipeType.register("crucible_cooling");
        CRUCIBLE_DIPPING = IRecipeType.register("crucible_dipping");

        CRUCIBLE_COOLING_SERIALIZER = RECIPE_SERIALIZER.register("crucible_cooling", CrucibleCoolingSerializer::new);
        CRUCIBLE_DIPPING_SERIALIZER = RECIPE_SERIALIZER.register("crucible_dipping", CrucibleDippingSerializer::new);
        CRUCIBLE_COOKING_SERIALIZER = RECIPE_SERIALIZER.register("crucible_cooking", CrucibleCookingSerializer::new);
        CRUCIBLE_MIXING_SERIALIZER = RECIPE_SERIALIZER.register("crucible_mixing", CrucibleMixingSerializer::new);
        CRUCIBLE_MELTING_SERIALIZER = RECIPE_SERIALIZER.register("crucible_melting", CrucibleMeltingSerializer::new);
    }

}
