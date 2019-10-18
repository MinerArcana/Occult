package com.minerarcana.occult.blocks.vegetation;


import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.minerarcana.occult.util.lib.OccultPropertyLib.IVYDAMAGE;

@Mod.EventBusSubscriber
public class PoisonIvy extends BushBlock {


    public PoisonIvy(Properties properties) {
        super(properties);
    }


    @Override
    @Deprecated
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entity) {
        entity.attackEntityFrom(IVYDAMAGE, 1.0F);

    }

    @SubscribeEvent
    public static void PoisonTime(LivingHurtEvent event) {
        if (event.getSource().getDamageType().equals(IVYDAMAGE))
        {
            if(event.getEntity() instanceof PlayerEntity){
                PlayerEntity player = (PlayerEntity) event.getEntity();
                player.addPotionEffect(new EffectInstance(Effects.POISON,800,10,true,true));
            }
        }
    }

}
