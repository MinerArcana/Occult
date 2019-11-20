package com.minerarcana.occult.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SatedLionMetalIngot extends Item {
    public SatedLionMetalIngot(Properties properties) {
        super(properties);
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return 1000;
    }

}
