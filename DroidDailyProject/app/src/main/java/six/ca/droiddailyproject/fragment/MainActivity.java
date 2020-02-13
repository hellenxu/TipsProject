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
                    .replace(R.id.flay_content, new FragmentOne(), "tagOne")
                    .addToBackStack("tagOne")
                    .commit();
            currentTab = "tagOne";
        }
    }

    private String currentTab;

    @Override
    public void onClick(View v) {
        System.out.println("xxl-findFragmentByTag00: " + fragmentManager.findFragmentByTag("tagOne"));
        System.out.println("xxl-findFragmentByTag01: " + fragmentManager.findFragmentByTag("tagTwo"));

        switch (v.getId()){
            case R.id.label_frag_one:
                System.out.println("xxl-click: one");

                fragmentManager
                        .beginTransaction()
                        .replace(R.id.flay_content, new FragmentOne(), "tagOne")
                        .addToBackStack("tagOne")
                        .commit();
                currentTab = "tagOne";
                break;
            case R.id.label_frag_two:
                System.out.println("xxl-click: two");

                fragmentManager
                        .beginTransaction()
                        .replace(R.id.flay_content, new FragmentTwo(), "tagTwo")
                        .addToBackStack("tagTwo")
                        .commit();
                currentTab = "tagTwo";
                break;
            case R.id.label_frag_three:

                System.out.println("xxl-click: three");

                fragmentManager
                        .beginTransaction()
                        .replace(R.id.flay_content, new FragmentThree(), "tagThree")
                        .addToBackStack("tagThree")
                        .commit();
                currentTab = "tagThree";
                break;
            case R.id.label_frag_lifecycle:
                System.out.println("xxl-click: lifecycle");

                fragmentManager
                        .beginTransaction()
                        .replace(R.id.flay_content, new LifecycleFragment(), "tagFour")
                        .addToBackStack("tagFour")
                        .commit();
                break;
        }
        System.out.println("xxl-findFragmentByTag11: " + fragmentManager.findFragmentByTag("tagOne"));
        System.out.println("xxl-findFragmentByTag110: " + fragmentManager.findFragmentByTag("tagTwo"));

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
