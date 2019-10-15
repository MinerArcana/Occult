package com.minerarcana.occult.common.biomes.features.bushes;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

import static com.minerarcana.occult.update.util.lib.OccultNameLib.poisonivy;

public class IvyFeature extends Feature<NoFeatureConfig>
{
    public IvyFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> deserializer)
    {
        super(deserializer);
    }

    public BlockState chooseGrassState(Random rand)
    {
        return rand.nextInt(3) == 0 ? poisonivy.getDefaultState() : poisonivy.getDefaultState();
    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        BlockState BlockState = this.chooseGrassState(rand);

        for (BlockState BlockState1 = world.getBlockState(pos); (BlockState1.isAir(world, pos) || BlockState1.isIn(BlockTags.LEAVES)) && pos.getY() > 0; BlockState1 = world.getBlockState(pos))
        {
            pos = pos.down();
        }

        int i = 0;

        for (int j = 0; j < 128; ++j)
        {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (world.isAirBlock(blockpos) && BlockState.isValidPosition(world, blockpos))
            {
                world.setBlockState(blockpos, BlockState, 2);
                ++i;
            }
        }

        return i > 0;
    }
}
