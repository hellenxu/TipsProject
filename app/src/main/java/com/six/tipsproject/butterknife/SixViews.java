package com.six.tipsproject.butterknife;

import android.app.Activity;

import com.six.tipsproject.R;

import java.lang.reflect.Field;

/**
 * Created by xiaolin on 24/01/17.
 */

public class SixViews {

    public static void inject(Activity activity) throws IllegalAccessException {
        Class<? extends Activity> clazz = activity.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field field : declaredFields){
            if(field.isAnnotationPresent(InjectView.class)){
                InjectView injectedView = field.getAnnotation(InjectView.class);
                int value = injectedView.value();
                field.setAccessible(true);
                field.set(activity, activity.findViewById(value));
            }
        }
    }
}
