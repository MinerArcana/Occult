package com.minerarcana.occult.multiblocksystem;


import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

public class Utils {

	/*
     * Everything below this line is #StolenByTheBrassGoggledCoders from ZeroCore. Temporarily!
	 * TODO
	 */

    public static int getChunkXFromBlock(int blockX) {
        return blockX >> 4;
    }

    public static int getChunkXFromBlock(BlockPos position) {
        return position.getX() >> 4;
    }

    public static int getChunkZFromBlock(int blockZ) {
        return blockZ >> 4;
    }

    public static int getChunkZFromBlock(BlockPos position) {
        return position.getZ() >> 4;
    }

    /**
     * force a block update at the given position
     *
     * @param world    the world to update
     * @param position the position of the block begin updated
     * @param oldState the old state of the block begin updated. if null, the current state will be retrieved from the
     *                 world
     * @param newState the new state for the block begin updated. if null, the final value of oldState will be used
     */
    public static void notifyBlockUpdate(World world, BlockPos position, BlockState oldState, BlockState newState) {

        if (null == oldState)
            oldState = world.getBlockState(position);

        if (null == newState)
            newState = oldState;

        world.notifyBlockUpdate(position, oldState, newState, 3);
    }

    public static long getChunkXZHashFromBlock(int blockX, int blockZ) {
        return ChunkPos.asLong(getChunkXFromBlock(blockX), getChunkZFromBlock(blockZ));
    }

    public static long getChunkXZHashFromBlock(BlockPos position) {
        return ChunkPos.asLong(getChunkXFromBlock(position), getChunkZFromBlock(position));
    }
}
