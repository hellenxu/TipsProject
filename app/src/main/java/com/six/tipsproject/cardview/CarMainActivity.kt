package com.six.tipsproject.cardview

import android.app.Activity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_card)
    }

    override fun onResume() {
        super.onResume()

        card_user.removeAllViews()
        card_profile.removeAllViews()

        val userViewModel = ViewModelProviders.of(CarMainActivity@ this).get(UserViewModel::class.java)
        val userBinding = ItemUserBinding.inflate(layoutInflater, null, false)
        userViewModel.getUserInfo()
        userViewModel.getUserData().observe(CarMainActivity@ this, Observer { user ->
            userBinding.user = user
        })
        card_user.addView(userBinding.root)

        val profileViewModel = ViewModelProviders.of(CarMainActivity@ this).get(ProfileViewModel::class.java)
        val profileBinding = ItemProfileBinding.inflate(layoutInflater, null, false)
        profileViewModel.getProfile()
        profileViewModel.getProfileData().observe(CarMainActivity@ this, Observer { profile ->
            profileBinding.profile = profile
        })
        card_profile.addView(profileBinding.root)
    }
}
