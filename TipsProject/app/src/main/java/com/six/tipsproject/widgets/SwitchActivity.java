package com.six.tipsproject.widgets;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.six.tipsproject.R;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-09-08.
 */
public abstract class SwitchActivity extends Activity {
    protected Switch switchStatus;
    protected TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_switch);

        switchStatus = (Switch) findViewById(R.id.sw_status);
        tvStatus = (TextView) findViewById(R.id.tv_switch_label);
        initListener();
    }

    protected void initListener() {
        switchStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("xxl", "switch in main");
                if(isChecked){
                    changeTextColor(true);
                }else {
                    changeTextColor(false);
                }
            }
        });
    }

    protected abstract void changeTextColor(boolean isChecked);
}
