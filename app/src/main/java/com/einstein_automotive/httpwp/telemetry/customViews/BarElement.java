package com.einstein_automotive.httpwp.telemetry.customViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Fabian Naaß on 08.08.2015,
 * for Einstein Mitorsport 2015
 */
public class BarElement extends View {

    float value, bottom =0.0f, top = 10.0f;
    float minimum,critical;
    boolean drawMinMax = true;
    String unit;
    Rect barRect;
    Paint barPaint, barFramePaint, textPaint, linePaint;
    int paddingLeft, paddingRight, paddingTop, paddingBottom;
    String textValue, textCritical, textMinimum;
    Rect valueBounds, criticalBounds, minimumBounds;
    float heightCritical, heightMinimum;
    int longestTextSize;
    int height,width;
    float bottomLine,topLine;

    public BarElement(Context context, AttributeSet attrs) {
        super(context, attrs);
        barPaint = new Paint();
        barPaint.setAntiAlias(true);
        barPaint.setColor(Color.RED);
        barPaint.setStyle(Paint.Style.FILL);

        barFramePaint = new Paint();
        barFramePaint.setAntiAlias(true);
        barFramePaint.setColor(Color.GRAY);
        barFramePaint.setStyle(Paint.Style.STROKE);
        barFramePaint.setStrokeWidth(3);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.STROKE);

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.GRAY);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(8);

        value = 0;
        unit = "X";
        //minimum = 2f;
        //critical = 8.5f;
    }

    public void configure(String unit, float bottom, float top, float minimum, float critical){
        this.unit = unit.replace("[", "").replace("]","");
        this.bottom = bottom;
        this.top = top;
        this.critical = critical;
        this.minimum = minimum;
    }

    public void setValue(float value){
        this.value = value;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();
        paddingBottom = getPaddingBottom();
        paddingTop = getPaddingTop();
        height = getHeight();
        width = getWidth();
        bottomLine = height-paddingBottom;
        topLine = paddingTop;

        longestTextSize = getTextSize(top).width();
        linePaint.setStrokeWidth(width*0.01f);

        barRect = getRect();
        barPaint.setColor(getColor());
        float halfFrameStroke = barFramePaint.getStrokeWidth() / 2;


        // Draw Rectangle Frame
       /* canvas.drawRect(paddingLeft + halfFrameStroke, paddingTop + halfFrameStroke,
                (int) ((width - paddingRight - halfFrameStroke - longestTextSize) * 0.8f),
                height - paddingBottom - halfFrameStroke, barFramePaint);
*/
        // Draw Value Rect, Line & Text

        valueBounds = getTextSize(value);
        textValue = String.format("%.1f", value) + " " + unit;

        float textPosY = barRect.top-valueBounds.exactCenterY();
        float textOffset = valueBounds.height()/2;
        if (barRect.top+textOffset > bottomLine) // Unten
            textPosY=bottomLine;
        if (barRect.top-textOffset < topLine) // Oben
            textPosY = topLine+valueBounds.height();

        canvas.drawRect(barRect, barPaint);
        linePaint.setColor(getColor());
        canvas.drawLine(barRect.left, barRect.top, barRect.right * 1.2f, barRect.top, linePaint);
        textPaint.setColor(Color.BLACK);
        canvas.drawText(textValue, width - paddingRight - longestTextSize, textPosY, textPaint);

        // Draw Minimum line and Text

        if (minimum > 0 && drawMinMax) {
            heightMinimum = height- (height- paddingBottom*2) * (minimum / (top))-paddingBottom;
            minimumBounds = getTextSize(minimum);
            textMinimum = String.format("%.1f", minimum) + " " + unit;

            linePaint.setColor(Color.GRAY);
            canvas.drawLine(barRect.left, heightMinimum, barRect.right * 1.2f, heightMinimum, linePaint);
            textPaint.setColor(Color.GRAY);

            textPosY = heightMinimum-minimumBounds.exactCenterY();
            textOffset = minimumBounds.height()/ 2;
            if (heightMinimum+textOffset > bottomLine) // Unten
                textPosY=bottomLine;
            if (heightMinimum-textOffset < topLine) // Oben
                textPosY = topLine+minimumBounds.height();

           // canvas.drawText(textMinimum, width - paddingRight - longestTextSize, textPosY, textPaint);
        }

        //Draw Critical Line and Text

        if (critical > 0 && drawMinMax) {
            heightCritical = height- (height- paddingBottom*2) * (critical / (top))-paddingBottom;
            criticalBounds = getTextSize(critical);
            textCritical = String.format("%.1f", critical) + " " + unit;

            linePaint.setColor(Color.GRAY);
            canvas.drawLine(barRect.left, heightCritical, barRect.right * 1.2f, heightCritical, linePaint);
            textPaint.setColor(Color.GRAY);

            textPosY = heightCritical-criticalBounds.exactCenterY();
            textOffset = criticalBounds.height()/ 2;
            if (heightCritical+textOffset > bottomLine) // Unten
                textPosY=bottomLine;
            if (heightCritical-textOffset < topLine) // Oben
                textPosY = topLine+criticalBounds.height();

           // canvas.drawText(textCritical, width - paddingRight - longestTextSize, textPosY, textPaint);
        }
    }

    private Rect getRect() {
        int posBottom = height - paddingBottom*2;
        int rectHeight = (int) (posBottom * (value / top));

        return new Rect(paddingLeft, height - rectHeight-paddingBottom, (int) ((width - paddingRight - longestTextSize) * 0.8f), height - paddingBottom);
    }


    private Rect getTextSize(float tmp) {
        Rect border = new Rect();
        String textToDraw = String.format("%.1f", tmp) + " " + unit;
        textPaint.setTextSize(width * 0.2f);
        textPaint.getTextBounds(textToDraw, 0, textToDraw.length(), border);
        return border;
    }

    private int getColor() {
        int r, g, b;
//        float percentage = 100f * value / (critical+minimum);
//
//        if (value < minimum){
//            if (unit == "bar"){
//                r = 255;
//                g = 0;
//                b = 0;
//            }else{
//                r = 0;
//                g = 0;
//                b = 255;
//            }
//
//        }else if (percentage <= 50) {
//            r = (int) (255 * percentage / 50);
//            g = 255;
//            b = 0;
//        } else {
//            if (percentage>100)
//                percentage =100;
//            r = 255;
//            g = (int) (255 - 255 * percentage / 100);
//            b = 0;
//        }

        if (value <= minimum){
            if (unit == "bar"){
                r = 255;
                g = 0;
                b = 0;
            }else{
                r = 0;
                g = 0;
                b = 255;
            }

        }else if (value <= critical) {
            r = 0;
            g = 255;
            b = 0;
        } else {
            r = 255;
            g = 0;
            b = 0;
        }
        return Color.rgb(r, g, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = 70;
        int desiredHeight = 100;

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
}
