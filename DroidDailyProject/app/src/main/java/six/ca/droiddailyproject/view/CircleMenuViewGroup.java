package six.ca.droiddailyproject.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import ca.six.util.L;
import six.ca.droiddailyproject.R;

/**
 * Circle menu: details about touch events
 * Created by Xiaolin on 2016-06-12.
 */
public class CircleMenuViewGroup extends ViewGroup{
    private int childCount;
    private int diameter;
    private int padding;
    private int startAngle;
    private static final int[] RES_IDS = {R.drawable.account_balance, R.drawable.credit_card,
            R.drawable.feedback, R.drawable.home, R.drawable.settings,
            R.drawable.account_circle};
    private static final String[] TITLES = {"Balance", "Credit", "Features", "Home",
            "Settings", "Account"};

    public CircleMenuViewGroup(Context context) {
        this(context, null);
    }

    public CircleMenuViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircleMenuViewGroup(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        childCount = RES_IDS.length;
        for(int i = 0; i < childCount; i ++){
            final int j = i;
            ImageView item = new ImageView(context);
            item.setBackground(context.getDrawable(RES_IDS[i]));
            item.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, TITLES[j], Toast.LENGTH_SHORT).show();
                }
            });
            addView(item);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        diameter = Math.min(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        super.onMeasure(MeasureSpec.makeMeasureSpec(diameter, widthMode), MeasureSpec.makeMeasureSpec(diameter, heightMode));

        int childMode = MeasureSpec.EXACTLY;
        int childSize = diameter / 8;
        int childMeaSpec = MeasureSpec.makeMeasureSpec(childSize, childMode);
        for(int i = 0; i < childCount; i ++){
            getChildAt(i).measure(childMeaSpec, childMeaSpec);
        }
        padding = diameter / 12;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int radius = diameter / 2, distance;
        int childLeft, childTop, childWidth, childHeight, deltaDegree = 360 / childCount;
        for(int i = 0; i < childCount; i ++){
            View child = getChildAt(i);
            childWidth = child.getMeasuredWidth();
            childHeight = child.getMeasuredHeight();
            distance = radius - childWidth / 2 - padding;
            childLeft = radius + (int) (Math.cos(Math.toRadians(startAngle)) * distance) - childWidth / 2;
            childTop = radius + (int) (Math.sin(Math.toRadians(startAngle)) * distance) - childHeight / 2;
            startAngle += deltaDegree;
            startAngle %= 360;
            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
        }
    }

    private float lastX, lastY;
    private int rotateDegree;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float x = ev.getX(), y = ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                rotateDegree = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                double start = getAngle(lastX, lastY);
                double end = getAngle(x, y);
                int quadrant = getQuadrant(x, y);

                if(quadrant == 1 || quadrant == 4){
                    startAngle += (end - start) * 2;
                    rotateDegree += (end - start) * 2;
                } else {
                    startAngle += (start - end) * 2;
                    rotateDegree += (start - end) * 2;
                }
                lastX = x;
                lastY = y;
                requestLayout();
                break;
            case MotionEvent.ACTION_UP:
                if(rotateDegree > 3){
                    return true;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    private double getAngle(float x, float y){
        double sinValue = y / Math.hypot(x, y);
        double radians = Math.asin(sinValue);
        return radians * 180 / Math.PI;
    }

    private int getQuadrant(float x, float y){
        int deltaX = (int) (x - diameter / 2);
        int deltaY = (int) (y - diameter / 2);
        if(deltaX >= 0){
            return deltaY >= 0 ? 4 : 1;
        }else {
            return deltaY >= 0 ? 2 : 3;
        }
    }
}
