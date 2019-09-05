package six.ca.droiddailyproject.webview

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import six.ca.droiddailyproject.R

/**
 * @author hellenxu
 * @date 2019-09-04
 * Copyright 2019 Six. All rights reserved.
 */
class WebViewSample: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.act_wv)

        val web = findViewById<WebView>(R.id.web)
        web.loadUrl("file:///android_asset/register.html")
    }
}