package six.ca.droiddailyproject.dialog;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import six.ca.droiddailyproject.R;


/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-08-24.
 */
public class DialogTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_dialog);
    }

    public void showDialog(View view) {
//        final LoadingDialog dialog = LoadingDialog.getInstance(R.layout.progress_dialog);
//        dialog.show(getFragmentManager(), "loading");
        final AlertDialog dialog = new AlertDialog.Builder(this, R.style.Theme_LoadingDialog).create();
        dialog.setView(LayoutInflater.from(this).inflate(R.layout.progress_dialog, null));
        dialog.show();
        dialog.getWindow().setLayout(dp2px(200), dp2px(200));

        new AsyncTask<Void, Integer, Void>() {

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
//                dialog.dismissAllowingStateLoss();
                dialog.dismiss();
            }
        }.execute();
    }

    private int dp2px(float dp){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }
}
