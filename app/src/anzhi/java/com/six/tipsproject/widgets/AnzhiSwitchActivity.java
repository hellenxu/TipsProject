package com.six.tipsproject.widgets;

import android.graphics.Color;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-09-08.
 */
public class AnzhiSwitchActivity extends SwitchActivity {

    @Override
    protected void changeTextColor(boolean isChecked) {
        if(isChecked){
            tvStatus.setTextColor(Color.BLUE);
        }else {
            tvStatus.setTextColor(Color.CYAN);
        }
    }
}
