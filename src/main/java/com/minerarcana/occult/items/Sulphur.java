package com.minerarcana.occult.items;

import net.minecraft.item.DyeColor;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;

public class Sulphur extends DyeItem {

    public Sulphur(DyeColor dyeColorIn, Properties builder) {
        super(dyeColorIn, builder);
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return 100;
    }
}
