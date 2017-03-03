package com.six.tipsproject.memory;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2017-03-02.
 */

public class BaseActivity extends Activity {

    private static boolean hasTouch;
    private static Handler actionHandler = new ActionHandler();

    private static final int INTERVAL = 5 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionHandler.sendEmptyMessageDelayed(0, INTERVAL);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        resetActionHandler();
        return super.dispatchTouchEvent(ev);
    }

    private void resetActionHandler(){
        hasTouch = true;
        actionHandler.sendEmptyMessageDelayed(0, INTERVAL);
    }

    @Override
    protected void onStop() {
        super.onStop();
        actionHandler.removeMessages(0); // to reset timer
    }

    private static class ActionHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (!hasTouch) {
                Session.getSession().logout();
            } else {
                sendEmptyMessageDelayed(0, INTERVAL);
            }
            hasTouch = false;
        }
    }
}
