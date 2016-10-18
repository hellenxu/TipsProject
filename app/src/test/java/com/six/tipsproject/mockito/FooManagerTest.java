package com.six.tipsproject.mockito;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-10-16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(FooManager.class)
public class FooManagerTest {

    @Test
    public void testSingleton(){
        FooManager fooManager = Mockito.mock(FooManager.class);
        PowerMockito.mockStatic(FooManager.class);
        Mockito.when(FooManager.getInstance())
                .thenReturn(fooManager);
        FooManager realManager = FooManager.getInstance();
        Assert.assertEquals(fooManager, realManager);
    }
}
