package com.minerarcana.occult;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import static com.minerarcana.occult.content.OccultItems.SATED_LIONMETAL_INGOT;

public class OccultGroup extends ItemGroup
{

    public static final OccultGroup OCCULT_GROUP = new OccultGroup(ItemGroup.GROUPS.length, "occult");

    private OccultGroup(int index, String label)
    {
        super(index, label);
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(SATED_LIONMETAL_INGOT.get());
    }


}
