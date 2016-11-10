package com.six.tipsproject.mockito;


import android.app.Activity;
import android.os.Bundle;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-09.
 */

public class LifeCycleActivity extends Activity {
    String stage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stage = "onCreate";
    }

    @Override
    protected void onResume() {
        super.onResume();
        stage = "onResume";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stage = "onDestory";
    }
}
