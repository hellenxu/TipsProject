package com.six.tipsproject.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

import com.six.tipsproject.R;

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
        builder.setView(R.layout.dialog_floating);
        builder.create().show();
    }
}
