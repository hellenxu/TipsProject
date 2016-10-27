package com.six.tipsproject.mockito;

import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-10-26.
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class JumpToActivityTest {

    @Test
    public void testJump(){
        Intent intent = new Intent();
        intent.putExtra(JumpToActivity.KEY_EXTRA_MESSAGE, "20161026");

        JumpToActivity target =
                Robolectric.buildActivity(JumpToActivity.class)
                .withIntent(intent).visible().get();
        assertNotNull(target);
    }
}
