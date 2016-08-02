package com.einstein_automotive.httpwp.telemetry.customViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by Higgy on 25.07.2015.
 */
public class SpeedView extends View{

    int speed =0;
    String sSpeed;
    int radiusCircle =0;
    int speedNeedleLength;
    int startAngle = 130;
    Canvas canvas;
    int xOrigin,yOrigin;
    Paint linePaintRed,linePaintBlack;
    Paint needlePaint;
    Paint ovalPaint;
    Paint outerOvalPaint;
    Paint textPaint = new Paint();
    String textMain = "km/h";

    public SpeedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        linePaintRed = new Paint();
        linePaintBlack = new Paint();
        needlePaint = new Paint();
        ovalPaint = new Paint();
        outerOvalPaint = new Paint();
        textPaint = new Paint();

        linePaintBlack.setStyle((Paint.Style.FILL));
        linePaintBlack.setAntiAlias(true);
        linePaintBlack.setStrokeWidth(2.0f);
        linePaintRed.setStyle((Paint.Style.FILL));
        linePaintRed.setAntiAlias(true);
        linePaintRed.setColor(Color.RED);
        linePaintRed.setStrokeWidth(3.5f);
        outerOvalPaint.setStyle(Paint.Style.STROKE);
        outerOvalPaint.setAntiAlias(true);
        ovalPaint.setAntiAlias(true);
        ovalPaint.setColor(Color.RED);
        ovalPaint.setStyle(Paint.Style.FILL);
        needlePaint.setColor(Color.RED);
        needlePaint.setStrokeWidth(6);
    }


    //Konstruktor mit Werten für das erstellen der oMeters(Speed oder Fuel oder RPM...)

    @Override
    protected void onDraw(Canvas canvas) {
        // Canvas merken
        this.canvas = canvas;
        super.onDraw(canvas);

        xOrigin = getWidth() / 2;
        yOrigin = getHeight() / 2;

        calcMaxRadius();

        // Kreis zeichnen, in der Mitte mit einem Radius von 98% von der kleinsten Länge (Höhe oder Breite)
        canvas.drawCircle(xOrigin, yOrigin, radiusCircle, outerOvalPaint);

        // Kleinen Punkt in der Mitte für Nadel 6% von der kleinsten Länge (Höhe oder Breite)
        canvas.drawCircle(xOrigin, yOrigin, radiusCircle * 0.06f, ovalPaint);

        // Text in der Mitte nach oben versetzt zeichnen Bezeichnung der Analoganzeige, in der Größe von 8% von der kleinsten Länge (Höhe oder Breite)
        textPaint.setTextSize(radiusCircle*0.08f);
        float textSize = textPaint.measureText(textMain);
        canvas.drawText(textMain,xOrigin-textSize/2,yOrigin - radiusCircle*0.4f,textPaint);

        // Punkte des Kreises Bestimmen für Wert Anzeige und Stummellinien
        // https://en.wikipedia.org/wiki/Circle#Equations
//        x = cx + r * cos(a)
//        y = cy + r * sin(a)
//        Where r is the radius, cx,cy the origin, and a the angle from 0..2PI radians or 0..360 degrees.
        for (int i =0; i<=280; i++){

            // Große Markierung und Speed Werte anzeigen
            if(i%20 == 0){

                // Zeichne Werte an passender Stelle mit 8% von der kleinsten Länge (Höhe oder Breite)
                float xValues = (float)(xOrigin  + (radiusCircle - radiusCircle*0.41f) * Math.cos((i+startAngle) * Math.PI / 180));
                float yValues = (float)(yOrigin  + (radiusCircle - radiusCircle*0.41f) * Math.sin((i+startAngle) * Math.PI / 180));

                textPaint.setTextSize(radiusCircle*0.08f);
                String text = String.valueOf(i/2);
                float textSizeMain = textPaint.measureText(text);

                canvas.drawText(text, xValues-textSizeMain/2, yValues+textSizeMain/4,textPaint);

                //Zeichne kleine Linien
                float xLinesOuter = (float)(xOrigin  + (radiusCircle) * Math.cos((i+startAngle) * Math.PI / 180));
                float yLinesOuter = (float)(yOrigin  + (radiusCircle) * Math.sin((i+startAngle) * Math.PI / 180));

                float xLinesInner = (float)(xOrigin  + (radiusCircle - radiusCircle*0.3f) * Math.cos((i+startAngle) * Math.PI / 180));
                float yLinesInner = (float)(yOrigin  + (radiusCircle - radiusCircle*0.3f) * Math.sin((i + startAngle) * Math.PI / 180));

                canvas.drawLine(xLinesOuter, yLinesOuter, xLinesInner, yLinesInner, linePaintRed);

            }
            else{
                if(i%4 == 0) {
                    //Zeichne große Linien
                    float xLinesOuter = (float) (xOrigin + (radiusCircle) * Math.cos((i + startAngle) * Math.PI / 180));
                    float yLinesOuter = (float) (yOrigin + (radiusCircle) * Math.sin((i + startAngle) * Math.PI / 180));

                    float xLinesInner = (float) (xOrigin + (radiusCircle - radiusCircle * 0.2f) * Math.cos((i + startAngle) * Math.PI / 180));
                    float yLinesInner = (float) (yOrigin + (radiusCircle - radiusCircle * 0.2f) * Math.sin((i + startAngle) * Math.PI / 180));

                    canvas.drawLine(xLinesOuter, yLinesOuter, xLinesInner, yLinesInner, linePaintBlack);
                }
            }
        }
        update(speed);
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public void update(int speed){
        float x = (float)(xOrigin  + speedNeedleLength * Math.cos((speed * 2 + startAngle) * Math.PI / 180));
        float y = (float)(yOrigin  + speedNeedleLength * Math.sin((speed*2 + startAngle) * Math.PI / 180));

        sSpeed = String.valueOf(speed);

        textPaint.setTextSize(radiusCircle * 0.3f);
        float textSize = textPaint.measureText(sSpeed);
        canvas.drawText(sSpeed, xOrigin - textSize / 2, yOrigin + radiusCircle * 0.8f,textPaint);

        canvas.drawLine(xOrigin, yOrigin, x, y, needlePaint);
    }

    // Maximale Nadel länge 70%
    private void calcSpeedNeedleLenght(){
        speedNeedleLength = (int)(radiusCircle*0.65f);
    }

    // Berechnet den besten Radius aus der Größe des "fensters"
    private void calcMaxRadius(){
        if (xOrigin < yOrigin)
            radiusCircle = (int)(xOrigin*0.98);
        else
            radiusCircle = (int)(yOrigin*0.98);

        calcSpeedNeedleLenght();
    }
}
