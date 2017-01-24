package com.six.tipsproject.butterknife;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.six.tipsproject.R;

/**
 * Created by xiaolin on 24/01/17.
 */

public class SixButterKnifeSample extends Activity {
    @InjectView(R.id.tv_label)
    private TextView vLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_one);
        try {
            SixViews.inject(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        vLabel.setText("ButterKnife");
    }
}
