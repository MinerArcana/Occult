package com.minerarcana.occult.biomes;

import com.minerarcana.occult.biomes.overworld.TheDeepForest;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class OccultBiomes
{
    public static Biome spookyforest;

    @SubscribeEvent
    public static void registerBiomes(final RegistryEvent.Register<Biome> event)
    {
        spookyforest = registerBiome(new TheDeepForest(), BiomeManager.BiomeType.WARM , "spookyforest", 100, BiomeDictionary.Type.SPOOKY);

    }


    public static Biome registerBiome (Biome biome, BiomeManager.BiomeType biometype,  String name, int weight, BiomeDictionary.Type types)
    {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeManager.addSpawnBiome(biome);
        BiomeManager.addBiome(biometype, new BiomeManager.BiomeEntry(biome, weight));
        BiomeDictionary.addTypes(biome, types);
        System.out.println(name + "registered");
        return biome;

    }


}
