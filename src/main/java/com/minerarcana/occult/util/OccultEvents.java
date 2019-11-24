package com.minerarcana.occult.util;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.AttackDamageEffect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.minerarcana.occult.Occult.LOGGER;
import static com.minerarcana.occult.Occult.MOD_ID;
import static com.minerarcana.occult.api.DamageSources.SATED_FIRE;
import static com.minerarcana.occult.content.OccultItems.SATED_LIONMETAL_HATCHET;
import static com.minerarcana.occult.content.OccultItems.SATED_LIONMETAL_SWORD;
import static net.minecraft.tags.ItemTags.LOGS;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OccultEvents {

    @SubscribeEvent
    public static void blockHarvest(BlockEvent.HarvestDropsEvent event)
    {
        if (event.getHarvester() != null && event.getHarvester().getHeldItemMainhand().getItem() == SATED_LIONMETAL_HATCHET.get())
        {
            LOGGER.info("it hears hatchet attacks!");
            for (int i = 0; i < event.getDrops().size(); i++)
            {
                ItemStack stack = event.getDrops().get(i);
                if (stack.getItem().isIn(LOGS))
                {
                    event.getDrops().set(i, new ItemStack(Items.CHARCOAL));
                }
            }
        }
    }

}
