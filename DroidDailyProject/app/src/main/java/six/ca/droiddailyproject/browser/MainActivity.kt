package six.ca.droiddailyproject.browser

import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
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
                // custom settings: toolbar color, action button and corresponding intent
                val settingsPendingIntent =
                    PendingIntent.getActivity(
                        this,
                        SettingsActivity.REQUEST_CODE_FROM_BROWSER,
                        Intent(this, SettingsActivity::class.java),
                        PendingIntent.FLAG_ONE_SHOT and PendingIntent.FLAG_NO_CREATE
                    )
                val customIntent =
                    builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
                        .setActionButton(
                            BitmapFactory.decodeResource(resources, R.drawable.settings),
                            getString(R.string.settings),
                            settingsPendingIntent
                        )
                        .addMenuItem(getString(R.string.settings), settingsPendingIntent)
                        .setStartAnimations(this, R.anim.slide_in_right, 0)
                        .build()
                customIntent.launchUrl(this, Uri.parse("https://www.google.ca/"))
            }
        }
    }

}