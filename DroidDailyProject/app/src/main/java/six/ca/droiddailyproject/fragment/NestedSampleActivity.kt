package six.ca.droiddailyproject.fragment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import six.ca.droiddailyproject.R

/**
 * @author hellenxu
 * @date 2020-02-13
 * Copyright 2020 Six. All rights reserved.
 */
class NestedSampleActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.act_nested_frag)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LevelOneFragment())
                .commit()
        }
    }
}