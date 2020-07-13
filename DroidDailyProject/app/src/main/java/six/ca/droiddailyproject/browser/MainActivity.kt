package six.ca.droiddailyproject.browser

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.browser.customtabs.CustomTabsIntent
import six.ca.droiddailyproject.R

/**
 * @author hellenxu
 * @date 2020-07-13
 * Copyright 2020 Six. All rights reserved.
 */
class MainActivity: AppCompatActivity(R.layout.act_browser), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<AppCompatButton>(R.id.btn_custom_tab).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_custom_tab -> {
                val builder = CustomTabsIntent.Builder()
                val customIntent = builder.build()
                customIntent.launchUrl(this, Uri.parse("https://www.google.ca/"))
            }
        }
    }

}