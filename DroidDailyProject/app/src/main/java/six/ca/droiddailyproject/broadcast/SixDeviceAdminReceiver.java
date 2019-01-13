package six.ca.droiddailyproject.broadcast;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-10-20.
 */

public class SixDeviceAdminReceiver extends DeviceAdminReceiver{

    @Override
    public void onEnabled(Context context, Intent intent) {
        Log.d("xxl", "device admin enable");
        super.onEnabled(context, intent);
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        Log.d("xxl", "device admin disable");
        super.onDisabled(context, intent);
    }
}
