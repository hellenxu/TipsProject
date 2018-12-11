package com.six.tipsproject.settings;


/**
 * Created by Xiaolin on 2016-05-16.
 */
public class SystemInfo {
    public static String getOsVer() {
        return System.getProperty("os.version");
    }

    public static String getName() {
        return System.getProperty("os.name");
    }
}
