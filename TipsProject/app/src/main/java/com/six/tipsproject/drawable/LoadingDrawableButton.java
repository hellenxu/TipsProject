package com.six.tipsproject.drawable;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;

/**
 * Material design loading drawable
 * Created by Xiaolin on 2016-05-30.
 */
public class LoadingDrawableButton extends Drawable implements Animatable {
    private Paint arcPaint;
    private ValueAnimator loadingAnimator;
    private long duration;
    private float startAngle;
    private float sweepAngle;
    private RectF arcRectF;

    private static final float DEFAULT_STROKE_WIDTH = 5;
    private static final int DEFAULT_DURATION = 1500;
    private static final float MAX_DEGREE = 350;
    private static final int DEGREE_INCREMENTAL = 10;

    public LoadingDrawableButton() {
        initParams();
        initPaint();
        initAnimator();
    }

    private void initParams() {
        arcRectF = new RectF();
        duration = DEFAULT_DURATION;
        startAngle = 0;
        sweepAngle = 0;
    }

    private void initPaint() {
        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(DEFAULT_STROKE_WIDTH);
        arcPaint.setColor(Color.WHITE);
    }

    private void initAnimator() {
        loadingAnimator = ValueAnimator.ofFloat(0, 1.0f);
        loadingAnimator.setRepeatCount(ValueAnimator.INFINITE);
        loadingAnimator.setRepeatMode(ValueAnimator.RESTART);
        loadingAnimator.setInterpolator(new LinearInterpolator());
        loadingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                computeProgress((float) animation.getAnimatedValue());
                invalidateSelf();
            }
        });
    }

    @Override
    public void start() {
        loadingAnimator.setDuration(duration);
        loadingAnimator.start();
    }

    @Override
    public void stop() {
        loadingAnimator.cancel();
    }

    @Override
    public boolean isRunning() {
        return loadingAnimator.isRunning();
    }

    @Override
    public void draw(Canvas canvas) {
        final int size = Math.min(getBounds().width(), getBounds().height()) / 5;
        arcRectF.set(getBounds().left + size, getBounds().top + size, getBounds().right - size, getBounds().bottom - size);
        canvas.drawArc(arcRectF, startAngle, sweepAngle, false, arcPaint);
        canvas.drawText("test", 0, 0, arcPaint);
    }

    private void computeProgress(float percentage) {
        startAngle = 360 * percentage;
        sweepAngle = MAX_DEGREE * percentage + DEGREE_INCREMENTAL;
//        L.d("sweepAngle: " + sweepAngle + "; startAngle: " + startAngle);
    }

    @Override
    public void setAlpha(int alpha) {
        arcPaint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        arcPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
