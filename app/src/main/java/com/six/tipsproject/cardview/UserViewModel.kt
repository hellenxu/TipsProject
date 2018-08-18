package com.six.tipsproject.cardview

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-08-16.
 */
class UserViewModel: ViewModel() {
    private val userData = MutableLiveData<User>()

    fun getUserInfo() {
        val user = User("test00", "50")
        userData.postValue(user)
    }

    fun getUserData(): LiveData<User> {
        return userData
    }
}