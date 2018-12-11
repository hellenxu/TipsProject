package com.six.tipsproject.view;

import android.content.Context;
import android.view.ViewConfiguration;

/**
 * Common utils for view
 * Created by Xiaolin on 2016-05-24.
 */
public class ViewUtils {
    public static int getTouchSlop(Context ctx) {
        return ViewConfiguration.get(ctx).getScaledTouchSlop();
    }
}
