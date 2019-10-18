package com.minerarcana.occult.world.feature;

import com.minerarcana.occult.util.lib.OccultHolderLib;
import com.minerarcana.occult.util.lib.OccultTagLib;
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

import static com.minerarcana.occult.util.lib.OccultNameLib.*;

public class NetherBushFeature extends Feature<NoFeatureConfig>
{
    public NetherBushFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> deserializer)
    {
        super(deserializer);

    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {

        for (BlockState state1 = world.getBlockState(pos); (state1.isAir(world, pos) || state1.isIn(BlockTags.LEAVES)) && pos.getY() > 0; state1 = world.getBlockState(pos))
        {
            pos = pos.down();
        }

        int i = 0;

        for (int j = 0; j < 128; ++j)
        {
            OccultHolderLib.NETHERBUSH = bushSelector();
            BlockState bush = OccultHolderLib.NETHERBUSH;
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (world.isAirBlock(blockpos) && world.getBlockState(blockpos.down()).isIn(OccultTagLib.Blocks.VALIDNETHERGROUND))
            {
                world.setBlockState(blockpos, bush, 2);
                ++i;
            }
        }

        return i > 0;
    }

    public BlockState bushSelector(){
        if(this == netherstranglegrassfeature){
            OccultHolderLib.NETHERBUSH = netherstranglegrass.getDefaultState();
        }
        return OccultHolderLib.NETHERBUSH;
    }


}
