package com.einstein_automotive.httpwp.telemetry.DataStorage;

/**
 * Created by Fabian Naaß on 15.08.2015,
 * for Einstein Motorsport 2015
 */
public class Data {

    private float value;
    private Unit unit;
    private ValueName valueName;

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getCritical() {
        return critical;
    }

    public void setCritical(float critical) {
        this.critical = critical;
    }

    private float min,max,critical;

    /**
     *
     * @param value
     * @param unit
     * @param critical
     * @param min
     * @param max
     */
    public Data(float value, Unit unit, float critical, float min, float max) {
        this.critical = critical;
        this.value = value;
        this.unit = unit;
        this.min = min;
        this.max = max;
    }

    public Data(float value, float min, float max) {
        this.value = value;
        this.min = min;
        this.max = max;
    }

    public Data(Unit unit) {
        this.unit = unit;
    }
    public Data(ValueName valueName, float value) {
        this.valueName = valueName;
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public String getStringValue(){
        if (unit.equals(Unit.Count) || unit.equals(Unit.Bit)|| unit.equals(Unit.RPM) || unit.equals(Unit.Speed) || unit.equals(Unit.Time) || unit.equals(Unit.Unknown))
            return String.valueOf((int)value);//String.format("%f",value);
        else if (unit.equals(Unit.Ampere) || unit.equals(Unit.Voltage) || unit.equals(Unit.Angle) || unit.equals(Unit.GForce) || unit.equals(Unit.Count))
            return String.format("%.2f",value);
        else
            return String.format("%.1f",value);
    }

    public void setValue(float value) {
        this.value = value;
    }

    public ValueName getValueName(){
        return valueName;
    }


    public Unit getUnit(){
        return unit;
    }
}
