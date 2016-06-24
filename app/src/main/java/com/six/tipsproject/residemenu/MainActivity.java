package com.six.tipsproject.residemenu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.six.tipsproject.R;

import ca.six.util.L;

/**
 * Created by Xiaolin on 2016-06-16.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ResideMenuLayout resideMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_residemenu_main);
        TextView tvOpen = (TextView) findViewById(R.id.tv_open);
        tvOpen.setOnClickListener(this);

        resideMenu = new ResideMenuLayout(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout leftMenu = (LinearLayout) inflater.inflate(R.layout.reside_menu, null);
        resideMenu.attachToActivity(this, leftMenu);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_open){
            resideMenu.openMenu();
        }
    }
}
