package com.minerarcana.occult.blocks.lionmetal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import static com.minerarcana.occult.content.OccultBlocks.SATED_LIONMETAL_BLOCK;
import static com.minerarcana.occult.util.lib.OccultTagLib.Blocks.LIONMETALFOOD;

public class HungyLionMetalBlock extends Block {

    private int fireConsumed = 0;

    public HungyLionMetalBlock(Properties properties) {
        super(properties.lightValue(0).tickRandomly());
    }

    @Override
    public void tick(BlockState state, World world, BlockPos pos, Random random) {

        lionMetalFireManager(world, pos);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("Hungry", 2));
        tooltip.add(new TranslationTextComponent("Must Consume 20 Fire Equivalent Blocks in 1 Sitting to Become Satiated", 2));
        tooltip.add(new TranslationTextComponent("RedStone Signal Required", 2));
    }

    private boolean isHungry() {
        return fireConsumed < 20;
    }

    public void lionMetalFireManager(World world, BlockPos pos) {
        if (!this.isHungry()) {
            world.setBlockState(pos, SATED_LIONMETAL_BLOCK.get().getDefaultState());
        } else {
            if(world.isBlockPowered(pos)) {
                for (int x = -3; x < 3; ++x) {
                    for (int y = -3; y < 3; ++y) {
                        for (int z = -3; z < 3; ++z) {
                            BlockPos targetPos = pos.add(x, y, z);
                            BlockState targetBlock = world.getBlockState(targetPos);
                            if (targetBlock.isIn(LIONMETALFOOD)) {
                                world.setBlockState(targetPos, Blocks.AIR.getDefaultState());
                                fireConsumed++;
                            }
                        }
                    }
                }
            }
        }
    }

}
