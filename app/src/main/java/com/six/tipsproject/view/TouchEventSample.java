package com.six.tipsproject.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.six.tipsproject.R;

/**
 * Created by Xiaolin on 2016-07-21.
 */
public class TouchEventSample extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_touch);

        TouchTextView tvTouch = (TouchTextView) findViewById(R.id.tv_touch);
        tvTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("Listener", "onTouch");
                return false;
            }
        });
    }
}
