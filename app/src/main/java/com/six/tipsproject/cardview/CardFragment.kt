package com.six.tipsproject.cardview

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.six.tipsproject.R
import com.six.tipsproject.databinding.ItemUserBinding
import kotlinx.android.synthetic.main.act_card.*

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-09-10.
 */
class CardFragment: Fragment() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var userObserver: Observer<User>
    private lateinit var userBinding: ItemUserBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userBinding = ItemUserBinding.inflate(layoutInflater, null, false)

        return inflater.inflate(R.layout.act_card, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userViewModel = ViewModelProviders.of(CardFragment@ this).get(UserViewModel::class.java)

        userObserver = Observer {
            println("xxl-user-onchanged00")
            userBinding.user = it
            userBinding.setLifecycleOwner(CardFragment@this)
            println("xxl-user-onchanged11")
        }

        card_user.removeAllViews()
        card_profile.removeAllViews()

        userViewModel.getUserData().reObserve(CarMainActivity@this, userObserver)
        card_user.addView(userBinding.root)
    }

    override fun onResume() {
        super.onResume()
        println("xxl-onResume00")
        userViewModel.getUserInfo()
    }

    override fun getUserVisibleHint(): Boolean {
        println("xxl-userVisibleHint00")
        return super.getUserVisibleHint()
    }
}