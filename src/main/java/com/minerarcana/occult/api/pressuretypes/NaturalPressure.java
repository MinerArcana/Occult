package com.minerarcana.occult.api.pressuretypes;

import com.minerarcana.occult.api.Pressure;
import com.minerarcana.occult.api.PressureType;

public class NaturalPressure extends PressureType {

    public NaturalPressure(Pressure pressure) {
        super(pressure, 16384);
    }

}
