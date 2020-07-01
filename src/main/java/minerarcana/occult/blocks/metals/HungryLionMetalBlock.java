package minerarcana.occult.blocks.metals;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import static minerarcana.occult.content.OccultBlocks.SATIATED_LIONMETAL_BLOCK;
import static minerarcana.occult.util.TagHelper.LIONMETALFOOD;

public class HungryLionMetalBlock extends Block {

    private int fireConsumed = 0;

    public HungryLionMetalBlock() {
        super(Properties.create(Material.ANVIL).tickRandomly());
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        lionMetalFireManager(world, pos);
    }

    private boolean isHungry() {
        return fireConsumed < 20;
    }

    public void lionMetalFireManager(World world, BlockPos pos) {
        if (!this.isHungry()) {
            world.setBlockState(pos, SATIATED_LIONMETAL_BLOCK.get().getDefaultState());
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
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

}
