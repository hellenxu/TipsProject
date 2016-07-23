package com.six.tipsproject.plastic;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Xiaolin on 2016-07-22.
 */
public class HomeAsyncTask extends AsyncTask<Void, Integer, List<EmployeeParcelable>> {
    private Context context;
    private HomeAdapter adapter;

    public HomeAsyncTask(Context context, HomeAdapter adapter){
        this.context = context;
        this.adapter = adapter;
    }

    @Override
    protected List<EmployeeParcelable> doInBackground(Void... params) {
        return DataUtils.loadEmployees(context);
    }

    @Override
    protected void onPostExecute(List<EmployeeParcelable> employees) {
        super.onPostExecute(employees);
        adapter.setData(employees);
        adapter.notifyDataSetChanged();
    }
}
