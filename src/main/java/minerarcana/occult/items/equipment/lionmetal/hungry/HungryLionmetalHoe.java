package minerarcana.occult.items.equipment.lionmetal.hungry;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;

import static minerarcana.occult.Occult.OG;

public class HungryLionmetalHoe extends AxeItem implements IHungryMetal{

    public HungryLionmetalHoe(IItemTier tier, float attackDamageIn, float attackSpeedIn) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(OG));
    }


}
