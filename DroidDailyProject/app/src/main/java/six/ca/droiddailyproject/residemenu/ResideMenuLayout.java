package six.ca.droiddailyproject.residemenu;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import androidx.annotation.NonNull;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import six.ca.droiddailyproject.R;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple version of ResideMenu
 * <p/>
 * Created by Xiaolin on 2016-06-16.
 */
public class ResideMenuLayout extends FrameLayout {
    private FrameLayout decorView;
    private FrameLayout contentView;
    private LinearLayout subView;
    private View lefMenu;
    private Activity contentActivity;
    private List<View> ignoredViews = new ArrayList<>();

    public ResideMenuLayout(Context context) {
        this(context, null);
    }

    public ResideMenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ResideMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupMenu(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupMenu(Context context) {
        setBackground(context.getDrawable(R.drawable.menu_bg));
    }

    public void attachToActivity(Activity activity, View menu) {
        lefMenu = menu;
        lefMenu.setAlpha(0);
        contentActivity = activity;

        decorView = (FrameLayout) activity.getWindow().getDecorView(); // decorview[FrameLayout]
        subView = (LinearLayout) decorView.getChildAt(0);
        contentView = (FrameLayout) subView.findViewById(android.R.id.content);

        decorView.removeViewAt(0);
        this.addView(lefMenu);
        this.addView(subView);
        decorView.addView(this, 0);
    }

    public void openMenu() {
        setPivotPoint();

        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(ObjectAnimator.ofFloat(lefMenu, ALPHA, 1),
                ObjectAnimator.ofFloat(contentView, SCALE_X, 0.5f),
                ObjectAnimator.ofFloat(contentView, SCALE_Y, 0.5f));
        animSet.setDuration(300);
            animSet.start();
    }

    private void setPivotPoint(){
        DisplayMetrics dm = new DisplayMetrics();
        contentActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;

        contentView.setPivotX(1.2f * screenWidth);
        contentView.setPivotY(.4f * screenHeight);
    }

    public void closeMenu() {
        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(ObjectAnimator.ofFloat(lefMenu, ALPHA, 0),
                ObjectAnimator.ofFloat(contentView, SCALE_X, 1),
                ObjectAnimator.ofFloat(contentView, SCALE_Y, 1));
        animSet.setDuration(300);
        animSet.start();
    }

    public void addIgnoredViews(@NonNull View view){
        ignoredViews.add(view);
    }

    private boolean isInIgnoredView(MotionEvent ev) {
        Rect rect = new Rect();
        for (View v : ignoredViews) {
            v.getGlobalVisibleRect(rect);
            if (rect.contains((int) ev.getX(), (int) ev.getY()))
                return true;
        }
        return false;
    }
}
