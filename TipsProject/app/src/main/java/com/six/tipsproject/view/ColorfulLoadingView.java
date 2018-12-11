package com.six.tipsproject.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.Animatable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

import com.six.tipsproject.R;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-09-13.
 */
public class ColorfulLoadingView extends View implements Animatable {
    private int loadingColor;
    private int pauseColor;
    private int textSize;
    private DisplayMetrics metrics;
    private WindowManager winManager;
    private Paint framePaint, progressPaint, textPaint;
    private ValueAnimator loadingAnimator;
    private int currentWidth;
    private int maxWidth;
    private String currentProgress;
    private int textX, textY;
    private Bitmap originalBitmap, progressBitmap;
    private Canvas progressCanvas;
    private Xfermode srcTopXfermode;
    private int bitmapLeft, bitmapWidth;

    private static final int DEFAULT_LOADING_COLOR = Color.parseColor("#57d2f7");
    private static final int DEFAULT_PAUSE_COLOR = Color.parseColor("#726dd1");
    private static final int DEFAULT_TEXT_SIZE = 12; //dp
    private static final int DEFAULT_HEIGHT = 30; //dp
    private static final String DOWNLOAD_LABEL = "Download ";

    public ColorfulLoadingView(Context context) {
        this(context, null);
    }

    public ColorfulLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorfulLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initValue(context, attrs);
        initPaint();
        initAnimator();
    }

    private void initValue(Context context, AttributeSet attrs) {
        winManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        metrics = new DisplayMetrics();
        originalBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.flicker);
        srcTopXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);
        bitmapWidth = originalBitmap.getWidth();
        bitmapLeft = -bitmapWidth;

        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ColorLoadingBar);
        loadingColor = ta.getColor(R.styleable.ColorLoadingBar_loadingColor, DEFAULT_LOADING_COLOR);
        pauseColor = ta.getColor(R.styleable.ColorLoadingBar_pauseColor, DEFAULT_PAUSE_COLOR);
        textSize = ta.getDimensionPixelSize(R.styleable.ColorLoadingBar_textSize, DEFAULT_TEXT_SIZE);
        ta.recycle();
    }

    private void initPaint() {
        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        framePaint = new Paint(progressPaint);
        textPaint = new Paint(progressPaint);

        progressPaint.setColor(loadingColor);
        progressPaint.setStyle(Paint.Style.FILL);
        progressPaint.setXfermode(srcTopXfermode);
        framePaint.setColor(loadingColor);
        framePaint.setStyle(Paint.Style.STROKE);
        framePaint.setStrokeWidth(dp2px(2));
        textPaint.setColor(loadingColor);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setTextSize(dp2px(textSize));
    }

    private void initAnimator() {
        loadingAnimator = ValueAnimator.ofFloat(0, 1f);
        loadingAnimator.setRepeatCount(ValueAnimator.INFINITE);
        loadingAnimator.setRepeatMode(ValueAnimator.RESTART);
        loadingAnimator.setInterpolator(new LinearInterpolator());
        loadingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                computeProgress((float) animation.getAnimatedValue());
                invalidate();
            }
        });
    }

    private void computeProgress(float percentage) {
        currentWidth = (int) (percentage * maxWidth);
        currentProgress = DOWNLOAD_LABEL + String.valueOf((int) (percentage * 100)) + "%";
        if (bitmapLeft < currentWidth) {
            bitmapLeft += dp2px(2);
        } else {
            bitmapLeft = -originalBitmap.getWidth();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        maxWidth = getWidth();
        textX = (int) ((getWidth() - textPaint.measureText(currentProgress)) / 2);
        textY = (int) ((getHeight() - textPaint.ascent()) / 2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                height = dp2px(DEFAULT_HEIGHT);
                break;
            case MeasureSpec.EXACTLY:
            case MeasureSpec.UNSPECIFIED:
                height = heightSize;
                break;
        }
        setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, heightMode));
    }

    private int dp2px(int dp) {
        winManager.getDefaultDisplay().getMetrics(metrics);
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //step 1: draw frame
        canvas.drawRect(0, 0, getWidth(), getHeight(), framePaint);

        //step 2: draw progress
//        canvas.drawRect(0, 0, currentWidth, getHeight(), progressPaint);
        progressBitmap = Bitmap.createBitmap(Math.max(1, currentWidth), getHeight(), Bitmap.Config.ARGB_8888);
        progressCanvas = new Canvas(progressBitmap);
        progressCanvas.drawColor(loadingColor);
        if (isRunning()) {
            progressPaint.setXfermode(srcTopXfermode);
//            progressCanvas.drawBitmap(progressBitmap, bitmapLeft, 0, progressPaint); // mistake
            progressCanvas.drawBitmap(originalBitmap, bitmapLeft, 0, progressPaint);
            progressPaint.setXfermode(null);
        }
        canvas.drawBitmap(progressBitmap, 0, 0, null);

        //step 3: draw text
        textPaint.setColor(loadingColor);
        canvas.drawText(currentProgress, textX, textY, textPaint);

        if (currentWidth > textX) {
            canvas.save();
            canvas.clipRect(textX, 0, Math.min(currentWidth, textX + textPaint.measureText(currentProgress)), getHeight());
            textPaint.setColor(Color.WHITE);
            canvas.drawText(currentProgress, textX, textY, textPaint);
            canvas.restore();
        }
    }

    @Override
    public void start() {
        loadingAnimator.setDuration(20000);
        loadingAnimator.start();
    }

    @Override
    public void stop() {
        loadingAnimator.cancel();
        progressPaint.setColor(pauseColor);
    }

    @Override
    public boolean isRunning() {
        return loadingAnimator.isRunning();
    }
}
