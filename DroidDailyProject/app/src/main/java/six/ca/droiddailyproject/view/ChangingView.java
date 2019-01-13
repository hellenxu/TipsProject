package six.ca.droiddailyproject.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import six.ca.droiddailyproject.R;


/**
 * Created by Xiaolin on 2016-06-10.
 */
public class ChangingView extends View {
    private Paint whitePaint, blackPaint;
    private boolean isClicked;
    private Bitmap bitmapWhite, bitmapBlack;
    private float left, top;

    public ChangingView(Context context) {
        super(context, null);
    }

    public ChangingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        whitePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        blackPaint = new Paint(whitePaint);
        isClicked = false;
        bitmapBlack = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_fingerprint_black);
        bitmapWhite = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_fingerprint_white);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClicked) {
                    whitePaint.setColorFilter(null);
                    blackPaint.setColorFilter(null);
                    isClicked = false;
                } else {
                    whitePaint.setColorFilter(new LightingColorFilter(0, 0x00FFFF00));
                    blackPaint.setColorFilter(new LightingColorFilter(1, 0X00FFFF00));
                    isClicked = true;
                }
                invalidate();
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        left = (w - bitmapWhite.getWidth()) / 2;
        top = (h - bitmapWhite.getHeight()) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmapWhite, left, top, whitePaint);
        canvas.drawBitmap(bitmapBlack, left + bitmapWhite.getWidth() + 10, top, blackPaint);
    }
}
