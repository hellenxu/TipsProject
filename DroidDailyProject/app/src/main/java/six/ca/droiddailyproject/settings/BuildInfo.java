package six.ca.droiddailyproject.settings;

import android.annotation.TargetApi;
import android.os.Build;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Utils: get system information
 * Created by Xiaolin on 2016-05-16.
 */
public class BuildInfo {

    public static String getBoard() {
        return Build.BOARD;
    }

    public static String getBrand() {
        return Build.BRAND;
    }

    @TargetApi(21)
    public static String getSupportedABIS() {
        return strArrToString(Build.SUPPORTED_ABIS);
    }

    private static String strArrToString(String[] raw) {
        String result = "";
        for (String item : raw) {
            result += item;
        }
        return result;
    }

    public static String getDevice() {
        return Build.DEVICE;
    }

    public static String getDisplay() {
        return Build.DISPLAY;
    }

    public static String getFingerPrint() {
        return Build.FINGERPRINT;
    }

    public static String getSerialNum() {
        return Build.SERIAL;
    }

    public static String getId() {
        return Build.ID;
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getModel() {
        return Build.MODEL;
    }

    public static String getHardware() {
        return Build.HARDWARE;
    }

    public static String getProduct() {
        return Build.PRODUCT;
    }

    public static String getTags() {
        return Build.TAGS;
    }

    public static String getBuilderType() {
        return Build.TYPE;
    }

    public static String getCodename() {
        return Build.VERSION.CODENAME;
    }

    public static String getIncrementalNum() {
        return Build.VERSION.INCREMENTAL;
    }

    public static String getRelease() {
        return Build.VERSION.RELEASE;
    }

    public static int getSdkInt() {
        return Build.VERSION.SDK_INT;
    }

    public static String getHost() {
        return Build.HOST;
    }

    public static String getUser() {
        return Build.USER;
    }

    public static String getTime() {
        return dateFormatter(Build.TIME);
    }

    private static String dateFormatter(long mill) {
        final String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getAvailableLocales()[0]);
        return dateFormat.format(new Date(mill));
    }
}
