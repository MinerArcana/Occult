package minerarcana.occult.blocks.metals;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import static minerarcana.occult.util.TagHelper.LIONMETALFOOD;

public class SatiatedLionMetalBlock extends Block {

    public SatiatedLionMetalBlock() {
        super(Properties.create(Material.ANVIL).lightValue(20).tickRandomly());
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isBlockPowered(pos)) {
            lionMetalFireSetting(world, pos);
        }
    }

    public void lionMetalFireSetting(World world, BlockPos pos) {
        for (int x = -3; x < 3; ++x) {
            for (int y = -3; y < 3; ++y) {
                for (int z = -3; z < 3; ++z) {
                    BlockPos targetPos = pos.add(x, y, z);
                    BlockState targetBlock = world.getBlockState(targetPos);
                    if (targetBlock.getMaterial().isFlammable() && world.getBlockState(targetPos.up()).getMaterial().isReplaceable()) {
                        world.setBlockState(targetPos.up(), Blocks.FIRE.getDefaultState());
                        return;
                    }
                }
            }
        }
    }


}
