package com.six.tipsproject.widgets;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-09-08.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button btnSettings = new Button(this);
        btnSettings.setText("Settings");
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSettings = new Intent(MainActivity.this, AnzhiSwitchActivity.class);
                startActivity(toSettings);
            }
        });

        setContentView(btnSettings);
    }
}
