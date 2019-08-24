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


    public void strangleDeath(World worldIn, BlockState state, BlockPos pos, Random random) {
            BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
            if(worldIn.getBlockState(blockpos) == Blocks.AIR.getDefaultState() && worldIn.getBlockState(pos.down()) != OccultBlocks.rockytrails.getDefaultState()) {
                worldIn.setBlockState(blockpos, OccultBlocks.deepgrass.getDefaultState());
                Occult.LOGGER.info("GrassMurder");
            }
        }

        @SubscribeEvent
        public static void strangeDeaths(LivingDeathEvent event) {
            if (event.getSource() instanceof StrangeDamage)
            {
            Occult.LOGGER.info("OMGITWORKS");
            }
        }
    }

