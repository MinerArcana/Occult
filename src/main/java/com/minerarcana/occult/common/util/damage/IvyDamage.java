package com.minerarcana.occult.util.damage;

import net.minecraft.util.DamageSource;

public class IvyDamage extends DamageSource {

    public static DamageSource PoisonIvy = (new IvyDamage("posionivy")).setDamageBypassesArmor();


    public IvyDamage(String name) {
        super(name);
    }
}
