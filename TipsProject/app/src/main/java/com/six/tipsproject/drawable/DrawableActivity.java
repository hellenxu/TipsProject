package com.six.tipsproject.drawable;

import android.app.Activity;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.six.tipsproject.R;

/**
 * Created by Xiaolin on 2016-05-28.
 */
public class DrawableActivity extends Activity {
    private LoadingDrawableButton loadingDrawable;
    private RotateCircleLoading circleLoading;
    private StateListDrawable stateListDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_loading);

        ImageView ivLoading = (ImageView) findViewById(R.id.iv_loading);
        loadingDrawable = new LoadingDrawableButton();
        ivLoading.setImageDrawable(loadingDrawable);
//        circleLoading = new RotateCircleLoading();
//        ivLoading.setBackground(circleLoading);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadingDrawable.start();
//        circleLoading.start();
    }

    @Override
    protected void onStop() {
        loadingDrawable.stop();
        super.onStop();
    }
}
