package six.ca.droiddailyproject.eventbus.six;

import android.os.Looper;
import android.os.Message;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import ca.six.util.L;

/**
 * a demo version of EventBus
 * Created by Xiaolin on 2016-06-20.
 */
public class SixEventBus {
    private static SixEventBus singleton = null;
    private MainThreadHandler mainHandler;
    private Map<Class<?>, CopyOnWriteArrayList<SixSubscription>> subscriptionByEventType;
    private static final String EVENT_PREFIX = "onEvent";
    private static final String EVENT_BACKGROUND = "Back";

    public static SixEventBus getInstance() {
        if (singleton == null) {
            synchronized (SixEventBus.class) {
                if (singleton == null) {
                    singleton = new SixEventBus();
                }
            }
        }
        return singleton;
    }

    private SixEventBus() {
        subscriptionByEventType = new HashMap<>();
        mainHandler = new MainThreadHandler(Looper.getMainLooper(), this);
    }

    public synchronized void register(Object subscriber) {
        Class<?> clazz = subscriber.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        CopyOnWriteArrayList<SixSubscription> subscriptions;
        for (Method method : methods) {
            final String methodName = method.getName();
            Class<?>[] paramsTypes = method.getParameterTypes();
            if (paramsTypes != null && paramsTypes.length == 1 && methodName.startsWith(EVENT_PREFIX)) {
                L.d("register: " + paramsTypes[0]);
                final String threadModeName = methodName.substring(EVENT_PREFIX.length());
                SixThreadMode threadMode = SixThreadMode.Main;
                L.d("register: threadMode = " + threadMode);
                if (!threadModeName.equals(EVENT_BACKGROUND)) {
                    threadMode = SixThreadMode.Back;
                }
                final Class<?> event = paramsTypes[0];
                subscriptions = subscriptionByEventType.get(event);
                if (subscriptions == null) {
                    subscriptions = new CopyOnWriteArrayList<>();
                    subscriptionByEventType.put(event, subscriptions);
                } else {
                    SixSubscription newSubscription = new SixSubscription(subscriber, threadMode,
                            method, event);
                    if (subscriptions.contains(newSubscription)) {
                        throw new RuntimeException("already register to SixEventBus...");
                    } else {
                        subscriptions.add(newSubscription);
                    }
                }
            }
        }

    }

    public void post(Object event) {
        L.d("post...beginning" + event);
        if (event == null) {
            L.d("post...event null");
            throw new IllegalArgumentException("post error: null event");
        }
        final Class<?> eventType = event.getClass();
        CopyOnWriteArrayList<SixSubscription> subscriptions = subscriptionByEventType.get(eventType);
        if (subscriptions != null) {
            boolean isMainThread = Looper.getMainLooper() == Looper.myLooper();
            for (SixSubscription subscription : subscriptions) {
                L.d("post...");
                postSingleEvent(subscription, isMainThread);
            }
        } else {
            L.d("post...subscriptions null");
        }
    }

    private void postSingleEvent(final SixSubscription subscription, boolean isMainThread) {
        switch (subscription.threadMode) {
            case Main:
                if (isMainThread) {
                    L.d("postSingleEvent...");
                    invokeSubscriber(subscription.subscriber, subscription.eventMethod, subscription.event);
                } else {
                    Message msg = new Message();
                    msg.obj = subscription;
                    mainHandler.sendMessage(msg);
                }
                break;
            case Back:
                if (isMainThread) {
                    new Thread() {
                        @Override
                        public void run() {
                            invokeSubscriber(subscription.subscriber, subscription.eventMethod, subscription.event);
                        }
                    }.start();
                } else {
                    invokeSubscriber(subscription.subscriber, subscription.eventMethod, subscription.event);
                }
                break;
            default:
                throw new RuntimeException("invalid threadMode...");
        }
    }

    void invokeSubscriber(Object subscriber, Method eventMethod, Object event) {
        try {
            eventMethod.invoke(subscriber, event);
            L.d("invoke Subscriber..." + event);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("illegal access", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("invocation error", e);
        }
    }

    public void unregister(Object subscriber) {
        Class<?> clazz = subscriber.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(EVENT_PREFIX)) {
                Class<?>[] paramsTypes = method.getParameterTypes();
                if (paramsTypes.length == 1 && subscriptionByEventType.containsKey(paramsTypes[0])) {
                    synchronized (this) {
                        subscriptionByEventType.remove(paramsTypes[0]);
                    }
                }
            }
        }
    }
}
