package com.six.tipsproject.log;

import android.util.Log;

/**
 * Created by Xiaolin on 2016-05-10.
 */
public class CommonLogger {

    public static void logI(final String tag, String content){
        Log.i(tag, content);
    }

    public static void logD(final String tag, String content){
        Log.d(tag, content);
    }

    public static void logE(final String tag, String content){
        Log.e(tag, content);
    }
}
