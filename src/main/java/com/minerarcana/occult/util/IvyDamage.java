package com.minerarcana.occult.util;

import net.minecraft.util.DamageSource;

public class IvyDamage extends DamageSource
{
    public static DamageSource IVYDAMAGE = (new IvyDamage("ivydamage"));

    public IvyDamage(String name) {
        super(name);
    }
}
