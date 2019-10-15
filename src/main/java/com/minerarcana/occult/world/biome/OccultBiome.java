package com.minerarcana.occult.common.world.biome;

import net.minecraft.world.biome.Biome;

public class OccultBiome extends Biome {
    public OccultBiomeBuilder wnBiomeBuilder;

    private static Builder analyseBuilder(Builder builder) {
        OccultBiomeBuilder wnbuilder = (OccultBiomeBuilder)builder;
        if (wnbuilder.getFog() == null) {
            wnbuilder.fog(new OccultBiomeBuilder.Fog(1, -1, -1.0F));
        }

        if (wnbuilder.getTopography() == null) {
            wnbuilder.topography(wnbuilder.getUnknownTopography());
        }

        if (wnbuilder.getClimate() == null) {
            wnbuilder.climate(wnbuilder.getUnknownClimate());
        }

        return wnbuilder;
    }

    public static OccultBiomeBuilder getWNBuilder(Builder builder) {
        OccultBiomeBuilder wnbuilder = (OccultBiomeBuilder)builder;
        if (wnbuilder.getFog() == null) {
            wnbuilder.fog(new OccultBiomeBuilder.Fog(1, -1, -1.0F));
        }

        if (wnbuilder.getTopography() == null) {
            wnbuilder.topography(wnbuilder.getUnknownTopography());
        }

        if (wnbuilder.getClimate() == null) {
            wnbuilder.climate(wnbuilder.getUnknownClimate());
        }

        return wnbuilder;
    }

    public OccultBiome(OccultBiomeBuilder biomeBuilder) {
        super(biomeBuilder);
        this.wnBiomeBuilder = biomeBuilder;
    }

    public int getCelsiusTemperature(float temp) {
        return (int)(temp * 100.0F) - 15;
    }
}

