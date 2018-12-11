package com.six.tipsproject.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.six.tipsproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A sample about dragging, dropping and swiping of items in RecyclerView
 * <p>
 * Created by Xiaolin on 2016-06-10.
 */
public class RVDragSwipeActivity extends AppCompatActivity {
    private RecyclerView rvSample;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recycler);

        rvSample = (RecyclerView) findViewById(R.id.rv_sample);
        rvSample.setLayoutManager(new LinearLayoutManager(this));

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add(" title " + i);
        }
        RVSampleAdapter adapter = new RVSampleAdapter(this, data);
        rvSample.setAdapter(adapter);
        ItemTouchHelper itemHelper = new ItemTouchHelper(new SampleItemCallback(data, adapter));
        itemHelper.attachToRecyclerView(rvSample);
    }
}
