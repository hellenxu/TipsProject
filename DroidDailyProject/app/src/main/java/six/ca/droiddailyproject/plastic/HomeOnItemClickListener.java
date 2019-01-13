package six.ca.droiddailyproject.plastic;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Xiaolin on 2016-07-22.
 */
public abstract class HomeOnItemClickListener implements RecyclerView.OnItemTouchListener {
    private RecyclerView rv;
    private GestureDetectorCompat gestureDetector;

    public HomeOnItemClickListener(RecyclerView rv){
        this.rv = rv;
        gestureDetector = new GestureDetectorCompat(rv.getContext(), new HomeGesListener());
    }

    private class HomeGesListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if(child != null){
                RecyclerView.ViewHolder vh = rv.getChildViewHolder(child);
                onItemClick(vh);
            }
            return true;
        }
    }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetector.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public abstract void onItemClick(RecyclerView.ViewHolder vh);

}
