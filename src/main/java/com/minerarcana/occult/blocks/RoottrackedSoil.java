package com.minerarcana.occult.blocks;

import com.minerarcana.occult.blocks.DeepGrass;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

import static com.minerarcana.occult.util.lib.OccultNameLib.rootedsoil;

public class RoottrackedSoil extends DeepGrass {

    public RoottrackedSoil(Properties properties) {
        super(properties);
    }

    @Deprecated
    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        if (!worldIn.isRemote) {
            if (!worldIn.isAreaLoaded(pos, 3))
            if (!getLightLevel(state, worldIn, pos)) {
                worldIn.setBlockState(pos, rootedsoil.getDefaultState());
            } else {
                if (worldIn.getBlockState(pos.up()) == Blocks.AIR.getDefaultState()) {
                    for (int i = 0; i < 4; ++i) {
                        BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                        if (worldIn.getBlockState(blockpos).getBlock() == Blocks.DIRT || worldIn.getBlockState(blockpos).getBlock() == Blocks.GRASS_BLOCK) {
                            worldIn.setBlockState(blockpos, rootedsoil.getDefaultState());

                        }
                    }
                }

            }
        }

 }
}
