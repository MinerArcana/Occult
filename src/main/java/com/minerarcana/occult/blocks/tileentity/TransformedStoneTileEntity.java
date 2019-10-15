package com.minerarcana.occult.blocks.tileentity;

import com.minerarcana.occult.blocks.EldritchStone;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.minerarcana.occult.util.lib.OccultNameLib.*;


@Mod.EventBusSubscriber
public class TransformedStoneTileEntity extends TileEntity {

    public TransformedStoneTileEntity() {
        super(TRANSFORMEDSTONETILEENTITY);
    }

    @SubscribeEvent
    public void TransformEldritchStone() {
        BlockState teBlockAbove = world.getBlockState(pos.up());
        BlockState teBlockAbove2 = world.getBlockState(pos.up(2));

        if (teBlockAbove.getBlock() instanceof EldritchStone && teBlockAbove2.getBlock() instanceof EldritchStone) {
                world.setBlockState(pos, transformedstone.getDefaultState());
                world.setBlockState(pos.up(), Blocks.AIR.getDefaultState());
                world.setBlockState(pos.up(2), Blocks.AIR.getDefaultState());
        }
    }

}
