package com.minerarcana.occult.util.damage;

import net.minecraft.util.DamageSource;

public class Poison extends DamageSource
{


        public Poison(String name) {
            super(name);
        }
        public static DamageSource POISONIVY = (new Poison("poisonivy"));



}
