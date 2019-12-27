package com.minerarcana.occult.api.recipes;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

import static com.minerarcana.occult.Occult.MOD_ID;


@ObjectHolder(MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class OccultRecipeTypes {
    private static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER = new DeferredRegister<>(ForgeRegistries.RECIPE_SERIALIZERS, MOD_ID);


    public static IRecipeType<ItemToFluidRecipe> CRUCIBLE_ITEM_TO_FLUID;

    public static IRecipeType<FluidToItemRecipe> CRUCIBLE_FLUID_TO_ITEM;

    public static IRecipeType<ItemNFluidToItemRecipe> CRUCIBLE_ITEM_FLUID_TO_ITEM;

    @SubscribeEvent
    public static void registerAll(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        if (!event.getName().equals(ForgeRegistries.RECIPE_SERIALIZERS.getRegistryName())) return;

        CRUCIBLE_ITEM_TO_FLUID = register("item_to_fluid");
        CRUCIBLE_FLUID_TO_ITEM = register("fluid_to_item");
        CRUCIBLE_ITEM_FLUID_TO_ITEM = register("item_fluid_to_item");
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