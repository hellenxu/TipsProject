package com.six.tipsproject.strings;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
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
    private ProgressDialog dialog;

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
        ((TextView) findViewById(R.id.tv_zero)).setText(quantityString);
        ((TextView) findViewById(R.id.tv_single)).setText(quantityString1);
        ((TextView) findViewById(R.id.tv_plural)).setText(quantityString2);

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

        for (String item : content) {
            CommonLogger.logD(TAG, item);
        }

        TextView tvDes = (TextView) findViewById(R.id.tv_des);
        SpannableString spanDes = new SpannableString(getString(R.string.much));
        spanDes.setSpan(new AbsoluteSizeSpan(20, true), 1, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanDes.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 20, 24, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvDes.setText(spanDes);
    }

    public void showProgressDialog(View view) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Please wait");
        dialog.setMessage("Loading data...");
        dialog.setProgress(0);
//        dialog.setMax(100);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(dialog.getProgress() <= dialog.getMax()){
//                    try {
//                        Thread.sleep(6000);
//                        dialog.dismiss();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    if(dialog.getProgress() == dialog.getMax()){
//                        dialog.dismiss();
//                    }
//                }
//            }
//        }).start();

        new AsyncTask<Void, Integer, Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dialog.dismiss();
            }
        }.execute();
    }
}
