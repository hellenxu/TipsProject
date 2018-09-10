package com.six.tipsproject.cardview

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.six.tipsproject.R
import com.six.tipsproject.databinding.ItemProfileBinding
import com.six.tipsproject.databinding.ItemUserBinding
import kotlinx.android.synthetic.main.act_card.*

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-08-16.
 */
class CarMainActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var userObserver: Observer<User>
    private lateinit var userBinding: ItemUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_card)

        userBinding = ItemUserBinding.inflate(layoutInflater, null, false)
        userViewModel = ViewModelProviders.of(CarMainActivity@ this).get(UserViewModel::class.java)

        userObserver = Observer {
            println("xxl-user-onchanged00")
            userBinding.user = it
            userBinding.setLifecycleOwner(CarMainActivity@this)
            println("xxl-user-onchanged11")
        }
    }

    override fun onStart() {
        super.onStart()

        card_user.removeAllViews()
        card_profile.removeAllViews()

        userViewModel.getUserData().reObserve(CarMainActivity@this, userObserver)
        card_user.addView(userBinding.root)

//        val profileViewModel = ViewModelProviders.of(CarMainActivity@ this).get(ProfileViewModel::class.java)
//        val profileBinding = ItemProfileBinding.inflate(layoutInflater, null, false)
//        profileViewModel.getProfileData().observe(CarMainActivity@ this, Observer { profile ->
//            println("xxl-profile-onchanged00")
//            profileBinding.profile = profile
//            println("xxl-profile-onchanged11")
//        })
//        card_profile.addView(profileBinding.root)

        userViewModel.getUserInfo()
//        profileViewModel.getProfile()
    }
}

fun  <T> LiveData<T>.reObserve(owner: LifecycleOwner, observer: Observer<T>) {
    removeObserver(observer)
    observe(owner, observer)
}