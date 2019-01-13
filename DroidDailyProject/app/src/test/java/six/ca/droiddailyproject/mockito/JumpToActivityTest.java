package six.ca.droiddailyproject.mockito;

import android.content.Intent;
import android.widget.TextView;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import six.ca.droiddailyproject.BuildConfig;
import six.ca.droiddailyproject.R;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-10-26.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, manifest=Config.NONE, sdk = 21)
public class JumpToActivityTest {
    private JumpToActivity target;
    private TextView tvContent;

    @Before
    public void setUp(){
        Intent intent = new Intent();
        intent.putExtra(JumpToActivity.KEY_EXTRA_MESSAGE, "20161026");

        target = Robolectric.buildActivity(JumpToActivity.class).withIntent(intent).create().get();
        tvContent = (TextView) target.findViewById(R.id.tv_content);
    }

    @Test
    public void testJumpSuc(){
        assertNotNull(target);
    }

    @Test
    public void testText(){
        assertNotNull(tvContent);
        assertEquals("20161026", tvContent.getText());
    }
}
