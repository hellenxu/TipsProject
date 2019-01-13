package six.ca.droiddailyproject.eventbus.six;

import java.lang.reflect.Method;

/**
 * Created by Xiaolin on 2016-06-20.
 */
public class SixSubscription {
    public Object subscriber;
    public SixThreadMode threadMode;
    public Method eventMethod;
    public Object event;

    public SixSubscription(Object subscriber, SixThreadMode threadMode, Method method, Object event){
        this.subscriber = subscriber;
        this.threadMode = threadMode;
        this.eventMethod = method;
        this.event = event;
    }
}
