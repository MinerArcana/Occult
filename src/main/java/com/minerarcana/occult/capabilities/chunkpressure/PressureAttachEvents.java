package com.minerarcana.occult.capabilities.chunkpressure;

import com.minerarcana.occult.Occult;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PressureAttachEvents {


    private static void attachChunkCapability(AttachCapabilitiesEvent<Chunk> event) {

            event.addCapability(new ResourceLocation(Occult.MOD_ID, "chunkpressure"), new ChunkPressureProvider());

    }

    private static void attachTileEntityCapability(AttachCapabilitiesEvent<TileEntity> event) {

        event.addCapability(new ResourceLocation(Occult.MOD_ID, "chunkpressure"), new ChunkPressureProvider());

    }

    private static void attachEntityCapability(AttachCapabilitiesEvent<Entity> event) {

        event.addCapability(new ResourceLocation(Occult.MOD_ID, "chunkpressure"), new ChunkPressureProvider());

    }

    private static void attachWorldCapability(AttachCapabilitiesEvent<ItemEntity> event) {

        event.addCapability(new ResourceLocation(Occult.MOD_ID, "chunkpressure"), new ChunkPressureProvider());

    }



}
