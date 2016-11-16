package com.six.tipsproject.mockito;

import android.os.Bundle;

import com.six.tipsproject.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-15.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class SampleServiceTest {
    private SampleService service;
    private Bundle data;

    private static final String BUNDLE_KEY = "serviceKey";

    @Before
    public void setUp(){
        data = mock(Bundle.class);
        data.putString(BUNDLE_KEY, "roro");

        service = Robolectric.setupService(SampleService.class);
        service.onMessageReceived("34", data);
    }

    @Test
    public void testOnMessageReceivedId(){
        assertEquals("34", service.pushId);
    }

    @Test
    public void testOnMessageReceivedBundle(){
        verify(data).getString(BUNDLE_KEY);
    }
}
