package com.minerarcana.occult.capabilities.spirit;

public interface SpiritPressure {

    int insertSpiritualPressure(int maxInsert, boolean simulate);
    int extractSpiritualPressure(int maxExtract, boolean simulate);

    int getSpiritualPressure();
    int getMaxSpiritualPressure();

    boolean canExtractSpiritualPressure();
    boolean canReceiveSpiritualPressure();
}
