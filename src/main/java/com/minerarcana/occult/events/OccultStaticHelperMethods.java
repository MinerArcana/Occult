package com.minerarcana.occult.events;

import com.minerarcana.occult.util.OccultConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.ForgeRegistries;

import static com.minerarcana.occult.util.lib.OccultNameLib.*;
import static net.minecraftforge.common.BiomeDictionary.Type.*;
import static net.minecraftforge.common.BiomeDictionary.addTypes;
import static net.minecraftforge.common.BiomeManager.addBiome;
import static net.minecraftforge.common.BiomeManager.addSpawnBiome;

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

    public static void registerOccultBiomes() {
        if (OccultConfigHandler.COMMON.SPAWNBIOMES.get()) {
            addSpawnBiome(spookyforest);
            addSpawnBiome(infernalforest);
            addSpawnBiome(bleachedforest);
        }

        addTypes(spookyforest, SPOOKY, FOREST, DRY, DEAD, MAGICAL);
        addTypes(infernalforest, SPOOKY, FOREST, DRY, MAGICAL);
        addTypes(bleachedforest, SPOOKY, FOREST, DRY, MAGICAL);

        addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(spookyforest, OccultConfigHandler.COMMON.DEEPFORESTWEIGHT.get()));
        addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(infernalforest, OccultConfigHandler.COMMON.CHARREDWOODLANDWEIGHT.get()));
        addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(bleachedforest, OccultConfigHandler.COMMON.BLEACHEDWOODWEIGHT.get()));

    }

}
