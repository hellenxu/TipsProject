package com.six.tipsproject.view;

import android.app.Activity;
import android.os.Bundle;

import com.six.tipsproject.R;

/**
 * Common Activity for view tests.
 * Created by Xiaolin on 2016-05-27.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_circle_menu);
        CircleMenuViewGroup circleMenu = (CircleMenuViewGroup) findViewById(R.id.cm_sample);
    }
}
