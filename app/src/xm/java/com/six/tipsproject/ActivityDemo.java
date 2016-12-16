package com.six.tipsproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.six.tipsproject.fragment.DialogActivity;
import com.six.tipsproject.fragment.MainActivity;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-12-05.
 */

public class ActivityDemo extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_demo);

        findViewById(R.id.tv_loading).setOnClickListener(this);

        findViewById(R.id.tv_other).setOnClickListener(this);

        LinearLayout llayContainer = (LinearLayout) findViewById(R.id.llay_container);
        final boolean isEnableSplitMotion = llayContainer.isMotionEventSplittingEnabled();

    }

    @Override
    public void onClick(View v) {
        System.out.println("xxl-target: " + v.getId());
        switch (v.getId()){
            case R.id.tv_loading:
                startActivity(new Intent(ActivityDemo.this, MainActivity.class));
                System.out.println("xxl-000");
                break;
            case R.id.tv_other:
                startActivity(new Intent(ActivityDemo.this, DialogActivity.class));
                System.out.println("xxl-111");
                break;
        }

    }
}
