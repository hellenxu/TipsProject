package com.six.tipsproject.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.six.tipsproject.R;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-28.
 */

public class MainActivity extends Activity implements View.OnClickListener{
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_frag);

        findViewById(R.id.label_frag_one).setOnClickListener(this);
        findViewById(R.id.label_frag_two).setOnClickListener(this);

        fragmentManager = getFragmentManager();

        if(savedInstanceState == null){
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.flay_content, new FragmentOne())
                    .commit();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.label_frag_one:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.flay_content, new FragmentOne())
                        .commit();
                break;
            case R.id.label_frag_two:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.flay_content, new FragmentTwo())
                        .commit();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("xxl-main");
    }
}
