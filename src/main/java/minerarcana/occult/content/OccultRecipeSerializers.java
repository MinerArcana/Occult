package minerarcana.occult.content;

import minerarcana.occult.recipe.crucible.recipe.*;
import minerarcana.occult.recipe.crucible.serializers.*;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;

import static minerarcana.occult.content.OccultRegistryHandler.RECIPE_SERIALIZER;

public class OccultRecipeSerializers {

    public static RegistryObject<IRecipeSerializer<CrucibleCoolingRecipe>> CRUCIBLE_COOLING_SERIALIZER;
    public static RegistryObject<IRecipeSerializer<CrucibleDippingRecipe>> CRUCIBLE_DIPPING_SERIALIZER;
    public static RegistryObject<IRecipeSerializer<CrucibleCookingRecipe>> CRUCIBLE_COOKING_SERIALIZER;
    public static RegistryObject<IRecipeSerializer<CrucibleMixingRecipe>> CRUCIBLE_MIXING_SERIALIZER;
    public static RegistryObject<IRecipeSerializer<CrucibleMeltingRecipe>> CRUCIBLE_MELTING_SERIALIZER;

    public static void init() {
        CRUCIBLE_COOLING_SERIALIZER = RECIPE_SERIALIZER.register("crucible_cooling_serializer", CrucibleCoolingSerializer::new);
        CRUCIBLE_DIPPING_SERIALIZER = RECIPE_SERIALIZER.register("crucible_dipping_serializer", CrucibleDippingSerializer::new);
        CRUCIBLE_COOKING_SERIALIZER = RECIPE_SERIALIZER.register("crucible_cooking_serializer", CrucibleCookingSerializer::new);
        CRUCIBLE_MIXING_SERIALIZER = RECIPE_SERIALIZER.register("crucible_mixing_serializer", CrucibleMixingSerializer::new);
        CRUCIBLE_MELTING_SERIALIZER = RECIPE_SERIALIZER.register("crucible_melting_serializer", CrucibleMeltingSerializer::new);
    }

}
