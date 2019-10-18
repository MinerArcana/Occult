package com.minerarcana.occult.util;

import net.minecraft.util.DamageSource;

public class StrangeDamage extends DamageSource
{
    public static DamageSource STRANGEDAMAGE = (new StrangeDamage("strangledamage"));

    public StrangeDamage(String name) {
        super(name);
    }
}
