package six.ca.droiddailyproject.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import six.ca.droiddailyproject.R;


/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-10-30.
 */

public class DialogDemo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_dialog);
    }

    public void showDialog(View view){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.LoadingDialog);
        View dilaogView = LayoutInflater.from(this).inflate(R.layout.dialog_floating, null);
        builder.setView(dilaogView).setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        builder.create().show();

        View parentView = (View)dilaogView.getParent();
        View superParent = (View) parentView.getParent();
        superParent.setBackgroundColor(getResources().getColor(android.R.color.transparent));
//        parentView.setBackground(null);

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        System.out.println(telephonyManager.getSimSerialNumber());

    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("xxl-onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("xxl-onDestroy");
    }
}
