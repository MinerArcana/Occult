package com.minerarcana.occult.common.world.noise;

import net.minecraft.world.World;

import java.util.Random;

public class PerlinOctaveGenerator extends OctaveGenerator {
    public PerlinOctaveGenerator(World world, int octaves) {
        this(new Random(world.getSeed()), octaves);
    }

    public PerlinOctaveGenerator(long seed, int octaves) {
        this(new Random(seed), octaves);
    }

    public PerlinOctaveGenerator(Random rand, int octaves) {
        super(createOctaves(rand, octaves));
    }

    private static NoiseGenerator[] createOctaves(Random rand, int octaves) {
        NoiseGenerator[] result = new NoiseGenerator[octaves];

        for(int i = 0; i < octaves; ++i) {
            result[i] = new PerlinNoiseGenerator(rand);
        }

        return result;
    }
}

