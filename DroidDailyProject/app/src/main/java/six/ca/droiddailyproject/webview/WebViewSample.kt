package six.ca.droiddailyproject.webview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import six.ca.droiddailyproject.R

/**
 * @author hellenxu
 * @date 2019-09-04
 * Copyright 2019 Six. All rights reserved.
 */
class WebViewSample: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_wv)

        val fragment = FragmentWebView()
        supportFragmentManager.beginTransaction().replace(R.id.frag_container, fragment).commit()
    }
}