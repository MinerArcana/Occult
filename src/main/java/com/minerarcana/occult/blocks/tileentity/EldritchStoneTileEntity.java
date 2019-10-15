package com.minerarcana.occult.blocks.tileentity;

import com.google.common.collect.Maps;
import com.minerarcana.occult.api.pressure.PressureType;
import com.minerarcana.occult.blocks.EldritchStone;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

import java.util.Map;


import static com.minerarcana.occult.util.lib.OccultNameLib.ELDRITCHSTONETILEENTITY;
import static com.minerarcana.occult.util.lib.OccultNameLib.transformedstone;

public class EldritchStoneTileEntity extends TileEntity implements ITickableTileEntity {


    public final Map<PressureType, Integer> pressures;

    public EldritchStoneTileEntity() {
        super(ELDRITCHSTONETILEENTITY);
        this.pressures = Maps.newHashMap();
    }


    @Override
    public void tick() {
        BlockState teBlockAbove = world.getBlockState(pos.up());
        BlockState teBlockAbove2 = world.getBlockState(pos.up(2));

        if (teBlockAbove.getBlock() instanceof EldritchStone && teBlockAbove2.getBlock() instanceof EldritchStone) {
            world.setBlockState(pos, transformedstone.getDefaultState());
            world.setBlockState(pos.up(), Blocks.AIR.getDefaultState());
            world.setBlockState(pos.up(2), Blocks.AIR.getDefaultState());
        }
    }
}
