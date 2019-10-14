package com.minerarcana.occult.update.util.itemgroup;

import com.minerarcana.occult.common.items.OccultItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import static com.minerarcana.occult.update.util.lib.OccultLib.occult_icon;

public class OccultGroup extends ItemGroup
{

    public static final OccultGroup instance = new OccultGroup(ItemGroup.GROUPS.length, "occult");

    private OccultGroup(int index, String label)
    {
        super(index, label);
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(occult_icon);
    }


}
