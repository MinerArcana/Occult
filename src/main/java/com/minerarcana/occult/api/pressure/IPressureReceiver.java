package com.minerarcana.occult.api.pressure;

public interface IPressureReceiver
{

    public boolean isFull();

    public void receivePressure(int pressure);

    public boolean canReceivePressure();

    public int getCurrentPressure();

}
