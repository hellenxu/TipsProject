package com.six.tipsproject.strings;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

import com.six.tipsproject.BuildConfig;
import com.six.tipsproject.R;
import com.six.tipsproject.log.CommonLogger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by Xiaolin on 2016-05-10.
 */
public class StringsActivity extends Activity {
    private final String TAG = StringsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_strings);

        initView();
    }

    private void initView() {
        final Resources res = getResources();
//        ((TextView)findViewById(R.id.tv_int)).setText(getString(R.string.buy_kindle, 1));
        final String quantityString = res.getQuantityString(R.plurals.buy_kindle, 0);
        final String quantityString1 = res.getQuantityString(R.plurals.buy_kindle, 1, 1);
        final String quantityString2 = res.getQuantityString(R.plurals.buy_kindle, 3, 3);
        ((TextView)findViewById(R.id.tv_zero)).setText(quantityString);
        ((TextView)findViewById(R.id.tv_single)).setText(quantityString1);
        ((TextView)findViewById(R.id.tv_plural)).setText(quantityString2);

        CommonLogger.logD(TAG, quantityString);
        CommonLogger.logD(TAG, quantityString1);
        CommonLogger.logD(TAG, quantityString2);
        CommonLogger.logD(TAG, BuildConfig.EMAIL);
        CommonLogger.logD(TAG, res.getString(R.string.res_sample));
        List<String> content = new CopyOnWriteArrayList<>();
        content.add("item00");
        content.add("item00");
        content.add("item00");
        content.add("item00");

        for(String item : content){
            CommonLogger.logD(TAG, item);
        }
    }
}
