package com.minerarcana.occult.util;

import com.minerarcana.occult.items.OccultItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class OccultItemGroup extends ItemGroup
{

    public static final OccultItemGroup instance = new OccultItemGroup(ItemGroup.GROUPS.length, "occult");

    private OccultItemGroup(int index, String label)
    {
        super(index, label);
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(OccultItems.occult_icon);
    }


}
