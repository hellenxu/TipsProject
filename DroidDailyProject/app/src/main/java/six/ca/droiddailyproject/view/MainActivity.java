package six.ca.droiddailyproject.view;

import android.app.Activity;
import android.os.Bundle;
import six.ca.droiddailyproject.R;


/**
 * Common Activity for view tests.
 * Created by Xiaolin on 2016-05-27.
 */
public class MainActivity extends Activity {
    private ColorfulLoadingView loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.act_circle_menu);
//        CircleMenuViewGroup circleMenu = (CircleMenuViewGroup) findViewById(R.id.cm_sample);
//        setContentView(R.layout.act_gif);
//        PlayGifView playGifView = (PlayGifView) findViewById(R.id.pgContent);
//        playGifView.setMovieResId(R.drawable.bb8);
        setContentView(R.layout.act_color_loading);
        loadingView = ((ColorfulLoadingView)findViewById(R.id.loading));
//        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//        telephonyManager.createForSubscriptionId(TelephonyManager.SIM_STATE_ABSENT);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadingView.start();
    }

    @Override
    protected void onStop() {
        loadingView.stop();
        super.onStop();
    }
}
