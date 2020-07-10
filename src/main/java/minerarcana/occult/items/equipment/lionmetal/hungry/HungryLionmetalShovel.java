package minerarcana.occult.items.equipment.lionmetal.hungry;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ShovelItem;

import static minerarcana.occult.Occult.OG;

public class HungryLionmetalShovel extends ShovelItem implements IHungryMetal{

    public HungryLionmetalShovel(IItemTier tier, float attackDamageIn, float attackSpeedIn) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(OG));
    }


}
