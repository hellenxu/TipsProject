package com.six.tipsproject.ipc;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.six.tipsproject.R;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-17.
 */

public class JumpToSTK extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_stk);
    }

    public void openSTK(View view){
//        Intent intent = new Intent();
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addCategory(Intent.CATEGORY_LAUNCHER);
//        intent.setAction(Intent.ACTION_MAIN);
//        intent.setComponent(new ComponentName("com.android.stk", "com.android.stk.StkLauncherActivity"));
//        startActivity(intent);

        PackageManager manager = getPackageManager();
        Intent intent =manager.getLaunchIntentForPackage("com.android.stk");
        if (intent != null)
            startActivity(intent);
    }
}
