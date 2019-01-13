package six.ca.droiddailyproject.drawable;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.animation.LinearInterpolator;

import ca.six.util.L;

/**
 * Created by Xiaolin on 2016-05-31.
 */
public class RotateCircleLoading extends Drawable implements Animatable {
    private Paint circlePaint;
    private int upRadius;
    private int downRadius;
    private int radius;
    private int centerX;
    private int centerY;
    private int degree;
    private long duration;
    private ValueAnimator mValueAnimator;

    private final int OUT_STROKE_WIDTH = 10;
    private final int INNER_STROKE_WIDTH = 6;
    private final int DEFAULT_DURATION = 10000;
    private final int ROTATE_DEGREE = 30;
    private final int MAX_DEGREE = 330;

    public RotateCircleLoading() {
        initPaint();
        initAnimator();
        initParams();
    }

    private void initPaint() {
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(OUT_STROKE_WIDTH);
        circlePaint.setColor(Color.WHITE);
    }

    private void initAnimator() {
        mValueAnimator = ValueAnimator.ofFloat(0, 1.0f);
        mValueAnimator.setInterpolator(new FastOutSlowInInterpolator());
        mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                calculateRadius((float) animation.getAnimatedValue());
                invalidateSelf();
            }
        });
    }

    private void initParams() {
        duration = DEFAULT_DURATION;
        upRadius = 0;
        downRadius = 0;
        degree = 0;
        radius = 300;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        centerX = bounds.centerX();
        centerY = bounds.centerY();
        radius = 4 * Math.min(radius, Math.min(centerX, centerY)) / 5;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        circlePaint.setStrokeWidth(OUT_STROKE_WIDTH);
        canvas.translate(centerX, centerY);
        canvas.drawCircle(0, 0, radius, circlePaint);
        canvas.rotate(degree);
        circlePaint.setStrokeWidth(INNER_STROKE_WIDTH);
        canvas.drawCircle(0, upRadius - radius, upRadius, circlePaint);
        canvas.drawCircle(0, radius - downRadius, downRadius, circlePaint);
        canvas.restore();
    }

    private void calculateRadius(float percentage) {
        upRadius = (int) (radius * percentage);
        downRadius = radius - upRadius;
        degree = (int) (MAX_DEGREE * percentage) + ROTATE_DEGREE;
    }

    @Override
    public void setAlpha(int alpha) {
        circlePaint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        circlePaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void start() {
        mValueAnimator.setDuration(duration);
        mValueAnimator.start();
    }

    @Override
    public void stop() {
        mValueAnimator.cancel();
    }

    @Override
    public boolean isRunning() {
        return mValueAnimator.isRunning();
    }
}
