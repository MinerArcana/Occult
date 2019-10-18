package com.minerarcana.occult.blocks.vegetation;



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
import static com.minerarcana.occult.util.lib.OccultNameLib.*;
import static com.minerarcana.occult.util.lib.OccultPropertyLib.STRANGEDAMAGE;
import static com.minerarcana.occult.util.lib.OccultTagLib.Blocks.BLACKLISTGROUND;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
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

    @SubscribeEvent
    public void strangeDeaths(LivingDeathEvent event) {
        if (event.getSource().getDamageType().equals(STRANGEDAMAGE)) {
            Entity entity = event.getEntity();
            BlockPos pos = entity.getPosition();
            World world = entity.world;
            Random random = world.rand;
            BlockPos blockpos = pos.add(random.nextInt(10) - 1, random.nextInt(10) - 3, random.nextInt(10) - 1);
            BlockPos downpos = blockpos.down();
            BlockState downblock = world.getBlockState(downpos);
            if(world.getBlockState(blockpos).isAir(world,blockpos)) {
               if(!downblock.isIn(BLACKLISTGROUND)) {
                   if (downblock.isIn(OccultTagLib.Blocks.VALIDNETHERGROUND) && this.isIn(OccultTagLib.Blocks.NETHER)) {
                       world.setBlockState(blockpos, this.getDefaultState());
                   } else if (downblock.isIn(OccultTagLib.Blocks.VALIDGROUND) && this.isIn(OccultTagLib.Blocks.OVERWORLD)) {
                       world.setBlockState(blockpos, this.getDefaultState());
                   }
               }
            }
        }
    }

}

