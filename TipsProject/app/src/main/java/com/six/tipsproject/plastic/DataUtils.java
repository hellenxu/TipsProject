package com.six.tipsproject.plastic;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.util.Log;

import com.six.tipsproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xiaolin on 2016-07-22.
 */
public class DataUtils {
    private static final int[] RES_IDS = {R.drawable.ic_boy1, R.drawable.ic_boy2, R.drawable.ic_boy3,
            R.drawable.ic_girl1, R.drawable.ic_girl2};
    private static final String[] RES_NAMES = {"boy1.png", "boy2.png", "boy3.png", "girl1.png", "girl2.png"};

    public static List<EmployeeParcelable> loadEmployees(Context context) {
        List<EmployeeParcelable> employees = new ArrayList<>();
        try {
            JSONArray employeeList = readInfo(context.getAssets().open("info.json"));
            for (int i = 0; i < employeeList.length(); i++) {
                JSONObject employee = employeeList.optJSONObject(i);
                employees.add(new EmployeeParcelable(employee.optInt(Constants.JSON_KEY_ID),
                        employee.optString(Constants.JSON_KEY_NAME),
                        employee.optString(Constants.JSON_KEY_PICTURE),
                        employee.optString(Constants.JSON_KEY_ABOUT),
                        new boolean[]{employee.optBoolean(Constants.JSON_KEY_IS_ACTIVATED)}));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static Drawable getAvatar(Context ctx, String picture) {
        int index = 0;
        for (int i = 0; i < RES_NAMES.length; i++) {
            if (picture.equals(RES_NAMES[i])) {
                index = i;
            }
        }
        return ctx.getResources().getDrawable(RES_IDS[index]);
    }

    private static JSONArray readInfo(InputStream is) {
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(is));
        StringBuilder info = new StringBuilder();
        JSONArray array = new JSONArray();
        String line;
        try {
            while ((line = bufReader.readLine()) != null) {
                info.append(line);
            }
            array = new JSONArray(info.toString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return array;
    }
}
