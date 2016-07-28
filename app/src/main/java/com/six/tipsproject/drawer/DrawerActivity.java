package com.six.tipsproject.drawer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.six.tipsproject.R;
import com.six.tipsproject.plastic.DetailsActivity;
import com.six.tipsproject.plastic.EmployeeParcelable;
import com.six.tipsproject.plastic.HomeAdapter;
import com.six.tipsproject.plastic.HomeAsyncTask;
import com.six.tipsproject.plastic.HomeOnItemClickListener;

import java.util.ArrayList;

/**
 * Created by Xiaolin on 2016-07-27.
 */

public class DrawerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_drawer);

        CollapsingToolbarLayout coltbLayout = (CollapsingToolbarLayout) findViewById(R.id.colToolbar);
        coltbLayout.setTitle("Drawer");
        coltbLayout.setExpandedTitleColor(Color.WHITE);
        coltbLayout.setCollapsedTitleTextColor(Color.WHITE);

        RecyclerView rvDrawer = (RecyclerView) findViewById(R.id.rv_drawer);
        final HomeAdapter adapter = new HomeAdapter(this, new ArrayList<EmployeeParcelable>());
        rvDrawer.setLayoutManager(new LinearLayoutManager(this));
        rvDrawer.setAdapter(adapter);
        rvDrawer.addOnItemTouchListener(new HomeOnItemClickListener(rvDrawer) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                Log.d("Home", "pos: " + vh.getAdapterPosition());
                EmployeeParcelable employee = adapter.employeeList.get(vh.getAdapterPosition());
                if(employee.isActivated[0]) {
                    Intent it = new Intent(DrawerActivity.this, DetailsActivity.class);
                    it.putExtra("selected", employee);
                    startActivity(it);
                }else {
                    Toast.makeText(DrawerActivity.this, "Activated: false", Toast.LENGTH_SHORT).show();
                }
            }
        });

        new HomeAsyncTask(this, adapter).execute();
    }
}
