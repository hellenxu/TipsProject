package com.six.tipsproject.dialog;

import android.app.Dialog;
import android.content.Context;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-08-24.
 */
public class SixDialog extends Dialog {

    public SixDialog(Context context) {
        super(context);
    }

    public SixDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected SixDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


}
