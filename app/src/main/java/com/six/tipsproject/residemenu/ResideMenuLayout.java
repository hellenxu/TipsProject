package com.six.tipsproject.residemenu;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.six.tipsproject.R;

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
}
