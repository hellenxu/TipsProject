package six.ca.droiddailyproject.cardview

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import six.ca.droiddailyproject.R

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-08-16.
 */
class CarMainActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var userObserver: Observer<User>
//    private lateinit var userBinding: ItemUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_card_main)

//        userBinding = ItemUserBinding.inflate(layoutInflater, null, false)
//        userViewModel = ViewModelProviders.of(CarMainActivity@ this).get(UserViewModel::class.java)
//
//        userObserver = Observer {
//            println("xxl-user-onchanged00")
//            userBinding.user = it
//            userBinding.setLifecycleOwner(CarMainActivity@this)
//            println("xxl-user-onchanged11")
//        }

        supportFragmentManager.beginTransaction().replace(R.id.container, CardFragment()).commit()
    }

//    override fun onStart() {
//        super.onStart()
//        println("xxl-onStart00")
//        card_user.removeAllViews()
//        card_profile.removeAllViews()
//
//        userViewModel.getUserData().reObserve(CarMainActivity@this, userObserver)
//        card_user.addView(userBinding.root)
//
//        val profileViewModel = ViewModelProviders.of(CarMainActivity@ this).get(ProfileViewModel::class.java)
//        val profileBinding = ItemProfileBinding.inflate(layoutInflater, null, false)
//        profileViewModel.getProfileData().observe(CarMainActivity@ this, Observer { profile ->
//            println("xxl-profile-onchanged00")
//            profileBinding.profile = profile
//            println("xxl-profile-onchanged11")
//        })
//        card_profile.addView(profileBinding.root)
//
//        userViewModel.getUserInfo()
//        profileViewModel.getProfile()
//        println("xxl-onStart11")
//    }

//    override fun onResume() {
//        super.onResume()
//        println("xxl-onResume00")
//    }
}

fun  <T> LiveData<T>.reObserve(owner: LifecycleOwner, observer: Observer<T>) {
    println("xxl-reObserve00-$observer")
    println("xxl-reObserve00-$owner")
    removeObserver(observer)
    observe(owner, observer)
}