package minerarcana.occult.items.equipment.lionmetal.hungry;

import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

import static minerarcana.occult.Occult.OG;

public class HungryLionmetalSword extends SwordItem implements IHungryMetal{

    public HungryLionmetalSword(IItemTier tier, float attackDamageIn, float attackSpeedIn) {
        super(tier, (int) attackDamageIn, attackSpeedIn, new Properties().group(OG));
    }


}
