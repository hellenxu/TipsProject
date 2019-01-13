package six.ca.droiddailyproject.view;

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
    private Movie movie;
    private int movieDuration;
    private long movieStart = 0;

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
        movieDuration = movie.duration();
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
        long now = SystemClock.uptimeMillis();
        if(movieStart == 0){
            movieStart = now;
        }

        int currentAnimTime = (int) ((now - movieStart) % movieDuration);
        movie.setTime(currentAnimTime);
        movie.draw(canvas, 0, 0);

        invalidate();
    }
}
