package six.ca.droiddailyproject.eventbus.six;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by Xiaolin on 2016-06-21.
 */
public class MainThreadHandler extends Handler {
    private SixEventBus eventBus;

    public MainThreadHandler(Looper looper, SixEventBus eventBus){
        super(looper);
        this.eventBus = eventBus;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        SixSubscription subscription = (SixSubscription) msg.obj;
        if(subscription != null){
            eventBus.invokeSubscriber(subscription.subscriber, subscription.eventMethod, subscription.event);
        }
    }
}
