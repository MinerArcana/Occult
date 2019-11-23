package com.minerarcana.occult.items.liontools;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.minerarcana.occult.content.OccultItems.HUNGRY_LIONMETAL_SWORD;
import static com.minerarcana.occult.content.OccultItems.SATED_LIONMETAL_SWORD;

public class OccultSword extends SwordItem {

    public OccultSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(this.equals(HUNGRY_LIONMETAL_SWORD.get())) {
            tooltip.add(new TranslationTextComponent("Hungry", 2));
        } else if (this.equals(SATED_LIONMETAL_SWORD.get())) {
            tooltip.add(new TranslationTextComponent("Satiated", 2));
        }
    }

}
