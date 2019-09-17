package com.minerarcana.occult.recipes;

import com.minerarcana.occult.Occult;
import com.minerarcana.occult.recipes.machines.CrucibleRecipes;
import com.minerarcana.occult.recipes.serializers.CrucibleRecipeSerializer;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

import static com.minerarcana.occult.Occult.MOD_ID;


@ObjectHolder(MOD_ID)
@Mod.EventBusSubscriber
public class OccultRecipeTypes {

    public static IRecipeType<CrucibleRecipes> CRUCIBLETYPE;
    public static CrucibleRecipeSerializer<CrucibleRecipes> CRUCIBLESERIALIZER;

    public static void registerAll(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        if (!event.getName().equals(ForgeRegistries.RECIPE_SERIALIZERS.getRegistryName())) return;

        CRUCIBLETYPE = register("crucible");

        Occult.LOGGER.info("Recipe types registered");

        CRUCIBLESERIALIZER = register("crucible", new CrucibleRecipeSerializer<CrucibleRecipes>(CrucibleRecipes::new, 1, 5000, 150, 1 ,""));

        Occult.LOGGER.info("Recipe serializers registered");
    }

    private static <S extends IRecipeSerializer<R>, R extends IRecipe<?>> S register(String name, S serializer) {
        serializer.setRegistryName(getLocation(name));
        ForgeRegistries.RECIPE_SERIALIZERS.register(serializer);
        return serializer;
    }

    private static <R extends IRecipe<?>> IRecipeType<R> register(String name) {
        return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(name), new IRecipeType<R>() {
            public String toString() {
                return name;
            }
        });
    }

    public static ResourceLocation getLocation(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}