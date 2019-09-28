package com.minerarcana.occult.blocks.featureblocks.eldritchstone;

import com.google.common.collect.Maps;
import com.minerarcana.occult.api.pressure.IPressure;
import com.minerarcana.occult.api.pressure.PressureType;
import com.minerarcana.occult.capabilities.ChunkPressureCapability;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import java.util.Map;

import static com.minerarcana.occult.tileentity.OccultTileEntities.ELDRITCHSTONETILEENTITY;

public class EldritchStoneTileEntity extends TileEntity {


    public final Map<PressureType, Integer> pressures;


    public EldritchStoneTileEntity() {
        super(ELDRITCHSTONETILEENTITY);
        this.pressures = Maps.newHashMap();
    }


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap = ChunkPressureCapability.CHUNKPRESSURECAPABILITY){
            return handler.cast();
        }
    }
}
