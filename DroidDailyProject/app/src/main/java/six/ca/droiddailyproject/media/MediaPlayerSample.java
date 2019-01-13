package six.ca.droiddailyproject.media;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import six.ca.droiddailyproject.R;


/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-12-15.
 */

public class MediaPlayerSample extends Activity {
    private ListView lvPlayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_media);

        lvPlayList = (ListView) findViewById(R.id.lv_play_list);

    }
}
