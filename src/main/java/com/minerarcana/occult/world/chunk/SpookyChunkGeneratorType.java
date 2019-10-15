package com.minerarcana.occult.world.chunk;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.*;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

@Mod.EventBusSubscriber
public class SpookyChunkGeneratorType {
    public static ChunkGeneratorType<OverworldGenSettings, SpookyChunkGeneratorEarth> OCCULTGEN;

    public SpookyChunkGeneratorType() {
        OCCULTGEN = register("occultgen", SpookyChunkGeneratorEarth::new, OverworldGenSettings::new, false);
    }

    @Nonnull
    @Deprecated
    private static <C extends GenerationSettings, T extends ChunkGenerator<C>> ChunkGeneratorType<C, T> register(String key, IChunkGeneratorFactory<C, T> factoryIn, Supplier<C> settingsIn, boolean canUseForBuffet) {
        return Registry.register(Registry.CHUNK_GENERATOR_TYPE, key, new ChunkGeneratorType<>(factoryIn, canUseForBuffet, settingsIn));
    }
}