package com.six.tipsproject.mockito;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-10-12.
 */

public interface ILoginCallback {

    void onSuccess(String resp);
    void onFail(Throwable throwable);
}
