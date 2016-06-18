package com.six.tipsproject.eventbus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.six.tipsproject.R;

import de.greenrobot.event.EventBus;


/**
 * Created by Xiaolin on 2016-06-17.
 */
public class ItemDetailFragment extends Fragment{
    private TextView tvDetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View detailView = inflater.inflate(R.layout.frag_detail, container, false);
        tvDetail = (TextView) detailView.findViewById(R.id.tv_detail);
        return detailView;
    }

    public void onEventMainThread(Item item){
        if(item != null){
            tvDetail.setText(item.content);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
