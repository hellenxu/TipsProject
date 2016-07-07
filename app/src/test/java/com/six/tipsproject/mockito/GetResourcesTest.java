package com.six.tipsproject.mockito;

import android.content.Context;

import com.six.tipsproject.R;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by Xiaolin on 2016-07-06.
 */

@RunWith(MockitoJUnitRunner.class)
public class GetResourcesTest {
    private static final String FAKE_STRING = "HELLO_WORLD";

    @Mock
    Context mockContext;

    @Test
    public void testGetString() throws Exception {
        when(mockContext.getString(R.string.hello_word))
                .thenReturn(FAKE_STRING);
        GetResources resGetter = new GetResources(mockContext);
        String result = resGetter.getString(R.string.hello_word);
        assertThat(result, is(FAKE_STRING));
    }
}