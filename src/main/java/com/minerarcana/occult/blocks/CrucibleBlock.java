package com.minerarcana.occult.blocks;


import com.minerarcana.occult.blocks.tileentity.CrucibleTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class CrucibleBlock extends Block {

    public CrucibleBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new CrucibleTile();
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
        TileEntity entity = world.getTileEntity(pos);
        if (!player.isSneaking()) {
            if (entity instanceof CrucibleTile) {
                ((CrucibleTile) entity).extractInsertItemMethod(player, hand);
            }
        }
        return true;
    }
}
