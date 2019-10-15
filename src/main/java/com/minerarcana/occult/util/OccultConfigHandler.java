package com.minerarcana.occult.util;


import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class OccultConfigHandler
{

    public static final ClientConfig CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;

    public static final CommonConfig COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;


    static {
        final Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        CLIENT_SPEC = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    static {
        final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static class ClientConfig {

        ClientConfig(ForgeConfigSpec.Builder builder) {


        }

    }

    public static class CommonConfig {

        public final ForgeConfigSpec.BooleanValue SPAWNBIOMES;

        public final ForgeConfigSpec.IntValue DEEPFORESTWEIGHT;
        public final ForgeConfigSpec.IntValue CHARREDWOODLANDWEIGHT;
        public final ForgeConfigSpec.IntValue BLEACHEDWOODWEIGHT;


        CommonConfig(ForgeConfigSpec.Builder builder) {
            builder.push("Biome Control");
            SPAWNBIOMES = builder
                    .comment("Set True to spawn Occult Biomes in the default provider")
                    .define("SpawnBiomes", true);
            builder.pop();

            builder.push("Biome Weights");
            DEEPFORESTWEIGHT = builder
                    .comment("This Controls the amount of this Biome spawns in the world")
                    .defineInRange("DeepForestWeight", 10, 0, 50);
            CHARREDWOODLANDWEIGHT = builder
                    .comment("This Controls the amount of this Biome spawns in the world")
                    .defineInRange("CharredWoodLandWeight", 10, 0, 50);
            BLEACHEDWOODWEIGHT = builder
                    .comment("This Controls the amount of this Biome spawns in the world")
                    .defineInRange("BleachedWoodWeight", 10, 0, 50);
            builder.pop();


        }

    }

}
