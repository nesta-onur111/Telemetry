package com.einstein_automotive.httpwp.telemetry.customViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Fabian Naaß on 14.11.2015.
 * Einstein Motorsport 2015.
 * + Copyrights by Einstein Motorsport e.V
 */
public class LineView extends View{

    public Paint getLinePaint() {
        return linePaint;
    }

    private Paint linePaint;
    public LineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        linePaint = new Paint();
        linePaint.setStyle((Paint.Style.FILL));
        linePaint.setColor(Color.RED);
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(3f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, getHeight(), getWidth(), 0, linePaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);

        if(parentWidth<parentHeight)
            setMeasuredDimension(parentWidth, parentWidth);
        else
            setMeasuredDimension(parentHeight, parentHeight);
    }

    public void setColor(int color) {
        linePaint.setColor(color);
        this.invalidate();
    }
}
