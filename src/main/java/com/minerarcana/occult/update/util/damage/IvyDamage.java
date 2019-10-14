package com.minerarcana.occult.update.util.damage;

import net.minecraft.util.DamageSource;

public class IvyDamage extends DamageSource {

    public static DamageSource PoisonIvy = (new DamageSource("posionivy")).setDamageBypassesArmor();


    public IvyDamage(String name) {
        super(name);
    }
}
