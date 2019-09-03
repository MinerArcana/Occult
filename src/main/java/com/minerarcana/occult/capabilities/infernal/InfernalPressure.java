package com.minerarcana.occult.capabilities.infernal;

public interface InfernalPressure
{

    int insertInfernalPressure(int maxInsert, boolean simulate);
    int extractInfernalPressure(int maxExtract, boolean simulate);

    int getInfernalPressure();
    int getMaxInfernalPressure();

    boolean canExtractInfernalPressure();
    boolean canReceiveInfernalPressure();

}
