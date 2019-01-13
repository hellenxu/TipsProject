package six.ca.droiddailyproject.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * First sample of custom drawable
 * Created by Xiaolin on 2016-05-28.
 */
public class CustomDrawable extends Drawable {
    private Paint mPaint;

    public CustomDrawable(int color){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setColor(color);
    }

    @Override
    public void draw(Canvas canvas) {
        final Rect bounds = getBounds();
        float cenX = bounds.exactCenterX();
        float cenY = bounds.exactCenterY();
        canvas.drawCircle(cenX, cenY, Math.min(cenX, cenY), mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
