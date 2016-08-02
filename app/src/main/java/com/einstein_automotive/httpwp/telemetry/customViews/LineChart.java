package com.einstein_automotive.httpwp.telemetry.customViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.einstein_automotive.httpwp.telemetry.DataStorage.Data;

import java.util.ArrayList;

/**
 * Created by Fabian Naaß on 07.08.2015.
 * Einstein Motorsport 2015.
 + Copyrights by Einstein Motorsport e.V
 */
public class LineChart extends View{

    float minHorizontalLineSpace = 20f;
    int horizontalLineCount =0;

    int verticalalLineCount =0;
    float minVericalLineSpace = 10f;

    float newlinePos = 20f;
    float paddingValue = 10f;
    private Canvas canvas;
    private Paint linePaint;
    private Paint[] dataPaint;


    private Paint textPaint;
    private Paint recPaint;

    private Rect main;

    private ArrayList<Data> speedData,rpmData,gearData,throttleData,brakeData,gforceData,steeringData;
    private ArrayList<Double> time;
    private ArrayList<Paint> paints = new ArrayList<Paint>();
    private Paint speedPaint,rpmPaint,gearPaint,throttlePaint,brakePaint,steeringPaint,geforecPaint;
    private int[] value = {0,0,0,1,2,3,4,10,15,22,30,44,60,70,70,75,80,100,110,70,50,40,30,20,10,5,1,0};
    float[] points;
    double[] timePoints;
    String unit, name;

    public LineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        speedPaint = new Paint();
        speedPaint.setStyle((Paint.Style.FILL));
        speedPaint.setColor(Color.MAGENTA);
        speedPaint.setAntiAlias(true);
        speedPaint.setStrokeWidth(1f);

        rpmPaint = new Paint();
        rpmPaint.setStyle((Paint.Style.FILL));
        rpmPaint.setColor(Color.MAGENTA);
        rpmPaint.setAntiAlias(true);
        rpmPaint.setStrokeWidth(1f);

        gearPaint = new Paint();
        gearPaint.setStyle((Paint.Style.FILL));
        gearPaint.setColor(Color.MAGENTA);
        gearPaint.setAntiAlias(true);
        gearPaint.setStrokeWidth(1f);

        throttlePaint = new Paint();
        throttlePaint.setStyle((Paint.Style.FILL));
        throttlePaint.setColor(Color.MAGENTA);
        throttlePaint.setAntiAlias(true);
        throttlePaint.setStrokeWidth(1f);

        brakePaint = new Paint();
        brakePaint.setStyle((Paint.Style.FILL));
        brakePaint.setColor(Color.MAGENTA);
        brakePaint.setAntiAlias(true);
        brakePaint.setStrokeWidth(1f);

        steeringPaint = new Paint();
        steeringPaint.setStyle((Paint.Style.FILL));
        steeringPaint.setColor(Color.MAGENTA);
        steeringPaint.setAntiAlias(true);
        steeringPaint.setStrokeWidth(1f);

        geforecPaint = new Paint();
        geforecPaint.setStyle((Paint.Style.FILL));
        geforecPaint.setColor(Color.MAGENTA);
        geforecPaint.setAntiAlias(true);
        geforecPaint.setStrokeWidth(1f);

        linePaint = new Paint();
        linePaint.setStyle((Paint.Style.FILL));
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(0.3f);

        textPaint = new Paint();
        textPaint.setStyle((Paint.Style.STROKE));
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(15.0f);

        recPaint = new Paint();
        recPaint.setStyle((Paint.Style.STROKE));
        recPaint.setAntiAlias(true);
        recPaint.setStrokeWidth(1.0f);
        speedData = new ArrayList<Data>();
        rpmData = new ArrayList<Data>();
        gearData = new ArrayList<Data>();
        throttleData = new ArrayList<Data>();
        brakeData = new ArrayList<Data>();
        steeringData = new ArrayList<Data>();
        gforceData = new ArrayList<Data>();
        time = new ArrayList<Double>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        // Draw horizontal lines waagerechte Linien
        // Verbesserung: bei zb 140 max kmh 14 Linien machen bei 6 Gängen 6 Linien bei beidem das mit dem höheren nehmen
        horizontalLineCount=-1;
        newlinePos = getHeight()-2* minHorizontalLineSpace; // 1. Linie zeichnen (Wert 0)
        while (newlinePos > 0){
            canvas.drawLine(0,newlinePos,getWidth(),newlinePos,linePaint);
            newlinePos-= minHorizontalLineSpace;
            horizontalLineCount++;
        }

        // Draw vertical lines senkrechte Linien
        verticalalLineCount=0;
        newlinePos = minVericalLineSpace;
        while (newlinePos < getWidth()){
            canvas.drawLine(newlinePos,0,newlinePos,getHeight()-minHorizontalLineSpace*1.5f,linePaint);
            newlinePos+= minVericalLineSpace;
            verticalalLineCount++;
        }

        // Draw Time/Distance Text
//        newlinePos = minVericalLineSpace;
//        int cnt=4;
//        while (newlinePos < getWidth()){
//            if(cnt==4) { // Draw Text only every 4th vertical fine
//                canvas.drawText(String.valueOf((int) newlinePos) + "m", newlinePos - 5f, getHeight() - 10f, textPaint);
//               cnt=0;
//            }
//            cnt++;
//            newlinePos += minVericalLineSpace;
//        }
        //float x = (verticalalLineCount-time.size())*minVericalLineSpace;
        float x;
        for (int i = 0; i < time.size(); i+=4) {
            x = (verticalalLineCount-time.size()+i)*minVericalLineSpace;
            canvas.drawText(String.format("%.1fs", time.get(i)),x,getHeight() - 10f,textPaint);
            //x = (verticalalLineCount-time.size()+i+4)*minVericalLineSpace;
        }

//        float xStart =0f, yStart = 0f,xEnd =0f, yEnd = 0f;
//        // Draw Data Lines
//        for (int i = 1; i < value.length; i++) {
//            xEnd += minVericalLineSpace;
//            yStart = (getHeight()-2* minHorizontalLineSpace)-value[i-1];
//            yEnd = (getHeight()-2* minHorizontalLineSpace)-value[i];
//            canvas.drawLine(xStart,yStart,xEnd,yEnd,linePaint);
//            xStart+= minVericalLineSpace;
//        }

        // Draw Speed Data

        if((points = calcValuePositios(speedData))!= null){
            canvas.drawLines(points,speedPaint);
        }
        if((points = calcValuePositios(rpmData))!= null){
            canvas.drawLines(points,rpmPaint);
        }
    }

    private float[] calcValuePositios(ArrayList<Data> dataList){
        if (dataList != null) {
        /*
            Draw a series of lines. Each line is taken from 4 consecutive values in the pts array.
            Thus to draw 1 line, the array must contain at least 4 values.
            This is logically the same as drawing the array as follows: drawLine(pts[0], pts[1], pts[2], pts[3]),
            followed by drawLine(pts[4], pts[5], pts[6], pts[7]) and so on.
            Parameters
            pts 	Array of points to draw [x0 y0 x1 y1 x2 y2 ...]
            offset 	Number of values in the array to skip before drawing.
            count 	The number of values in the array to process, after skipping "offset" of them.
            Since each line uses 4 values, the number of "lines" that are drawn is really (count >> 2).
            paint 	The paint used to draw the points */
            if (dataList.size() >= 2) {
                float[] points = null;
                // For each Data make 2 Point(x,y)
                points = new float[(dataList.size() - 1) * 4];
                timePoints = new double[(dataList.size() - 1) * 4];
                // höchste Linie
                float heighestLine = getHeight()-2* minHorizontalLineSpace;
                float x = (verticalalLineCount-dataList.size()-1)*minVericalLineSpace;
                int dataIdx =0;
                float y = heighestLine - ((dataList.get(dataIdx).getValue() * heighestLine) / dataList.get(dataIdx).getMax());
                // add new line ()
                for (int i = 0; i < points.length/2; i++) {
                    points[i * 2] = x;
                    points[i * 2 + 1] = y;
                    if (i % 2 == 0) {
                        dataIdx++;
                        x += minVericalLineSpace;
                        y = heighestLine - ((dataList.get(dataIdx).getValue() * heighestLine) / dataList.get(dataIdx).getMax());
                    }
                }
                return points;
            }
            return null;
        }
        return null;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(parentWidth, parentHeight);
    }

    public void updateTime(double timeStamp){
        time.add(timeStamp);
    }


    public void updateSpeed(Data data, Paint dataPaint) {
        speedData.add(data);
        speedPaint = dataPaint;
        postInvalidate();
    }

    public void updateRPM(Data data, Paint dataPaint) {
        rpmData.add(data);
        rpmPaint =dataPaint;
        postInvalidate();
    }

    public void updateGear(Data data, Paint dataPaint) {
        gearData.add(data);
        gearPaint =dataPaint;
        postInvalidate();
    }

    public void updateThrottle(Data data, Paint dataPaint) {
        throttleData.add(data);
        throttlePaint =dataPaint;
        postInvalidate();
    }

    public void updateBrake(Data data, Paint dataPaint) {
        brakeData.add(data);
        brakePaint = dataPaint;
        postInvalidate();
    }

    public void updateGForce(Data data, Paint dataPaint) {
//        gforceData.add(data);
//        postInvalidate();

    }

    public void updateSteering(Data data, Paint dataPaint) {
//        steeringData.add(data);

//        postInvalidate();
    }


}
