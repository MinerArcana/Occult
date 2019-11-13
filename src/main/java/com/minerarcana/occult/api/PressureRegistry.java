package com.minerarcana.occult.api;


import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryManager;

public class PressureRegistry {
    static {
        init();
    }

    public static IForgeRegistry<Pressure> pressureTypes = RegistryManager.ACTIVE.getRegistry(Pressure.class);

    private static void init() {
        new RegistryBuilder<Pressure>()
                .setName(new ResourceLocation("occult", "pressure"))
                .setType(Pressure.class)
                .setDefaultKey(new ResourceLocation("occult", "empty"))
                .create();
    }
}