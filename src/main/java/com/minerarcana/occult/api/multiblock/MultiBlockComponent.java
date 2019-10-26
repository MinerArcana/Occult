package com.minerarcana.occult.api.multiblock;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MultiBlockComponent {

    protected BlockPos relativePos;
    protected final BlockState state;
    protected final TileEntity tileEntity;

    public MultiBlockComponent(BlockPos relPos, BlockState state) {
        this(relPos, state, null);
    }

    public MultiBlockComponent(BlockPos relativePos, BlockState state, TileEntity tileEntity) {
        this.relativePos = relativePos;
        this.state = state;
        this.tileEntity = tileEntity;
    }

    public BlockPos getRelativePosition() {
        return relativePos;
    }

    public BlockState getBlockState() {
        return state;
    }

    public boolean matches(World world, BlockPos pos) {
        return world.getBlockState(pos) == state;
    }

    public ItemStack[] getMaterials() {
        return new ItemStack[] { new ItemStack(state.getBlock()) };
    }

    public void rotate(double angle) {
        double x = relativePos.getX();
        double z = relativePos.getZ();
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);

        double xn = x * cos - z * sin;
        double zn = x * sin + z * cos;
        relativePos = new BlockPos((int) Math.round(xn), relativePos.getY(), (int) Math.round(zn));
    }

    public MultiBlockComponent copy() {
        return new MultiBlockComponent(relativePos, state, tileEntity);
    }

    public TileEntity getTileEntity() {
        return tileEntity;
    }



}
