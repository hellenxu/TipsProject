package six.ca.droiddailyproject.eventbus;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import six.ca.droiddailyproject.R;


/**
 * A sample about using EventBus
 * Created by Xiaolin on 2016-06-17.
 */
public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_event_bus);
    }
}