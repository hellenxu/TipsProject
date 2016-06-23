package com.six.tipsproject.eventbus;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.six.tipsproject.eventbus.Event.*;
import com.six.tipsproject.eventbus.six.SixEventBus;


/**
 * Created by Xiaolin on 2016-06-17.
 */
public class ItemListFragment extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EventBus.getDefault().register(this);
        SixEventBus.getInstance().register(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Thread(){
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
//                    EventBus.getDefault().post(new ItemListEvent(Item.items));
                    SixEventBus.getInstance().post(new Event.ItemListEvent(Item.items));
                }catch (InterruptedException e){
                    System.out.println("error: " + e.toString());
                }
            }
        }.start();
    }

//    public void onEventMainThread(ItemListEvent event){
//        setListAdapter(new ArrayAdapter<>(getActivity(),
//                android.R.layout.simple_list_item_activated_2,
//                android.R.id.text1, event.getItems()));
//    }

    public void onEventMain(ItemListEvent event){
        setListAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_activated_2,
                android.R.id.text1, event.getItems()));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
//        EventBus.getDefault().post(getListView().getItemAtPosition(position));
        SixEventBus.getInstance().post(getListView().getItemAtPosition(position));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
        SixEventBus.getInstance().unregister(this);
    }
}
