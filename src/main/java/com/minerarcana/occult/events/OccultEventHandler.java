package com.minerarcana.occult.events;

import com.minerarcana.occult.util.lib.OccultNameLib;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

import static com.minerarcana.occult.Occult.MOD_ID;
import static com.minerarcana.occult.blocks.vegetation.StrangleGrass.onStrangleDeath;
import static com.minerarcana.occult.events.OccultStaticHelperMethods.registerOreOverworld;
import static com.minerarcana.occult.events.OccultStaticHelperMethods.registerOreSpecificBiome;
import static com.minerarcana.occult.util.damage.StrangeDamage.STRANGEGRASS;


@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OccultEventHandler {

    @SubscribeEvent
    public static void onFeatureRegistryEvent(RegistryEvent.Register<Feature<?>> event) {
        registerOreOverworld(OccultNameLib.cinnabarore,1, 1, 0, 24);
        registerOreSpecificBiome(OccultNameLib.saltore, Biome.Category.OCEAN,12, 20, 0, 80);
    }

    @SubscribeEvent
    public static void strangeDeaths(LivingDeathEvent event) {
        if (event.getSource().getDamageType().equals(STRANGEGRASS)) {
            Entity entity = event.getEntity();
            BlockPos entitypos = entity.getPosition();
            World entityworld = entity.world;
            Random random = entityworld.rand;
            onStrangleDeath(entitypos, entityworld, random);
        }
    }


}
