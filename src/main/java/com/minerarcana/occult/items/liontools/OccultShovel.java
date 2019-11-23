package com.minerarcana.occult.items.liontools;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.minerarcana.occult.content.OccultItems.HUNGRY_LIONMETAL_SHOVEL;
import static com.minerarcana.occult.content.OccultItems.SATED_LIONMETAL_SHOVEL;

public class OccultShovel extends ShovelItem {

    public OccultShovel(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(this.equals(HUNGRY_LIONMETAL_SHOVEL.get())) {
            tooltip.add(new TranslationTextComponent("Hungry", 2));
        } else if (this.equals(SATED_LIONMETAL_SHOVEL.get())) {
            tooltip.add(new TranslationTextComponent("Satiated", 2));
        }
    }
}
