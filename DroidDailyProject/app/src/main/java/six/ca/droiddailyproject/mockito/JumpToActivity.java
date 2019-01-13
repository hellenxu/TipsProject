package six.ca.droiddailyproject.mockito;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import six.ca.droiddailyproject.R;


/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-10-26.
 */

public class JumpToActivity extends Activity {
    public static final String KEY_EXTRA_MESSAGE = "extra_msg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_jump);

        String messg = getIntent().getStringExtra(KEY_EXTRA_MESSAGE);
        ((TextView) findViewById(R.id.tv_content)).setText(messg);
    }

    static Intent newStartIntent(Context ctx, String content){
        Intent newIntent = new Intent(ctx, JumpToActivity.class);
        newIntent.putExtra(KEY_EXTRA_MESSAGE, content);
        return newIntent;
    }
}
