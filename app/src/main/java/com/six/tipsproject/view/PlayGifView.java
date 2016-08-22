package com.six.tipsproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-08-21.
 */
public class PlayGifView extends View {
    private static final int DEFAULT_MOVIE_DURATION = 1000;
    private Movie movie;
    private long movieStart = 0;
    private int currentAnimTime = 0;

    public PlayGifView(Context context) {
        this(context, null);
    }

    public PlayGifView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayGifView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        movie = null;
        super.onDetachedFromWindow();
    }

    public void setMovieResId(int resId){
        movie = Movie.decodeStream(getResources().openRawResource(resId));
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(movie != null){
            setMeasuredDimension(movie.width(), movie.height());
        }else {
            setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(movie != null){
            updateAnim();
            drawGif(canvas);
            invalidate();
        }else {
            drawGif(canvas);
        }
    }

    private void updateAnim(){
        long now = SystemClock.uptimeMillis();
        if(movieStart == 0){
            movieStart = now;
        }

        int dur = movie.duration();
        if(dur == 0){
            dur = DEFAULT_MOVIE_DURATION;
        }
        currentAnimTime = (int) ((now - movieStart) % dur);
    }

    private void drawGif(Canvas canvas){
        movie.setTime(currentAnimTime);
        movie.draw(canvas, 0, 0);
        canvas.restore();
    }
}
