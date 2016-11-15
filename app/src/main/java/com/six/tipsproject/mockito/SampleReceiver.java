package com.six.tipsproject.mockito;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-14.
 */

public class SampleReceiver extends BroadcastReceiver {
    public int value = -1;

    @Override
    public void onReceive(Context context, Intent intent) {
        value = 10;
    }
}
