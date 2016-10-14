package com.six.tipsproject.mockito;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-10-12.
 */
public class LoginPresenterTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    UserManager mockUserManager;

    @Captor
    private ArgumentCaptor<ILoginCallback> loginCallbackCaptor;

    private LoginPresenter loginPresenter = new LoginPresenter();

    @Test
    public void login() {
        loginPresenter.setUserManager(mockUserManager);

        loginPresenter.login("xxl", "xxl jia you");
        Mockito.verify(mockUserManager).performLogin("xxl", "xxl jia you", loginPresenter);
    }

    @Test
    public void onSuccess(){
        loginPresenter.setUserManager(mockUserManager);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                ILoginCallback callback = (ILoginCallback) arguments[2];
                callback.onSuccess("mock success!");
                return arguments;
            }
        })
                .when(mockUserManager)
                .performLogin(anyString(), anyString(), any(ILoginCallback.class));

        loginPresenter.login("xxl", "xxl jia you");
        Assert.assertEquals("mock success!", loginPresenter.resultCode);

    }

    @Test
    public void onSuccess2(){
        loginPresenter.setUserManager(mockUserManager);
        loginPresenter.login("xxl", "xxl111");
        verify(mockUserManager)
                .performLogin(anyString(), anyString(), loginCallbackCaptor.capture());

        loginCallbackCaptor.getValue().onSuccess("xxl success");
        Assert.assertEquals("xxl success", loginPresenter.resultCode);
    }

    @Test
    public void onFail(){
        loginPresenter.setUserManager(mockUserManager);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                ILoginCallback callback = (ILoginCallback) arguments[2];
                callback.onFail(new Throwable("login failed"));
                return arguments;
            }
        })
                .when(mockUserManager)
                .performLogin(anyString(), anyString(), any(ILoginCallback.class));

        loginPresenter.login("xxl", "xxljiayou");
        Assert.assertEquals("login failed", loginPresenter.exception.getMessage());
    }

}