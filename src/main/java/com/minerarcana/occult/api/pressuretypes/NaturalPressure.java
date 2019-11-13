package com.minerarcana.occult.api.pressuretypes;

import com.minerarcana.occult.api.PressureType;

public class NaturalPressure extends PressureType {

    public NaturalPressure(PressureType pressure) {
        super(pressure, 16384);
    }

}
