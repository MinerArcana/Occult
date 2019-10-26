package com.minerarcana.occult.blocks.vegetation;



import com.minerarcana.occult.Occult;
import com.minerarcana.occult.util.StrangeDamage;
import com.minerarcana.occult.util.lib.OccultTagLib;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

import static com.minerarcana.occult.Occult.MOD_ID;
import static com.minerarcana.occult.util.StrangeDamage.STRANGEDAMAGE;
import static com.minerarcana.occult.util.lib.OccultNameLib.netherstranglegrass;
import static com.minerarcana.occult.util.lib.OccultNameLib.stranglegrass;
import static com.minerarcana.occult.util.lib.OccultTagLib.Blocks.*;


public class StrangleGrass extends BushBlock
{

    public StrangleGrass(Properties properties) {
        super(properties);

    }

    @Override
    @Deprecated
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.attackEntityFrom(STRANGEDAMAGE, 1.0F);
    }



    public static void strangleSpread(World world, BlockPos pos, Random random){
        BlockPos blockpos = pos.add(random.nextInt(10) - 1, random.nextInt(10) - 3, random.nextInt(10) - 1);
        BlockPos downpos = blockpos.down();
        BlockState downblock = world.getBlockState(downpos);
        if(world.getBlockState(blockpos).isAir()) {
            if(!downblock.isIn(BLACKLISTGROUND)) {
                if (downblock.isIn(VALIDNETHERGROUND)) {
                    world.setBlockState(blockpos, netherstranglegrass.getDefaultState());
                } else if (downblock.isIn(VALIDGROUND)) {
                    world.setBlockState(blockpos, stranglegrass.getDefaultState());
                }
            }
        }
    }


}

