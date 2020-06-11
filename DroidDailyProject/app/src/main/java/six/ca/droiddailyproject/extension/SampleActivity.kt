package six.ca.droiddailyproject.extension

import android.app.Activity
import android.content.Context
import android.os.Bundle

/**
 * @author hellenxu
 * @date 2020-06-10
 * Copyright 2020 Six. All rights reserved.
 */
class SampleActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sp = getSharedPreferences("six_test", Context.MODE_PRIVATE)
        sp.edit(true) {
            putBoolean("sp_work", true)
            putString("sp_string", "hhhh")
            true
        }
    }
}