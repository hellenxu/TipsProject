package six.ca.droiddailyproject;

import android.app.Application;

import six.ca.droiddailyproject.fragment.InfoManager;
import six.ca.droiddailyproject.fragment.SixUnCaughtExceptionHandler;


/**
 * Created by Xiaolin on 2016-07-22.
 */
public class SixApplication extends Application {
    private static SixApplication appContext;
    public static int sharedInt = 0;
    public static InfoManager.Info sharedInfo = null;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;

//        Thread.setDefaultUncaughtExceptionHandler(new SixUnCaughtExceptionHandler(this));
    }

    public static SixApplication getAppContext(){
        return appContext;
    }
}
