package com.six.tipsproject.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;

import com.six.tipsproject.R;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-09-26.
 */

public class PendingIntentDemo extends AppCompatActivity{
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pending_intent);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(getApplicationContext());
        mBuilder.setContentTitle(getString(R.string.label_notification))
                .setContentText("Test diff pending flags")
                .setSmallIcon(R.drawable.home)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setOngoing(true);
    }

    //one time
    public void showOneShot(View view){
        Intent intent = new Intent(this, PendingIntentDemo.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        mBuilder.setContentIntent(PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT));
        mNotificationManager.notify(0, mBuilder.build());
    }


    //exist ? null : new
    public void showNoCreate(View view){
        Intent intent = new Intent(this, PendingIntentDemo.class);
        mBuilder.setContentIntent(PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_NO_CREATE));
        mNotificationManager.notify(1, mBuilder.build());
    }

    //cancel current and new
    public void showCancelCurrent(View view){
        Intent intent = new Intent(this, PendingIntentDemo.class);
        mBuilder.setContentIntent(PendingIntent.getActivity(this, 2, intent, PendingIntent.FLAG_CANCEL_CURRENT));
        mNotificationManager.notify(2, mBuilder.build());
    }

    //replace extra data
    public void showUpdateCurrent(View view){
        Intent intent = new Intent(this, PendingIntentDemo.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        mBuilder.setContentIntent(PendingIntent.getActivity(this, 3, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        mNotificationManager.notify(3, mBuilder.build());
    }

    //
    public void showImmutable(View view){
        Intent intent = new Intent(this, PendingIntentDemo.class);
//        mBuilder.setContentIntent(PendingIntent.getActivity(this, 4, intent, PendingIntent.FLAG_CANCEL_CURRENT|PendingIntent.FLAG_IMMUTABLE));
        mNotificationManager.notify(4, mBuilder.build());
    }

    public void implicitIntent(View view){
        Intent intent = new Intent("com.six.tips.pending");
        mBuilder.setContentIntent(PendingIntent.getActivity(this, 5, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        mNotificationManager.notify(5, mBuilder.build());
    }

    @Override
    protected void onStop() {
//        mNotificationManager.cancelAll();
        super.onStop();
    }
}
