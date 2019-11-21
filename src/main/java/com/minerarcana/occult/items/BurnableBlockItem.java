package com.minerarcana.occult.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class BurnableBlockItem extends BlockItem {

    public BurnableBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        int burnTime = 0;
        if(this.toString().equals("sulphur_block")) {
            burnTime = 900;
        }
        return burnTime;
    }

}
