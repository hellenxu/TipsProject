package six.ca.droiddailyproject.browser

import android.content.Intent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import six.ca.droiddailyproject.R

/**
 * @author hellenxu
 * @date 2020-07-13
 * Copyright 2020 Six. All rights reserved.
 */
class SettingsActivity: AppCompatActivity(R.layout.act_settings) {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        findViewById<TextView>(R.id.source).text = "$requestCode"
    }

    companion object {
        const val REQUEST_CODE_FROM_BROWSER = 400
    }
}