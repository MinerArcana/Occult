package com.minerarcana.occult.blocks.lionmetal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import static com.minerarcana.occult.util.lib.OccultTagLib.Blocks.LIONMETALFOOD;

public class SatedLionMetalBlock extends Block {

    private BlockPos firePos;

    public SatedLionMetalBlock(Properties properties) {
        super(properties.lightValue(20));
    }

    @Override
    public void tick(BlockState state, World world, BlockPos pos, Random random) {
        if(!world.isBlockPowered(pos)){
            for (Direction direction : Direction.values()) {
                lionMetalFireManager(world, pos, direction);
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("Satiated", 2));
        tooltip.add(new TranslationTextComponent("Satiated", 2));
    }

    public void lionMetalFireManager(World world, BlockPos pos, Direction direction) {
        for (int x = -3; x < 3; ++x) {
            for (int y = -3; y < 3; ++y) {
                for (int z = -3; z < 3; ++z) {
                    BlockPos targetPos = pos.add(x, y, z);
                    BlockState targetBlock = world.getBlockState(targetPos);
                    BlockPos firePos = firePos(world, targetPos);
                    if (targetBlock.isFlammable(world, targetPos, direction) && firePos != null) {
                        world.setBlockState(firePos, Blocks.FIRE.getDefaultState());
                    }
                }
            }
        }
    }

    private BlockPos firePos(World world, BlockPos pos) {
        BlockPos up = pos.up();
        BlockPos down = pos.down();
        BlockPos east = pos.east();
        BlockPos west = pos.west();
        BlockPos north = pos.north();
        BlockPos south = pos.south();
        if (world.getBlockState(down).isAir()) {
            firePos = down;
        } else if (world.getBlockState(up).isAir()) {
            firePos = up;
        } else if (world.getBlockState(north).isAir()) {
            firePos = north;
        } else if (world.getBlockState(south).isAir()) {
            firePos = south;
        } else if (world.getBlockState(east).isAir()) {
            firePos = east;
        } else if (world.getBlockState(west).isAir()) {
            firePos = west;
        }
        return firePos;
    }

}
