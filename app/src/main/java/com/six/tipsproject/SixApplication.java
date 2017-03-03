package com.six.tipsproject;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Xiaolin on 2016-07-22.
 */
public class SixApplication extends Application {
    private static SixApplication appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        appContext = this;
    }

    public static SixApplication getAppContext(){
        return appContext;
    }
}
