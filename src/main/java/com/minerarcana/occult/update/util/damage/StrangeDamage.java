package com.minerarcana.occult.update.util.damage;

import net.minecraft.util.DamageSource;

public class StrangeDamage extends DamageSource {

    public static DamageSource STRANGLEGRASS = (new StrangeDamage("stranglegrass"));


    public StrangeDamage(String name)
    {
        super(name);

    }

}
