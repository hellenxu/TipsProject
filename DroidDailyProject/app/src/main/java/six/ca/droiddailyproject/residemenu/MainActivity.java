package six.ca.droiddailyproject.residemenu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import ca.six.util.L;
import six.ca.droiddailyproject.R;

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

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout leftMenu = (LinearLayout) inflater.inflate(R.layout.reside_menu, null);
        TextView tvHome = (TextView) leftMenu.findViewById(R.id.tv_home);
        TextView tvAccount = (TextView) leftMenu.findViewById(R.id.tv_account);
        TextView tvSettings = (TextView) leftMenu.findViewById(R.id.tv_settings);
        tvHome.setOnClickListener(this);
        tvAccount.setOnClickListener(this);
        tvSettings.setOnClickListener(this);
        leftMenu.setOnClickListener(this);

        resideMenu = new ResideMenuLayout(this);
        resideMenu.attachToActivity(this, leftMenu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_open:
                resideMenu.openMenu();
                break;
            case R.id.tv_home:
                showToast("Home");
                break;
            case R.id.tv_account:
                showToast("Account");
                break;
            case R.id.tv_settings:
                showToast("Settings");
                break;
            default:
                resideMenu.closeMenu();
        }
    }

    private void showToast(String content){
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }
}
