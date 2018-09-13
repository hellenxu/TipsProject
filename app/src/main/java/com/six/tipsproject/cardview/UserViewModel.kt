package com.six.tipsproject.cardview

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-08-16.
 */
class UserViewModel : ViewModel() {
    private val userData = MutableLiveData<User>()
    private var user: User? = null
    private var oldUser: User? = null

    init {
        if (user == null) {
            user = User("test00", "50")
        }
    }

    fun getUserInfo() {

        val thread = Thread(Runnable {
            Thread.sleep(5000)
            if(!user?.equals(oldUser)!!) {
                oldUser = user
                userData.postValue(user)
            }
        })

        thread.start()
    }

    fun getUserData(): LiveData<User> {
        return userData
    }
}