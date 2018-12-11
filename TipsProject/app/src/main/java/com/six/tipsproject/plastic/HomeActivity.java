package com.six.tipsproject.plastic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.six.tipsproject.R;

import java.util.ArrayList;

/**
 * Created by Xiaolin on 2016-07-22.
 */
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);

        final HomeAdapter adapter = new HomeAdapter(this, new ArrayList<EmployeeParcelable>());
        RecyclerView rvEmployees = (RecyclerView) findViewById(R.id.rv_home);
        rvEmployees.setLayoutManager(new LinearLayoutManager(this));
        rvEmployees.setAdapter(adapter);
        rvEmployees.addOnItemTouchListener(new HomeOnItemClickListener(rvEmployees) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                Log.d("Home", "pos: " + vh.getAdapterPosition());
                EmployeeParcelable employee = adapter.employeeList.get(vh.getAdapterPosition());
                if(employee.isActivated[0]) {
                    Intent it = new Intent(HomeActivity.this, DetailsActivity.class);
                    it.putExtra("selected", employee);
                    startActivity(it);
                }else {
                    Toast.makeText(HomeActivity.this, "Activated: false", Toast.LENGTH_SHORT).show();
                }
            }
        });
        new HomeAsyncTask(this, adapter).execute();
    }

}
