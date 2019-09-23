package com.minerarcana.occult.blocks.vegetation;

import com.minerarcana.occult.Occult;
import com.minerarcana.occult.blocks.OccultBlocks;
import com.minerarcana.occult.util.damage.StrangeDamage;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.Random;
@Mod.EventBusSubscriber
public class StrangleGrass extends BushBlock
{

    public StrangleGrass(Properties properties) {
        super(properties);

    }

    @Override
    @Deprecated
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entity) {
        entity.attackEntityFrom(StrangeDamage.STRANGLEGRASS, 1.0F);
    }


    public static void strangleDeath(BlockPos pos, World worldIn, Random random) {

            BlockPos blockpos = pos.add(random.nextInt(10) - 1, random.nextInt(10) - 3, random.nextInt(10) - 1);
            if(worldIn.getBlockState(blockpos) == Blocks.AIR.getDefaultState() && worldIn.getBlockState(blockpos.down()) != OccultBlocks.rockytrails.getDefaultState() && worldIn.getBlockState(blockpos.down()) != Blocks.AIR.getDefaultState()) {
                worldIn.setBlockState(blockpos, OccultBlocks.stranglegrass.getDefaultState());
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

