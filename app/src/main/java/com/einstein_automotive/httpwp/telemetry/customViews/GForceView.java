package com.einstein_automotive.httpwp.telemetry.customViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Fabian Naaß on 25.07.2015,
 * for Einstein Mitorsport 2015
 */
public class GForceView extends View{


    Canvas canvas;
    int xOrigin,yOrigin;
    int radiusCircle;
    float maxGForce = 0.0f;
    float maxValue = 5.0f;
    List<Point> pointList;
    float pointSize;

    Paint pointPaint, linePaint, textPaint, ovalPaint;

    public GForceView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        linePaint = new Paint();
        linePaint.setStyle((Paint.Style.FILL));
        linePaint.setAntiAlias(true);
        textPaint = new Paint();
        ovalPaint = new Paint();
        ovalPaint.setStyle((Paint.Style.STROKE));
        ovalPaint.setAntiAlias(true);
        pointList = new ArrayList<>();
        pointPaint = new Paint();
        pointPaint.setStyle((Paint.Style.FILL));
        pointPaint.setAntiAlias(true);
        pointPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        super.onDraw(canvas);

        xOrigin = getWidth() / 2;
        yOrigin = getHeight() / 2;
        calcMaxRadius();
        // Kreis zeichnen, in der mitte
        canvas.drawCircle(xOrigin, yOrigin, radiusCircle, ovalPaint);

        canvas.drawCircle(xOrigin, yOrigin, radiusCircle/2, ovalPaint);
//        canvas.drawCircle(xOrigin, yOrigin, radiusCircle / 4 * 3, ovalPaint);
//        canvas.drawCircle(xOrigin, yOrigin, radiusCircle / 4, ovalPaint);
        canvas.drawLine(xOrigin, yOrigin - radiusCircle, xOrigin, yOrigin + radiusCircle, linePaint);
        canvas.drawLine(xOrigin - radiusCircle, yOrigin, xOrigin + radiusCircle, yOrigin, linePaint);

        String textMain = String.valueOf(maxValue);
        textPaint.setTextSize(radiusCircle * 0.2f);
        float textSize = textPaint.measureText(textMain);
        // TOP
        canvas.drawText(textMain, xOrigin - textSize / 2, yOrigin - radiusCircle * 1.05f, textPaint);
        // BOTTOM
        canvas.drawText(textMain, xOrigin - textSize / 2, yOrigin + radiusCircle * 1.2f, textPaint);
        // LEFT
        canvas.drawText(textMain, xOrigin - radiusCircle * 1.3f, yOrigin + textSize / 4, textPaint);
        // RIGHT
        canvas.drawText(textMain, xOrigin + radiusCircle * 1.03f, yOrigin + textSize / 4, textPaint);

        textMain = "Max:";
        textPaint.setTextSize(radiusCircle * 0.24f);
        textPaint.setColor(Color.RED);
        textSize = textPaint.measureText(textMain);
        //Max g-Force
        canvas.drawText(textMain, getWidth() * 0.033f , getHeight() * 0.94f, textPaint);
        textMain = String.format("%.2f",maxGForce);
        textPaint.setColor(Color.RED);
        canvas.drawText(textMain, getWidth() * 0.033f + textSize, getHeight() * 0.94f, textPaint);

        update();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = 200;
        int desiredHeight = 200;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }

    public void addPoint(float x, float y){
        Point p = new Point(x, y);
        getMaxGForce(p);
        pointList.add(p);


    }

    public void addPoint(Point p){
        getMaxGForce(p);
        pointList.add(p);

    }

    private void getMaxGForce(Point p){
        if (p.getValue() > maxGForce)
            maxGForce = p.getValue();
    }

    public void clearPoints(){
        pointList.clear();
        maxGForce = 0.0f;
    }


    // Berechnet den besten Radius aus der Größe des "fensters"
    private void calcMaxRadius(){
        if (xOrigin < yOrigin)
            radiusCircle = (int)(xOrigin*0.75);
        else
            radiusCircle = (int)(yOrigin*0.75);
    }

    public void update(){
        for (int i=0; i<pointList.size(); i++){
            float value = pointList.get(i).getValue();
            if (value <= maxValue && value >=maxValue*-1.0f){

                if (i == pointList.size()-1) {
                    pointPaint.setColor(Color.RED);
                    pointSize = radiusCircle * 0.1f;
                }
                canvas.drawCircle(  xOrigin + pointList.get(i).getX() * (radiusCircle/5),
                                    yOrigin + pointList.get(i).getY() * (radiusCircle/5), pointSize, pointPaint);
            }
        }
        pointPaint.setColor(Color.GRAY);
        pointSize = radiusCircle * 0.05f;
    }
}
