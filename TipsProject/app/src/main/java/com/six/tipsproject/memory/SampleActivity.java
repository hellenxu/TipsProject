package com.six.tipsproject.memory;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ca.six.util.L;

/**
 * A sample to show how to leak a Context or an Activity
 *
 * Created by Xiaolin on 2016-06-09.
 */
public class SampleActivity extends AppCompatActivity {
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            L.d("handling msg...");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                L.d("running...");
            }
        }, 1000 * 60 * 4);
    }
}
