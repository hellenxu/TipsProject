package six.ca.droiddailyproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Xiaolin on 2016-05-27.
 */
public class DiagramView extends View {
    private Paint circlePaint;
    private Paint textPaint;
    private Paint linePaint;
    private Paint arcPaint;
    private int centerX;
    private int centerY;
    private RectF arcRect;
    private final int ROTATE_DEGREE = 72;
    private final int RADIUS = 100;
    private final int LINE_LENGTH = 80;
    private final int STROKE_WIDTH = 6;
    private final int TEXT_SIZE = 35;
    private final int TEXT_STROKE = 4;
    private final String[] TEXTS = {"Somethings", "Apple", "Banana", "Bee", "Life", "Loving"};
    private final String[] ARC_TXT = {"aa", "bb", "cc", "dd"};

    public DiagramView(Context context) {
        super(context);
        init();
    }

    public DiagramView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DiagramView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(STROKE_WIDTH);
        circlePaint.setColor(Color.WHITE);

        textPaint = new Paint(circlePaint);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setStrokeWidth(TEXT_STROKE);
        textPaint.setTextSize(TEXT_SIZE);

        linePaint = new Paint(circlePaint);
        linePaint.setStrokeCap(Paint.Cap.ROUND);

        arcPaint = new Paint(circlePaint);

        arcRect = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = getMeasuredWidth() / 2;
        centerY = 2 * getMeasuredHeight() / 3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.argb(255, 241, 156, 119));
        canvas.translate(centerX, centerY);

        drawCenterCircle(canvas);
        drawFirstLayerCircle(canvas);
        drawExtendCircle(canvas);
        drawArc(canvas);
    }

    private void drawCenterCircle(Canvas canvas) {
        canvas.drawCircle(0, 0, RADIUS, circlePaint);
        canvas.drawText("SIX.CA", 0, textPaint.descent(), textPaint);
    }

    private void drawFirstLayerCircle(Canvas canvas) {
        int startDegree = ROTATE_DEGREE / 2;
        int lineEndY = -RADIUS - LINE_LENGTH;
        float radius, startY, endY;

        for (int i = 0; i < 5; i++) {
            int tempDegree = startDegree + ROTATE_DEGREE * i;
            canvas.save();
            canvas.rotate(tempDegree);
            if (i != 0 && i != 4) {
                radius = RADIUS * 0.9f;
                startY = -RADIUS * 1.1f;
                endY = lineEndY * 0.95f;
            } else {
                radius = RADIUS;
                startY = -RADIUS;
                endY = lineEndY;
            }
            canvas.drawLine(0, startY, 0, endY, linePaint);
            canvas.translate(0, lineEndY - RADIUS);
            canvas.drawCircle(0, 0, radius, circlePaint);
            canvas.rotate(-tempDegree);
            canvas.drawText(TEXTS[i], 0, textPaint.descent(), textPaint);
            canvas.restore();
        }
    }

    private void drawExtendCircle(Canvas canvas) {
        canvas.save();
        canvas.rotate(-ROTATE_DEGREE / 2);
        canvas.drawLine(0, -3 * RADIUS - LINE_LENGTH, 0, -3 * RADIUS - 2 * LINE_LENGTH, linePaint);
        canvas.translate(0, -4 * RADIUS - 2 * LINE_LENGTH);
        canvas.drawCircle(0, 0, RADIUS, circlePaint);
        canvas.rotate(ROTATE_DEGREE / 2);
        canvas.drawText(TEXTS[5], 0, textPaint.descent(), textPaint);
        canvas.restore();
    }

    private void drawArc(Canvas canvas) {
        canvas.save();
        canvas.rotate(ROTATE_DEGREE / 2);
        canvas.translate(0, -2 * RADIUS - LINE_LENGTH);
        int radius = RADIUS * 4 / 3;
        arcRect.set(-radius, -radius, radius, radius);
        arcPaint.setStyle(Paint.Style.FILL);
        arcPaint.setColor(0x55EC6941);
        canvas.drawArc(arcRect, 200, 120, true, arcPaint);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setColor(Color.WHITE);
        canvas.drawArc(arcRect, 200, 120, false, arcPaint);
        canvas.rotate(-70);

        for (int i = 0; i < 4; i++) {
            canvas.save();
            if (i != 0){
                canvas.rotate(40 * i);
            }
            canvas.drawText(ARC_TXT[i], 0, -radius * 1.1f, textPaint);
            canvas.restore();
        }
        canvas.restore();
    }
}
