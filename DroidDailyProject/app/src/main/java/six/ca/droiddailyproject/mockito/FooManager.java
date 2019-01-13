package six.ca.droiddailyproject.mockito;

import android.os.Bundle;

/**
 * for singleton unit tests.
 * @copyright six.ca
 * Created by Xiaolin on 2016-10-16.
 */

public class FooManager {
    private static FooManager instance;

    private FooManager() {

    }

    public static FooManager getInstance() {
        if (instance == null) {
            synchronized (FooManager.class) {
                if (instance == null) {
                    instance = new FooManager();
                }
            }
        }
        return instance;
    }

    public void receiveMsg(Bundle data){
        System.out.println("xxl: data = " + data);
    }
}
