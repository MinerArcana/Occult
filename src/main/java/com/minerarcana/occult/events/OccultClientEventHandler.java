package com.minerarcana.occult.events;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.minerarcana.occult.Occult.MOD_ID;
import static com.minerarcana.occult.util.lib.OccultNameLib.*;
import static com.minerarcana.occult.util.lib.OccultNameLib.bleachedforest;

@Mod.EventBusSubscriber(value = Dist.CLIENT ,modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OccultClientEventHandler
{

    //FOG
    //======================================================
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onColorFog(EntityViewRenderEvent.RenderFogEvent.FogColors e) {
        if (Minecraft.getInstance().world.getBiome(Minecraft.getInstance().player.getPosition()) == spookyforest) {
            e.setRed(0);
            e.setGreen(0);
            e.setBlue(0);
        }

        if (Minecraft.getInstance().world.getBiome(Minecraft.getInstance().player.getPosition()) == infernalforest) {
            e.setRed(252);
            e.setGreen(144);
            e.setBlue(3);
        }

        if (Minecraft.getInstance().world.getBiome(Minecraft.getInstance().player.getPosition()) == bleachedforest) {
            e.setRed(171);
            e.setGreen(179);
            e.setBlue(196);
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onSetupFogDensity(EntityViewRenderEvent.RenderFogEvent.FogDensity event) {
        if (Minecraft.getInstance().world.getBiome(Minecraft.getInstance().player.getPosition()) == spookyforest) {
            PlayerEntity player = (PlayerEntity) Minecraft.getInstance().getRenderViewEntity();
            GlStateManager.enableFog();
            event.setCanceled(true);
            float opacity = .04f;
            event.setDensity(opacity);
        }

        if (Minecraft.getInstance().world.getBiome(Minecraft.getInstance().player.getPosition()) == infernalforest) {
            PlayerEntity player = (PlayerEntity) Minecraft.getInstance().getRenderViewEntity();
            GlStateManager.enableFog();
            event.setCanceled(true);
            float opacity = .04f;
            event.setDensity(opacity);
        }

        if (Minecraft.getInstance().world.getBiome(Minecraft.getInstance().player.getPosition()) == bleachedforest) {
            PlayerEntity player = (PlayerEntity) Minecraft.getInstance().getRenderViewEntity();
            GlStateManager.enableFog();
            event.setCanceled(true);
            float opacity = .04f;
            event.setDensity(opacity);
        }
    }



}