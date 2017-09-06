package com.six.tipsproject.broadcast;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.six.tipsproject.R;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-09-22.
 */

public class SixBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "test";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("ca.six.tips")) {
            System.out.println("received broadcast sent by local manager...");
        } else {
            //noinspection HardCodedStringLiteral
            Log.d("xxl", "connectivity change");
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent("com.six.tips.pending"), PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setSmallIcon(R.drawable.home)
                    .setContentTitle("Boot Completed")
                    .setContentText("Please enjoy your journey.")
                    .setAutoCancel(true)
                    .setOngoing(true)
                    .setContentIntent(pendingIntent);

            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0, builder.build());

            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Log.d("xxl", "telephony: " + telephonyManager.isNetworkRoaming());

//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//        Log.d("xxl", "networkInfo: " + networkInfo.isRoaming());
        }

    }
}
