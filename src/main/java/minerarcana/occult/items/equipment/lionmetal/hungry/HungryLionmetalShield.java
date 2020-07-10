package minerarcana.occult.items.equipment.lionmetal.hungry;

import net.minecraft.item.ShieldItem;

import static minerarcana.occult.Occult.OG;

public class HungryLionmetalShield extends ShieldItem implements IHungryMetal{

    public HungryLionmetalShield() {
        super(new Properties().group(OG));
    }
}
