package six.ca.droiddailyproject;

import android.app.Application;

import six.ca.droiddailyproject.fragment.SixUnCaughtExceptionHandler;


/**
 * Created by Xiaolin on 2016-07-22.
 */
public class SixApplication extends Application {
    private static SixApplication appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;

        Thread.setDefaultUncaughtExceptionHandler(new SixUnCaughtExceptionHandler(this));
    }

    public static SixApplication getAppContext(){
        return appContext;
    }
}
