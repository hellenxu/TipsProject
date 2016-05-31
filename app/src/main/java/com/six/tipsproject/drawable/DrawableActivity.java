package com.six.tipsproject.drawable;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.six.tipsproject.R;

/**
 * Created by Xiaolin on 2016-05-28.
 */
public class DrawableActivity extends Activity {
    private ImageView ivLoading;
    private LoadingDrawable loadingDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_loading);

        ivLoading = (ImageView) findViewById(R.id.iv_loading);
        loadingDrawable = new LoadingDrawable();
        ivLoading.setBackground(loadingDrawable);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadingDrawable.start();
    }
}
