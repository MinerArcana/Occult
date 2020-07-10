package minerarcana.occult.util;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static minerarcana.occult.util.TagHelper.LIONMETALFOOD;

public class StaticHandler {

    public static void hungryLionmetalEaterHelper(World world, PlayerEntity player, int distance) {
        BlockPos pos = player.getPosition();
        for (int x = -distance; x < distance; ++x) {
            for (int y = -distance; y < distance; ++y) {
                for (int z = -distance; z < distance; ++z) {
                    BlockPos targetPos = pos.add(x, y, z);
                    Block targetblock = world.getBlockState(targetPos).getBlock();
                    if (targetblock.isIn(LIONMETALFOOD)) {
                        world.setBlockState(targetPos, Blocks.AIR.getDefaultState());
                        return;
                    }
                }
            }
        }
    }


}
