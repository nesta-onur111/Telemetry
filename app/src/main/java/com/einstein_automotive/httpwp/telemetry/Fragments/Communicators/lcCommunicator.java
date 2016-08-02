package com.einstein_automotive.httpwp.telemetry.Fragments.Communicators;

import com.einstein_automotive.httpwp.telemetry.DataStorage.Data;

/**
 * Created by Fabian Naaß on 15.11.2015.
 * Einstein Motorsport 2015.
 * + Copyrights by Einstein Motorsport e.V
 */
public interface lcCommunicator {
    public void setConfig();
    public void receiveData();
    public Data sendData();
}
