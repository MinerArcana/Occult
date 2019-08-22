package com.minerarcana.occult.biomes.features;

import com.minerarcana.occult.biomes.OccultBiomes;
import com.minerarcana.occult.world.biome.OccultBiome;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber
public class FogFeature {

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onColorFog(EntityViewRenderEvent.RenderFogEvent.FogColors e) {
        if (Minecraft.getInstance().world.getBiome(Minecraft.getInstance().player.getPosition()) == OccultBiomes.spookyforest) {
            e.setRed(0);
            e.setGreen(0);
            e.setBlue(0);
        }
    }
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onSetupFogDensity(EntityViewRenderEvent.RenderFogEvent.FogDensity event) {
        if (Minecraft.getInstance().world.getBiome(Minecraft.getInstance().player.getPosition()) == OccultBiomes.spookyforest) {
            PlayerEntity player = (PlayerEntity) Minecraft.getInstance().getRenderViewEntity();
            GlStateManager.enableFog();
            event.setCanceled(true);
            float opacity = .03f;
            event.setDensity(opacity);
        }
    }


}
