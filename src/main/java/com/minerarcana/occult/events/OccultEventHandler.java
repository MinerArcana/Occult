package com.minerarcana.occult.events;

import com.minerarcana.occult.util.OccultConfigHandler;
import com.minerarcana.occult.util.damage.StrangeDamage;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.common.BiomeDictionary.*;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

import java.util.Random;

import static com.minerarcana.occult.Occult.MOD_ID;
import static com.minerarcana.occult.blocks.vegetation.StrangleGrass.onStrangleDeath;
import static com.minerarcana.occult.util.lib.OccultNameLib.*;
import static net.minecraftforge.common.BiomeDictionary.Type.*;
import static net.minecraftforge.common.BiomeDictionary.addTypes;
import static net.minecraftforge.common.BiomeManager.addBiome;
import static net.minecraftforge.common.BiomeManager.addSpawnBiome;


@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OccultEventHandler {


    @SubscribeEvent
    public static void oreFeature(RegistryEvent.Register<Feature<?>> event) {

        // ForgeRegistries.BIOMES(biome -> biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, OccultBlocks.saltore.getDefaultState(), , Placement.COUNT_RANGE, );

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


    @SubscribeEvent
    public static void strangeDeaths(LivingDeathEvent event) {
        if (event.getSource() instanceof StrangeDamage) {
            Entity entity = event.getEntity();
            BlockPos entitypos = entity.getPosition();
            World entityworld = entity.world;
            Random random = entityworld.rand;
            onStrangleDeath(entitypos, entityworld, random);
        }
    }



}
