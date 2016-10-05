package com.six.tipsproject.mockito;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-10-04.
 */
public class MockitoHelloWorldTest {
    private MockitoHelloWorld mockitoHelloWorld;

    @Before
    public void setUp(){
        mockitoHelloWorld = new MockitoHelloWorld();
    }

    @Test
    public void add() throws Exception {
        int result = mockitoHelloWorld.add(1, 3);
        assertEquals(4, result);
    }

    @Test
    public void print() throws Exception {
        MyString mockMyStr = mock(MyString.class);
        mockitoHelloWorld.print(mockMyStr);
        verify(mockMyStr).print();
    }

    @Test(expected = IllegalStateException.class)
    public void divide(){
        MockitoHelloWorld instance = mock(MockitoHelloWorld.class);
        when(instance.divide(10, 2))
                .thenThrow(new IllegalStateException("error"));
        instance.divide(10, 2);
    }

}