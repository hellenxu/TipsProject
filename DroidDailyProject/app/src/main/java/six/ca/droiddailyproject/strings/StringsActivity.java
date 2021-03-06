package six.ca.droiddailyproject.strings;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import android.widget.Toast;
import six.ca.droiddailyproject.BuildConfig;
import six.ca.droiddailyproject.R;
import six.ca.droiddailyproject.log.CommonLogger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by Xiaolin on 2016-05-10.
 */
public class StringsActivity extends Activity {
    private final String TAG = StringsActivity.class.getSimpleName();
    private ProgressDialog dialog;
    private View dialogView;
    private static final String[] dayNames = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    private static final String[] openHours = {"8:00 AM – 5:00 PM", "8:00 AM – 5:00 PM", "8:00 AM – 5:00 PM", "8:00 AM – 5:00 PM", "8:00 AM – 5:00 PM", "Closed", "Closed"};
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

        dialogView = findViewById(R.id.llay_progress_dialog);

        StringBuilder formattedString = new StringBuilder();
        for(int i = 0; i < dayNames.length; i ++) {
            formattedString.append(String.format("%-10s%-20s\n", dayNames[i], openHours[i]));
        }

        ((TextView)findViewById(R.id.hours)).setText(formattedString.toString());

        clickableTextInit();
    }

    private void clickableTextInit() {
        TextView text = findViewById(R.id.clickableText);
        SpannableString spannableStr = new SpannableString(text.getText());
        spannableStr.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(StringsActivity.this, "click me", Toast.LENGTH_SHORT).show();
            }
        }, 5, 12, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        text.setText(spannableStr);
        text.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void showProgressDialog(View view) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Please wait");
        dialog.setMessage("Loading data");
//        dialog.setProgress(0);
//        dialog.setMax(100);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
//        dialog.getWindow().getDecorView().setBackgroundColor(0x33000000);

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
//        dialogView.setVisibility(View.VISIBLE);

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
//                dialogView.setVisibility(View.GONE);
            }
        }.execute();
    }
}
