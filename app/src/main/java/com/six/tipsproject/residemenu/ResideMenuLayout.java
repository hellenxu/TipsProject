package com.six.tipsproject.residemenu;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.six.tipsproject.R;

/**
 * A simple version of ResideMenu
 *
 * Created by Xiaolin on 2016-06-16.
 */
public class ResideMenuLayout extends FrameLayout {
    private LinearLayout leftMenu;
    private ScrollView svLeftMenu;
    private static final int LEFT_MENU_ID = 0;

    public ResideMenuLayout(Context context) {
        this(context, null);
    }

    public ResideMenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ResideMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackground(context.getDrawable(R.drawable.menu_bg));
    }

    private void setupLeftMenu(Context context){

    }


}
