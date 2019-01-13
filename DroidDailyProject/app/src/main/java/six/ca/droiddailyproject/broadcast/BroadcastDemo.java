package six.ca.droiddailyproject.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2017-09-06.
 */

public class BroadcastDemo extends Activity {
    private LocalBroadcastManager localBroadcastManager;
    private SixBroadcastReceiver sixBroadcastReceiver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        sixBroadcastReceiver = new SixBroadcastReceiver();
        localBroadcastManager.registerReceiver(sixBroadcastReceiver, new IntentFilter("ca.six.tips"));
        localBroadcastManager.sendBroadcast(new Intent("ca.six.tips"));
    }

    @Override
    protected void onDestroy() {
        localBroadcastManager.unregisterReceiver(sixBroadcastReceiver);
        super.onDestroy();
    }
}
