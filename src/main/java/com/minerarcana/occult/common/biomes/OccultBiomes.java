package com.minerarcana.occult.common.biomes;

import com.minerarcana.occult.common.biomes.overworld.TheBleachedWood;
import com.minerarcana.occult.common.biomes.overworld.TheCharredWoodlands;
import com.minerarcana.occult.common.biomes.overworld.TheDeepForest;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.MOD)
public class OccultBiomes
{
    public static Biome spookyforest;
    public static Biome infernalforest;
    public static Biome bleachedforest;

    @SubscribeEvent
    public static void registerBiomes(final RegistryEvent.Register<Biome> event)
    {
        spookyforest = registerBiome(new TheDeepForest(), BiomeManager.BiomeType.WARM , "spookyforest", 10, BiomeDictionary.Type.SPOOKY);
        infernalforest = registerBiome(new TheCharredWoodlands(), BiomeManager.BiomeType.DESERT , "infernalforest", 10, BiomeDictionary.Type.SPOOKY);
        bleachedforest = registerBiome(new TheBleachedWood(), BiomeManager.BiomeType.WARM , "bleachedforest", 10, BiomeDictionary.Type.SPOOKY);

    }


    public static Biome registerBiome (Biome biome, BiomeManager.BiomeType biometype, String name, int weight, BiomeDictionary.Type types)
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
