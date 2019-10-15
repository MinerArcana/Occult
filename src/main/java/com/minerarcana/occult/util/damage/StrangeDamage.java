package com.minerarcana.occult.update.util.damage;

import net.minecraft.util.DamageSource;

public class StrangeDamage extends DamageSource {

    public StrangeDamage(String name) {
        super(name);
    }
    public static DamageSource STRANGEGRASS = (new StrangeDamage("stranglegrass"));

}
