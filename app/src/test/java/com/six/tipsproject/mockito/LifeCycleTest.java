package com.six.tipsproject.mockito;

import com.six.tipsproject.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static junit.framework.Assert.assertEquals;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-09.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class LifeCycleTest {

    @Test
    public void testLifeCycle(){
        ActivityController<LifeCycleActivity> actvController =
                Robolectric.buildActivity(LifeCycleActivity.class).create();
        LifeCycleActivity actLifeCycle = actvController.get();

        actvController.start();
        assertEquals("onCreate", actLifeCycle.stage);
    }
}
