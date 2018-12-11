package com.six.tipsproject.mockito;

import com.six.tipsproject.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotEquals;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, shadows = {ShadowPerson.class})
public class CustomShadowTest {

    @Test
    public void customShadow(){
        Person xxl = new Person("xxl");
        assertNotEquals("name = xxl", xxl.getPersonInfo());
//        assertEquals("mockInformation", xxl.getPersonInfo());
    }

    @Test
    public void shadowItSelf(){

    }
}
