package six.ca.droiddailyproject.cardview

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.act_card.*
import six.ca.droiddailyproject.R
import six.ca.droiddailyproject.databinding.ItemUserBinding

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-09-10.
 */
class CardFragment: androidx.fragment.app.Fragment() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var userObserver: Observer<User>
    private lateinit var userBinding: ItemUserBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userBinding = ItemUserBinding.inflate(layoutInflater, null, false)

        return inflater.inflate(R.layout.act_card, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        userObserver = Observer {
            println("xxl-user-onchanged00$userObserver")
            userBinding.user = it
            userBinding.setLifecycleOwner(this)
            println("xxl-user-onchanged11")
        }

        card_user.removeAllViews()
        card_profile.removeAllViews()

        userViewModel.getUserData().observe(this, userObserver)
        card_user.addView(userBinding.root)
    }

    override fun onResume() {
        super.onResume()
        println("xxl-onResume00")
        userViewModel.getUserInfo()
    }

    override fun onStart() {
        super.onStart()
        println("xxl-onStart00")
    }

    override fun getUserVisibleHint(): Boolean {
        println("xxl-userVisibleHint00")
        return super.getUserVisibleHint()
    }
}