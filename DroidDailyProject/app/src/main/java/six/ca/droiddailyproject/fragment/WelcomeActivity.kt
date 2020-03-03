package six.ca.droiddailyproject.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.TextView
import six.ca.droiddailyproject.R
import six.ca.droiddailyproject.SixApplication

/**
 * @author hellenxu
 * @date 2020-03-01
 * Copyright 2020 Six. All rights reserved.
 */
class WelcomeActivity: AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        InfoManager.instance.sharedInt = 1
        InfoManager.instance.sharedInfo = InfoManager.Info("123456")
        StaticInfo.sharedInt = 2
        StaticInfo.sharedInfo = InfoManager.Info("987654")
        SixApplication.sharedInt = 4
        SixApplication.sharedInfo = InfoManager.Info("plokju")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tvWelcome = TextView(this)
        tvWelcome.text = "Welcome"
        tvWelcome.textSize = 22f
        tvWelcome.setOnClickListener {
            startActivity(Intent(this, NestedSampleActivity::class.java))
        }

        setContentView(tvWelcome)
    }
}