package com.einstein_automotive.httpwp.telemetry.DataStorage;

/**
 * Created by Fabian Naaß on 15.08.2015,
 * for Einstein Motorsport 2015
 */
public enum Unit {
    Voltage("[V]"),
    Ampere("[A]"),
    Count("[n]"),
    Percentage("[%]"),
    Angle("[°]"),
    Temperature("[°C]"),
    Pressure("[bar]"),
    Speed("[km/h]"),
    Distance("[m]"),
    RPM("[rpm]"),
    Power("[W]"),
    Time("[s]"),
    GForce("[g]"),
    Bit(""),
    Unknown("[?]");

    private String stringUnit;

    Unit(final String stringUnit) {
        this.stringUnit = stringUnit;
    }

    public String getName() {
        return stringUnit;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}

