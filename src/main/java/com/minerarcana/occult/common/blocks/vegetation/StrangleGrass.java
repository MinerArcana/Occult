package com.minerarcana.occult.common.blocks.vegetation;


import com.minerarcana.occult.update.util.damage.StrangeDamage;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

import static com.minerarcana.occult.update.util.damage.StrangeDamage.STRANGLEGRASS;
import static com.minerarcana.occult.update.util.lib.OccultLib.*;

@Mod.EventBusSubscriber
public class StrangleGrass extends BushBlock
{

    public StrangleGrass(Properties properties) {
        super(properties);

    }

    @Override
    @Deprecated
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entity) {
        entity.attackEntityFrom(STRANGLEGRASS, 1.0F);
    }


    public static void strangleDeath(BlockPos pos, World worldIn, Random random) {

            BlockPos blockpos = pos.add(random.nextInt(10) - 1, random.nextInt(10) - 3, random.nextInt(10) - 1);
            if(worldIn.getBlockState(blockpos) == Blocks.AIR.getDefaultState() && worldIn.getBlockState(blockpos.down()) != rockytrails.getDefaultState() && worldIn.getBlockState(blockpos.down()) != Blocks.AIR.getDefaultState()) {
                worldIn.setBlockState(blockpos, stranglegrass.getDefaultState());
            }
        }

        @SubscribeEvent
        public static void strangeDeaths(LivingDeathEvent event) {
            if (event.getSource() instanceof StrangeDamage)
            {
               Entity entity = event.getEntity();
               BlockPos entitypos = entity.getPosition();
               World entityworld = entity.world;
               Random random = entityworld.rand;
               strangleDeath(entitypos, entityworld, random);

               }

            }



}

