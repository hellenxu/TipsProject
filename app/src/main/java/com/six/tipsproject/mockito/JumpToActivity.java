package com.six.tipsproject.mockito;

import android.app.Activity;
import android.os.Bundle;

import com.six.tipsproject.R;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-10-26.
 */

public class JumpToActivity extends Activity {
    public static final String KEY_EXTRA_MESSAGE = "extra_msg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_touch);
    }
}
