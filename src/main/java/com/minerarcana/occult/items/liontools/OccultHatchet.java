package com.minerarcana.occult.items.liontools;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.minerarcana.occult.content.OccultItems.HUNGRY_LIONMETAL_HATCHET;
import static com.minerarcana.occult.content.OccultItems.SATED_LIONMETAL_HATCHET;

public class OccultHatchet extends AxeItem {

    public OccultHatchet(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {

    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(this.equals(HUNGRY_LIONMETAL_HATCHET.get())) {
            tooltip.add(new TranslationTextComponent("Hungry", 2));
        } else if (this.equals(SATED_LIONMETAL_HATCHET.get())) {
            tooltip.add(new TranslationTextComponent("Satiated", 2));
        }
    }
}
