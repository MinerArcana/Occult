package com.minerarcana.occult.api;

import net.minecraftforge.registries.ForgeRegistryEntry;



public class PressureType extends ForgeRegistryEntry<PressureType> {

    private final int colour;

    public PressureType(int colour) {
        this.colour = colour;
    }

    public int getPressureColour() {
        return colour;
    }

}