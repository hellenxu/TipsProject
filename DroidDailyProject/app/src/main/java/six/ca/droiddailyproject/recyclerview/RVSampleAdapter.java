package six.ca.droiddailyproject.recyclerview;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import six.ca.droiddailyproject.R;


import java.util.ArrayList;
import java.util.List;

/**
 * Sample Adapter
 * Created by Xiaolin on 2016-06-10.
 */
public class RVSampleAdapter extends RecyclerView.Adapter<RVSampleAdapter.SampleHolder> {
    private List<String> data = new ArrayList<>();
    private LayoutInflater mInflater;

    public RVSampleAdapter(Context context, List<String> data) {
        mInflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void setData(ArrayList<String> data) {
        if (data != null) {
            this.data = data;
            notifyDataSetChanged();
        }
    }

    @Override
    public SampleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SampleHolder(mInflater.inflate(R.layout.item_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(SampleHolder holder, int position) {
        holder.tvTitle.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class SampleHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public SampleHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }
}
