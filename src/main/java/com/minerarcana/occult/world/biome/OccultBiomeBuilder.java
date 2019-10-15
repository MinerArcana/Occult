package com.minerarcana.occult.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class OccultBiomeBuilder extends Biome.Builder {
    @Nullable
    private ConfiguredSurfaceBuilder<?> surfaceBuilder;
    @Nullable
    private Biome.RainType precipitation;
    @Nullable
    private Biome.Category category;
    @Nullable
    private OccultBiomeBuilder.Topography topography;
    @Nullable
    private OccultBiomeBuilder.Climate climate;
    @Nullable
    private Float depth;
    @Nullable
    private Float scale;
    @Nullable
    private Float smoothness;
    @Nullable
    private Float temperature;
    @Nullable
    private Float downfall;
    @Nullable
    private Integer waterColor;
    @Nullable
    private Integer waterFogColor;
    @Nullable
    private OccultBiomeBuilder.Fog fog;
    @Nullable
    private String parent;

    public OccultBiomeBuilder() {
    }

    public <SC extends ISurfaceBuilderConfig> OccultBiomeBuilder surfaceBuilder(SurfaceBuilder<SC> p_222351_1_, SC p_222351_2_) {
        this.surfaceBuilder = new ConfiguredSurfaceBuilder(p_222351_1_, p_222351_2_);
        return this;
    }

    public OccultBiomeBuilder surfaceBuilder(ConfiguredSurfaceBuilder<?> surfaceBuilderIn) {
        this.surfaceBuilder = surfaceBuilderIn;
        return this;
    }

    public OccultBiomeBuilder precipitation(Biome.RainType precipitationIn) {
        this.precipitation = precipitationIn;
        return this;
    }

    public OccultBiomeBuilder category(Biome.Category biomeCategory) {
        this.category = biomeCategory;
        return this;
    }

    public OccultBiomeBuilder topography(OccultBiomeBuilder.Topography biometopography) {
        this.topography = biometopography;
        return this;
    }

    public OccultBiomeBuilder climate(OccultBiomeBuilder.Climate biomeClimate) {
        this.climate = biomeClimate;
        return this;
    }

    public OccultBiomeBuilder depth(float depthIn) {
        this.depth = depthIn;
        return this;
    }

    public OccultBiomeBuilder scale(float scaleIn) {
        this.scale = scaleIn;
        return this;
    }

    public OccultBiomeBuilder smoothness(float smoothness) {
        this.smoothness = smoothness;
        return this;
    }

    public OccultBiomeBuilder temperature(float temperatureIn) {
        this.temperature = temperatureIn;
        return this;
    }

    public OccultBiomeBuilder downfall(float downfallIn) {
        this.downfall = downfallIn;
        return this;
    }

    public OccultBiomeBuilder waterColor(int waterColorIn) {
        this.waterColor = waterColorIn;
        return this;
    }

    public OccultBiomeBuilder waterFogColor(int waterFogColorIn) {
        this.waterFogColor = waterFogColorIn;
        return this;
    }

    public OccultBiomeBuilder fog(OccultBiomeBuilder.Fog fog) {
        this.fog = fog;
        return this;
    }

    public OccultBiomeBuilder parent(@Nullable String parentIn) {
        this.parent = parentIn;
        return this;
    }

    public String toString() {
        return "WNBiomeBuilder{\nsurfaceBuilder=" + this.surfaceBuilder + ",\nprecipitation=" + this.precipitation + ",\nbiomeCategory=" + this.category + ",\ntopography=" + this.topography + ",\nclimate=" + this.climate + ",\ndepth=" + this.depth + ",\nscale=" + this.scale + ",\ntemperature=" + this.temperature + ",\ndownfall=" + this.downfall + ",\nwaterColor=" + this.waterColor + ",\nwaterFogColor=" + this.waterFogColor + ",\nparent='" + this.parent + '\'' + "\n" + '}';
    }

    @Nullable
    public OccultBiomeBuilder.Climate getClimate() {
        return this.climate;
    }

    @Nullable
    public OccultBiomeBuilder.Topography getTopography() {
        return this.topography;
    }

    @Nullable
    public OccultBiomeBuilder.Fog getFog() {
        return this.fog;
    }

    public OccultBiomeBuilder.Climate getUnknownClimate() {
        return OccultBiomeBuilder.Climate.CONTINENTAL_COOL;
    }

    public OccultBiomeBuilder.Topography getUnknownTopography() {
        return OccultBiomeBuilder.Topography.LOWLANDS;
    }

    public static enum Topography {
        NONE("none"),
        LOWLANDS("lowlands"),
        HIGHLANDS("highlands"),
        LOW_MOUNTAINS("low_mountains"),
        HIGH_MOUNTAINS("high_mountains"),
        LAKE_DISTRICT("lake_district"),
        SEA_DISTRICT("sea_district"),
        BASIN("basin");

        private static final Map<String, Topography> BY_NAME = (Map) Arrays.stream(values()).collect(Collectors.toMap(OccultBiomeBuilder.Topography::getName, (name) -> {
            return name;
        }));
        private final String name;

        private Topography(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    public static enum Climate {
        NONE("none"),
        POLAR("polar"),
        SUBPOLAR("subpolar"),
        CONTINENTAL_COOL("cool_continental"),
        OCEANIC_COOL("cool_oceanic"),
        CONTINENTAL_WARM("warm_continental"),
        OCEANIC_WARM("warm_oceanic"),
        DRY_SUBTROPICAL("dry_subtropical"),
        MOIST_SUBTROPICAL("moist_subtropical"),
        DRY_TROPICAL("dry_tropical"),
        MOIST_TROPICAL("moist_tropical");

        private static final Map<String, OccultBiomeBuilder.Climate> BY_NAME = (Map)Arrays.stream(values()).collect(Collectors.toMap(OccultBiomeBuilder.Climate::getName, (name) -> {
            return name;
        }));
        private final String name;

        private Climate(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    public static class Fog {
        private int fogStatus;
        private int color;
        private double density;
        private int defaultColor = 16777215;
        private float defaultDensity = 0.1F;

        public Fog(int fogStatus, int color, float density) {
            this.fogStatus = fogStatus;
            this.color = color;
            this.density = (double)density;
            if (color == -1) {
                this.color = this.defaultColor;
            }

            if (density == -1.0F) {
                this.density = (double)this.defaultDensity;
            }

        }
    }
}

