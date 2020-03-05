package six.ca.droiddailyproject.cardview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-08-16.
 */
class ProfileViewModel: ViewModel() {
    private val profileData = MutableLiveData<Profile>()

    fun getProfile() {
        val profile = Profile("2011-09-20", "5000")
        profileData.postValue(profile)
    }

    fun getProfileData(): LiveData<Profile> {
        return profileData
    }
}