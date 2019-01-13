package six.ca.droiddailyproject.mockito;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import six.ca.droiddailyproject.BuildConfig;

import static junit.framework.Assert.assertEquals;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-09.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class LifeCycleTest {
    private LifeCycleActivity actLifeCycle;
    private ActivityController<LifeCycleActivity> actvController;

    @Before
    public void setUp(){
        actvController =
                Robolectric.buildActivity(LifeCycleActivity.class);
        actLifeCycle = actvController.get();
    }

    @Test
    public void testOnStart(){
        actvController.start();
        assertEquals("onStart", actLifeCycle.stage);
    }

    @Test
    public void testOnCreate(){
        actvController.create();
        assertEquals("onCreate", actLifeCycle.stage);
    }

    @Test
    public void testOnResume(){
        actvController.resume();
        assertEquals("onResume", actLifeCycle.stage);
    }

    @Test
    public void testOnPause(){
        actvController.pause();
        assertEquals("onPause", actLifeCycle.stage);
    }

    @Test
    public void testOnDestroy(){
        actvController.destroy();
        assertEquals("onDestroy", actLifeCycle.stage);
    }
}
