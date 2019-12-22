package com.minerarcana.occult.api;


import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryManager;

public class PressureRegistry {
    static {
        init();
    }

    public static IForgeRegistry<PressureType> pressureTypes = RegistryManager.ACTIVE.getRegistry(PressureType.class);

    private static void init() {
        new RegistryBuilder<PressureType>()
                .setName(new ResourceLocation("occult", "pressure"))
                .setType(PressureType.class)
                .setDefaultKey(new ResourceLocation("occult", "empty"))
                .create();
    }
}