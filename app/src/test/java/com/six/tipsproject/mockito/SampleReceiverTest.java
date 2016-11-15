package com.six.tipsproject.mockito;

import android.content.Intent;
import android.content.IntentFilter;

import com.six.tipsproject.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-14.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class SampleReceiverTest {

    private SampleReceiver receiver;
    private Intent intent;

    @Before
    public void setUp(){
        this.receiver = new SampleReceiver();
        IntentFilter filter = new IntentFilter("ca.six.sample.broadcast");
        ShadowApplication.getInstance().registerReceiver(this.receiver, filter);

        this.intent = new Intent("ca.six.sample.broadcast");
        RuntimeEnvironment.application.sendBroadcast(this.intent);
    }

    @Test
    public void testRegister(){
        assertTrue(ShadowApplication.getInstance().hasReceiverForIntent(this.intent));
    }

    @Test
    public void testOnReceive(){
        assertEquals(10, receiver.value);
    }
}
