package minerarcana.occult.util;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import static minerarcana.occult.content.OccultItems.LIONMETAL_INGOT;

public class OccultGroup extends ItemGroup
{

    public OccultGroup(int index, String label)
    {
        super(index, label);
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(LIONMETAL_INGOT.get());
    }


}