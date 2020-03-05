package six.ca.droiddailyproject.cardview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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