package com.minerarcana.occult.common.biomes.surfaces;

import com.minerarcana.occult.common.biomes.surfaces.overworld.DeepSpookySurface;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;


    @Mod.EventBusSubscriber(
            modid = "occult",
            bus = Mod.EventBusSubscriber.Bus.MOD
    )
    @ObjectHolder("occult")
    public class OccultSurfaceRegistry {

        public static final SurfaceBuilder<SurfaceBuilderConfig> DEEPSPOOKYSURFACE = new DeepSpookySurface();

        private static IForgeRegistry<SurfaceBuilder<?>> registry;

        @SubscribeEvent
        public static void onRegisterBiomes(RegistryEvent.Register<SurfaceBuilder<?>> event) {
            if (registry == null) {
                registry = event.getRegistry();
            }

            registerSurfaceBuilder(DEEPSPOOKYSURFACE, "deepspookysurface");

        }


        public static SurfaceBuilder<?> registerSurfaceBuilder(SurfaceBuilder<?> surface, String name) {
            if (registry == null) {
                throw new NullPointerException("surface registry aint workin!");
            } else {
                surface.setRegistryName("occult", name);
                registry.register(surface);
                return surface;
            }
        }
}
