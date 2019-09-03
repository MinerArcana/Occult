package com.minerarcana.occult.capabilities.nature;

public interface NaturePressure
{

    int insertNaturalPressure(int maxInsert, boolean simulate);
    int extractNaturalPressure(int maxExtract, boolean simulate);

    int getNaturalPressure();
    int getMaxNaturalPressure();


    boolean canExtractNaturalPressure();
    boolean canReceiveNaturalPressure();

}
