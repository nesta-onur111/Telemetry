package com.einstein_automotive.httpwp.telemetry.DataStorage;

import android.graphics.Paint;

import java.util.HashMap;

/**
 * Created by Fabian Naaß on 15.11.2015.
 * Einstein Motorsport 2015.
 * + Copyrights by Einstein Motorsport e.V
 */
public class LineChartConfig {

    public LineChartConfig(){
        hashMap = new HashMap<>();
    }

    public void setXValue(int xValue, boolean timeSelected){
        this.timeSelected = timeSelected;
        if(timeSelected){
            time_ms = xValue;
        }else{
            distance_m = xValue;
        }
    }

    private HashMap<ValueName, Paint> hashMap;

    private int time_ms, distance_m;
    private boolean timeSelected;

    public int getTime() {
        return time_ms;
    }

    public void add(Paint linePaint, ValueName name) {
        hashMap.put(name,linePaint);
    }

    public HashMap<ValueName, Paint> getLineConfig(){
        return hashMap;
    }

    public int getSize(){
        return hashMap.size();
    }

public boolean isTimeSelected(){
    return timeSelected;
}

    public int getDistance() {
        return distance_m;
    }


}
