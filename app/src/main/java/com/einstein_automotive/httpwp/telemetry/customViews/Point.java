package com.einstein_automotive.httpwp.telemetry.customViews;

/**
 * Created by Fabian Naaß on 03.08.2015,
 * for Einstein Mitorsport 2015
 */
public class Point{

    private float x,y, value =0.0f;

    public Point(float x, float y){
        this.x = x;
        this.y = y;
        calcValue();
    }

    private void calcValue(){
        if (Math.abs(x)>Math.abs(y))
            value = Math.abs(x);
        else
            value = Math.abs(y);
    }

    public float getValue() {
        return value;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
