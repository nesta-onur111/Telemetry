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
public class RevolutionsView extends View {

    int rpm = 0;
    int radiusCircle = 0;
    int speedNeedleLength;
    int startAngle = 130;
    Canvas canvas;
    int xOrigin, yOrigin;
    Paint linePaint, ovalPaint, textPaint, outerOvalPaint;

    public RevolutionsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        linePaint = new Paint();
        linePaint.setStyle((Paint.Style.FILL));
        linePaint.setAntiAlias(true);
        ovalPaint = new Paint();
        ovalPaint.setStyle((Paint.Style.STROKE));
        ovalPaint.setAntiAlias(true);
        textPaint = new Paint();
        outerOvalPaint = new Paint();
        outerOvalPaint.setStyle(Paint.Style.STROKE);
        outerOvalPaint.setAntiAlias(true);
    }

    //Konstruktor mit Werten für das erstellen der oMeters(Speed oder Fuel oder RPM...)

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        super.onDraw(canvas);

        xOrigin = getWidth() / 2;
        yOrigin = getHeight() / 2;
        calcMaxRadius();
        // Kreis zeichnen, in der mitte Mit einem Radius von 180
        canvas.drawCircle(xOrigin, yOrigin, radiusCircle, outerOvalPaint);
        ovalPaint.setColor(Color.RED);
        ovalPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(xOrigin, yOrigin, radiusCircle * 0.06f, ovalPaint);

        String textMain = "x1000/min";
        float textSize = textPaint.measureText(textMain);
        canvas.drawText(textMain, xOrigin - textSize / 2, yOrigin - radiusCircle * 0.4f, textPaint);

        // Punkte des Kreises Bestimmen für Wert Anzeige und Stummellinien
        // https://en.wikipedia.org/wiki/Circle#Equations
//        x = cx + r * cos(a)
//        y = cy + r * sin(a)
//        Where r is the radius, cx,cy the origin, and a the angle from 0..2PI radians or 0..360 degrees.
        for (int i = 0; i <= 280; i++) {

            // Große Markierung und Speed Werte anzeigen
            if (i % 20 == 0) {

                // DRAW TEXT
                float xValues = (float) (xOrigin + (radiusCircle - radiusCircle * 0.41f) * Math.cos((i + startAngle) * Math.PI / 180));
                float yValues = (float) (yOrigin + (radiusCircle - radiusCircle * 0.41f) * Math.sin((i + startAngle) * Math.PI / 180));

                textPaint.setTextSize(radiusCircle * 0.08f);
                String text = String.valueOf(i / 20);
                float textSizeMain = textPaint.measureText(text);

                //canvas.drawText(text, xValues - XValueOffset, yValues + YValueOffset,textPaint);
                canvas.drawText(text, xValues - textSizeMain / 2, yValues + textSizeMain / 4, textPaint);

                //DRAW LINES
                float xLinesOuter = (float) (xOrigin + (radiusCircle) * Math.cos((i + startAngle) * Math.PI / 180));
                float yLinesOuter = (float) (yOrigin + (radiusCircle) * Math.sin((i + startAngle) * Math.PI / 180));

                float xLinesInner = (float) (xOrigin + (radiusCircle - radiusCircle * 0.3f) * Math.cos((i + startAngle) * Math.PI / 180));
                float yLinesInner = (float) (yOrigin + (radiusCircle - radiusCircle * 0.3f) * Math.sin((i + startAngle) * Math.PI / 180));
                linePaint.setColor(Color.RED);
                linePaint.setStrokeWidth(3.5f);
                canvas.drawLine(xLinesOuter, yLinesOuter, xLinesInner, yLinesInner, linePaint);
                linePaint.setStrokeWidth(1.0f);
                linePaint.setColor(Color.BLACK);
            } else {
                if (i % 4 == 0) {
                    //DRAW LINES BETWEEN in einer Schritten
                    float xLinesOuter = (float) (xOrigin + (radiusCircle) * Math.cos((i + startAngle) * Math.PI / 180));
                    float yLinesOuter = (float) (yOrigin + (radiusCircle) * Math.sin((i + startAngle) * Math.PI / 180));

                    float xLinesInner = (float) (xOrigin + (radiusCircle - radiusCircle * 0.2f) * Math.cos((i + startAngle) * Math.PI / 180));
                    float yLinesInner = (float) (yOrigin + (radiusCircle - radiusCircle * 0.2f) * Math.sin((i + startAngle) * Math.PI / 180));
                    linePaint.setStrokeWidth(2.0f);
                    canvas.drawLine(xLinesOuter, yLinesOuter, xLinesInner, yLinesInner, linePaint);
                }
            }
        }
        update(rpm);
    }

    // Berechnet den besten Radius aus der Größe des "fensters"
    private void calcMaxRadius() {
        if (xOrigin < yOrigin)
            radiusCircle = (int) (xOrigin * 0.98);
        else
            radiusCircle = (int) (yOrigin * 0.98);

        calcSpeedNeedleLenght();
    }

    // Maximale Nadel länge 70%
    private void calcSpeedNeedleLenght() {
        speedNeedleLength = (int) (radiusCircle * 0.65f);
    }

    public void update(int rpm) {
        Paint linePaint = new Paint();
        Paint textPaint = new Paint();

        //DRAW Needle
        float x = (float) (xOrigin + speedNeedleLength * Math.cos((rpm / 50 + startAngle) * Math.PI / 180));
        float y = (float) (yOrigin + speedNeedleLength * Math.sin((rpm / 50 + startAngle) * Math.PI / 180));

        linePaint.setColor(Color.RED);
        linePaint.setStrokeWidth(6);
        canvas.drawLine(xOrigin, yOrigin, x, y, linePaint);


        // Draw Text Middle Big
        String sRPM = String.valueOf(rpm);
        textPaint.setTextSize(radiusCircle * 0.25f);
        float textSize = textPaint.measureText(sRPM);
        canvas.drawText(sRPM, xOrigin - textSize / 2, yOrigin + radiusCircle * 0.7f, textPaint);


    }

    public void setRPM(int RPM) {
        this.rpm = RPM;
    }
}
