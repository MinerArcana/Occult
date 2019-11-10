package com.minerarcana.occult.controllermultiblock;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryManager;

public class MultiblockRegistries {
    static {
        init();
    }

    public static IForgeRegistry<MultiblockRegistry> FACES = RegistryManager.ACTIVE.getRegistry(MultiblockRegistry.class);

    private static void init() {
        new RegistryBuilder<MultiblockRegistry>()
                .setName(new ResourceLocation("reengineeredtoolbox", "faces"))
                .setType(MultiblockRegistry.class)
                .setDefaultKey(new ResourceLocation("reengineeredtoolbox", "empty"))
                .create();
    }
}