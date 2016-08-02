package com.einstein_automotive.httpwp.telemetry.customViews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.einstein_automotive.httpwp.telemetry.R;


/**
 * Created by Higgy on 25.07.2015.
 */
public class SteeringView extends View {

    float smallestSide = 0;

    int xView, yView;
    Bitmap bitmap;

    float angle = 0f;
    Matrix matrix;
    Paint textPaint;

    public SteeringView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.steering_wheel);
        matrix = new Matrix();
        textPaint = new Paint();
        textPaint.setStyle((Paint.Style.STROKE));
        textPaint.setAntiAlias(true);
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        xView = getWidth();
        yView = getHeight();
        calcMaxRadius();

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = smallestSide / width;
        float scaleHeight = smallestSide / height;

        // Matrix für die Rotier, Scale und verschiebungen

        matrix.reset();
        // Rotiere um die Center Pos des Bitmaps, Angle Grad
        matrix.postRotate(angle, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        // Größe des Bitmaps an die Größe des Fensters anpassen
        matrix.postScale(scaleWidth, scaleHeight);
        // In die Mitte Verschieben
        matrix.postTranslate((xView / 2) - smallestSide / 2, (yView / 2) - smallestSide / 2);

        // Zeichne Bitmap mit matrix
        canvas.drawBitmap(bitmap, matrix, null);

//        String text = String.valueOf(angle);
//        textPaint.setTextSize(smallestSide * 0.2f);
//        textPaint.setColor(Color.RED);
//        float textSize = textPaint.measureText(text);
//        canvas.drawText(text, xView / 2 - textSize / 2, yView * 0.33f, textPaint);

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


    // Berechnet den besten Radius aus der Groessee des "fensters"
    private void calcMaxRadius() {
        if (xView < yView)
            smallestSide = xView;// * 0.8f;
        else
            smallestSide = yView;// * 0.8f;

    }

    public void setAngle(float angle){
        this.angle = angle;
    }
}

