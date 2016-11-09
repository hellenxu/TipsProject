package com.six.tipsproject.plastic;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.six.tipsproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xiaolin on 2016-07-22.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder>{
    public List<EmployeeParcelable> employeeList = new ArrayList<>();
    private Context ctx;
    private LayoutInflater inflater;

    public HomeAdapter(Context ctx, List<EmployeeParcelable> data){
        this.ctx = ctx;
        employeeList = data;
        inflater = LayoutInflater.from(ctx);
    }

    public void setData(List<EmployeeParcelable> data){
        employeeList = data;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeViewHolder(inflater.inflate(R.layout.item_home, null));
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        holder.item.setText(employeeList.get(position).name);
        Drawable icon = DataUtils.getAvatar(ctx, employeeList.get(position).picture);
        holder.icon.setImageDrawable(icon);
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder{
        TextView item;
        ImageView icon;

        public HomeViewHolder(View itemView) {
            super(itemView);
            item = (TextView) itemView.findViewById(R.id.tv_name);
            icon = (ImageView) itemView.findViewById(R.id.iv_avatar_left);
        }
    }
}
