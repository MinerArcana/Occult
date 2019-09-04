package com.minerarcana.occult.capabilities.chunkpressure;

import com.minerarcana.occult.Occult;
import com.minerarcana.occult.api.pressure.IPressure;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.LazyOptional;

import static com.minerarcana.occult.api.pressure.ChunkPressureInject.PRESSURE_CAPABILITY;

public class ChunkPressure {

    public static final Direction DEFAULT_FACING = null;

    public static final int INFERNAL_MAX = 100;

    private static final ResourceLocation ID = new ResourceLocation(Occult.MOD_ID, "chunk_pressure");

    public static void register() {
        CapabilityManager.INSTANCE.register(IPressure.class, new Capability.IStorage<IPressure>() {

            @Override
            public INBT writeNBT(final Capability<IPressure> capability, final IPressure instance, final Direction side) {
                return new IntNBT(instance.getPressure());
            }

            @Override
            public void readNBT(final Capability<IPressure> capability, final IPressure instance, final Direction side, final INBT nbt) {
                if (!(instance instanceof IPressure))
                    throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");

                ((PressureStorage) instance).(((IntNBT) nbt).getInt());
            }
        }, () -> null);

    }

    public static LazyOptional<IPressure> getChunkPressure(final World world, final ChunkPos chunkPos) {
        return getChunkPressure(world.getChunk(chunkPos.x, chunkPos.z));
    }

    public static LazyOptional<IPressure> getChunkPressure(final Chunk chunk) {
        return chunk.getCapability(PRESSURE_CAPABILITY, DEFAULT_FACING);
    }








}