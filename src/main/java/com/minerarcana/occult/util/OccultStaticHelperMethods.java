package com.minerarcana.occult.util;


import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OccultStaticHelperMethods {


    public static void registerOreOverworld(Block block, int maxVeinSize, int rarity, int minSpawnHeight, int maxSpawnHeight) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (!biome.getCategory().equals(Biome.Category.NETHER)) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(
                        Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, block.getDefaultState(), maxVeinSize),
                        Placement.COUNT_RANGE, new CountRangeConfig(rarity, minSpawnHeight, 0, maxSpawnHeight)));
            }
        }
    }

    public static void registerOreNether(Block block, int maxVeinSize, int rarity, int minSpawnHeight, int maxSpawnHeight) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (biome.getCategory().equals(Biome.Category.NETHER)) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(
                        Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, block.getDefaultState(), maxVeinSize),
                        Placement.COUNT_RANGE, new CountRangeConfig(rarity, minSpawnHeight, 0, maxSpawnHeight)));
            }
        }
    }

    public static void registerOreSpecificBiome( Block block, Biome.Category biomeCategory, int maxVeinSize, int rarity, int minSpawnHeight, int maxSpawnHeight) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (biome.getCategory().equals(biomeCategory)) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(
                        Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, block.getDefaultState(), maxVeinSize),
                        Placement.COUNT_RANGE, new CountRangeConfig(rarity, minSpawnHeight, 0, maxSpawnHeight)));
            }
        }
    }

    public static void registerFeatureSpecificBiome(Biome.Category biomeCategory, Feature feature, Placement placement, IFeatureConfig config, int frequency) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (biome.getCategory().equals(biomeCategory)) {
                biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                        feature, config, placement, new FrequencyConfig(frequency)));

            }
        }
    }
}
