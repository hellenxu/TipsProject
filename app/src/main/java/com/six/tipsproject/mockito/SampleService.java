package com.six.tipsproject.mockito;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-15.
 */

public class SampleService extends Service {
    public String pushId;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onMessageReceived(String id, Bundle data){
        pushId = id;
        String value = data.getString("serviceKey");
    }
}
