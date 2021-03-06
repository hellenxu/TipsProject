package six.ca.droiddailyproject.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import six.ca.droiddailyproject.R;


/**
 * Created by Xiaolin on 2016-07-21.
 */
public class TouchEventSample extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_touch);

        TouchTextView tvTouch = (TouchTextView) findViewById(R.id.tv_touch);
        tvTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("Listener", "onTouch");
                return false;
            }
        });
    }
}
