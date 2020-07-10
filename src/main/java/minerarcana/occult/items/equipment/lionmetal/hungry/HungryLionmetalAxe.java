package minerarcana.occult.items.equipment.lionmetal.hungry;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;

import static minerarcana.occult.Occult.OG;

public class HungryLionmetalAxe extends AxeItem implements IHungryMetal{

    public HungryLionmetalAxe(IItemTier tier, float attackDamageIn, float attackSpeedIn) {
        super(tier, attackDamageIn, attackSpeedIn, new Item.Properties().group(OG));
    }


}
