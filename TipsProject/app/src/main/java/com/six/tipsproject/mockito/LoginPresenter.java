package com.six.tipsproject.mockito;

import android.text.TextUtils;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-10-12.
 */

public class LoginPresenter implements ILoginCallback{
    private UserManager mUserManager = new UserManager();
    String resultCode;
    Throwable exception;

    void login(String username, String password){
        if(TextUtils.isEmpty(username)) return;
        if (password == null || password.length() < 6) return;
        mUserManager.performLogin(username, password, this);
    }

    void setUserManager(UserManager userManager){
        this.mUserManager = userManager;
    }

    @Override
    public void onSuccess(String resp) {
        resultCode = resp;
        System.out.println("xxl: " + resp);
    }

    @Override
    public void onFail(Throwable throwable) {
        this.exception = throwable;
        System.out.println("xxl: " + throwable.toString());
    }
}
