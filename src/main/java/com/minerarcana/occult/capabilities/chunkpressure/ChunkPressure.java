package com.minerarcana.occult.capabilities.chunkpressure;

import com.minerarcana.occult.Occult;
import com.minerarcana.occult.capabilities.infernal.InfernalPressure;
import com.minerarcana.occult.capabilities.infernal.InfernalStorage;
import com.minerarcana.occult.capabilities.nature.NaturePressure;
import com.minerarcana.occult.capabilities.nature.NatureStorage;
import com.minerarcana.occult.capabilities.spirit.SpiritPressure;
import com.minerarcana.occult.capabilities.spirit.SpiritStorage;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.LazyOptional;

public class ChunkPressure {

    @CapabilityInject(InfernalPressure.class)
    public static final Capability<InfernalPressure> INFERNAL_CAP = null;

    @CapabilityInject(NaturePressure.class)
    public static final Capability<NaturePressure> NATURAL_CAP = null;

    @CapabilityInject(SpiritPressure.class)
    public static final Capability<SpiritPressure> SPIRIT_CAP = null;

    public static final Direction DEFAULT_FACING = null;

    public static final int INFERNAL_MAX = 100;

    public static final int NATURE_MAX = 100;

    public static final int SPIRIT_MAX = 100;

    private static final ResourceLocation ID = new ResourceLocation(Occult.MOD_ID, "chunk_pressure");

    public static void register() {
        CapabilityManager.INSTANCE.register(SpiritPressure.class, new Capability.IStorage<SpiritPressure>() {

            @Override
            public INBT writeNBT(final Capability<SpiritPressure> capability, final SpiritPressure instance, final Direction side) {
                return new IntNBT(instance.getSpiritualPressure());
            }

            @Override
            public void readNBT(final Capability<SpiritPressure> capability, final SpiritPressure instance, final Direction side, final INBT nbt) {
                if (!(instance instanceof SpiritStorage))
                    throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");

                ((SpiritStorage) instance).setEnergy(((IntNBT) nbt).getInt());
            }
        }, () -> null);

        CapabilityManager.INSTANCE.register(InfernalPressure.class, new Capability.IStorage<InfernalPressure>() {

            @Override
            public INBT writeNBT(final Capability<InfernalPressure> capability, final InfernalPressure instance, final Direction side) {
                return new IntNBT(instance.getInfernalPressure());
            }

            @Override
            public void readNBT(final Capability<InfernalPressure> capability, final InfernalPressure instance, final Direction side, final INBT nbt) {
                if (!(instance instanceof InfernalStorage))
                    throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");

                ((InfernalStorage) instance).setEnergy(((IntNBT) nbt).getInt());
            }
        }, () -> null);

        CapabilityManager.INSTANCE.register(NaturePressure.class, new Capability.IStorage<NaturePressure>() {

            @Override
            public INBT writeNBT(final Capability<NaturePressure> capability, final NaturePressure instance, final Direction side) {
                return new IntNBT(instance.getNaturalPressure());
            }

            @Override
            public void readNBT(final Capability<NaturePressure> capability, final NaturePressure instance, final Direction side, final INBT nbt) {
                if (!(instance instanceof NatureStorage))
                    throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");

                ((NatureStorage) instance).setEnergy(((IntNBT) nbt).getInt());
            }
        }, () -> null);

    }

    public static LazyOptional<InfernalPressure> getChunkInfernalPressure(final World world, final ChunkPos chunkPos) {
        return getChunkInfernalPressure(world.getChunk(chunkPos.x, chunkPos.z));
    }

    public static LazyOptional<InfernalPressure> getChunkInfernalPressure(final Chunk chunk) {
        return chunk.getCapability(INFERNAL_CAP, DEFAULT_FACING);
    }

    public static LazyOptional<NaturePressure> getChunkNaturePressure(final World world, final ChunkPos chunkPos) {
        return getChunkNaturePressure(world.getChunk(chunkPos.x, chunkPos.z));
    }

    public static LazyOptional<NaturePressure> getChunkNaturePressure(final Chunk chunk) {
        return chunk.getCapability(NATURAL_CAP, DEFAULT_FACING);
    }

    public static LazyOptional<SpiritPressure> getChunkSpiritPressure(final World world, final ChunkPos chunkPos) {
        return getChunkSpiritPressure(world.getChunk(chunkPos.x, chunkPos.z));
    }

    public static LazyOptional<SpiritPressure> getChunkSpiritPressure(final Chunk chunk) {
        return chunk.getCapability(SPIRIT_CAP, DEFAULT_FACING);
    }







}