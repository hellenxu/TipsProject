package com.six.tipsproject.view;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.Collections;
import java.util.List;

import ca.six.util.L;

/**
 * Created by Xiaolin on 2016-06-11.
 */
public class SampleItemCallback extends ItemTouchHelper.Callback {
    private List data;
    private RecyclerView.Adapter adapter;

    public SampleItemCallback(List data, RecyclerView.Adapter adapter) {
        this.data = data;
        this.adapter = adapter;
    }

    //when swipeFlags = 0, we ignore swiping event.
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags;
        int swipeFlags = 0;
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                    ItemTouchHelper.START | ItemTouchHelper.END;
        } else {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            swipeFlags = ItemTouchHelper.END;
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPos = viewHolder.getAdapterPosition();
        int toPos = target.getAdapterPosition();
        if (fromPos < toPos) {
            for (int i = fromPos; i < toPos; i++) {
                Collections.swap(data, i, i + 1);
            }
        } else {
            for (int i = fromPos; i > toPos; i--) {
                Collections.swap(data, i, i - 1);
            }
        }
        adapter.notifyItemMoved(fromPos, toPos);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int pos = viewHolder.getAdapterPosition();
        data.remove(pos);//call an asynchronous task to deal with deleting data in server or something like that.
        adapter.notifyItemRemoved(pos);
        L.d("swiped: " + pos);
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if(actionState != ItemTouchHelper.ACTION_STATE_IDLE){
            viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(0);
    }
}
