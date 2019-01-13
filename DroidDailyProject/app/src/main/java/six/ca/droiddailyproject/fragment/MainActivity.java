package six.ca.droiddailyproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import six.ca.droiddailyproject.R;


/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-28.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_frag);

        findViewById(R.id.label_frag_one).setOnClickListener(this);
        findViewById(R.id.label_frag_two).setOnClickListener(this);
        findViewById(R.id.label_frag_three).setOnClickListener(this);
        findViewById(R.id.label_frag_lifecycle).setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();

        if(savedInstanceState == null){
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.flay_content, new FragmentOne())
                    .addToBackStack("tagOne")
                    .commit();
            currentTab = "tagOne";
        }
    }

    private String currentTab;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.label_frag_one:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.flay_content, new FragmentOne())
                        .addToBackStack("tagOne")
                        .commit();
                currentTab = "tagOne";
                break;
            case R.id.label_frag_two:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.flay_content, new FragmentTwo())
                        .addToBackStack("tagTwo")
                        .commit();
                currentTab = "tagTwo";
                break;
            case R.id.label_frag_three:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.flay_content, new FragmentThree())
                        .addToBackStack("tagThree")
                        .commit();
                currentTab = "tagThree";
                break;
            case R.id.label_frag_lifecycle:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.flay_content, new LifecycleFragment())
                        .addToBackStack("tagFour")
                        .commit();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("xxl-main");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(outState == null){
            outState = new Bundle();
            outState.putString("currentTab", currentTab);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
