package com.minerarcana.occult.common.world.noise;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.INoiseGenerator;
import net.minecraft.world.gen.ImprovedNoiseGenerator;

import java.util.Random;

public class SimplexOctavesGenerator implements INoiseGenerator {
    private final ImprovedNoiseGenerator[] octaves;

    public SimplexOctavesGenerator(Random seed, int octavesIn) {
        this.octaves = new ImprovedNoiseGenerator[octavesIn];

        for(int i = 0; i < octavesIn; ++i) {
            this.octaves[i] = new ImprovedNoiseGenerator(seed);
        }

    }

    public double func_205563_a(double p_205563_1_, double p_205563_3_, double p_205563_5_) {
        return this.func_215462_a(p_205563_1_, p_205563_3_, p_205563_5_, 0.0D, 0.0D, false);
    }

    public double func_215462_a(double p_215462_1_, double p_215462_3_, double p_215462_5_, double p_215462_7_, double p_215462_9_, boolean p_215462_11_) {
        double d0 = 0.0D;
        double d1 = 1.0D;
        ImprovedNoiseGenerator[] var16 = this.octaves;
        int var17 = var16.length;

        for(int var18 = 0; var18 < var17; ++var18) {
            ImprovedNoiseGenerator improvednoisegenerator = var16[var18];
            d0 += improvednoisegenerator.func_215456_a(maintainPrecision(p_215462_1_ * d1), p_215462_11_ ? -improvednoisegenerator.yCoord : maintainPrecision(p_215462_3_ * d1), maintainPrecision(p_215462_5_ * d1), p_215462_7_ * d1, p_215462_9_ * d1) / d1;
            d1 /= 2.0D;
        }

        return d0;
    }

    public ImprovedNoiseGenerator getOctave(int p_215463_1_) {
        return this.octaves[p_215463_1_];
    }

    public static double maintainPrecision(double p_215461_0_) {
        return p_215461_0_ - (double) MathHelper.lfloor(p_215461_0_ / 3.3554432E7D + 0.5D) * 3.3554432E7D;
    }

    public double func_215460_a(double p_215460_1_, double p_215460_3_, double p_215460_5_, double p_215460_7_) {
        return this.func_215462_a(p_215460_1_, p_215460_3_, 0.0D, p_215460_5_, p_215460_7_, false);
    }
}

