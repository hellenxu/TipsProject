package six.ca.droiddailyproject.webview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import six.ca.droiddailyproject.R

/**
 * @author hellenxu
 * @date 2019-09-05
 * Copyright 2019 Six. All rights reserved.
 */
class FragmentWebView: Fragment() {
    private lateinit var webView: WebView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.frag_wv, container, false)
        webView = rootView.findViewById(R.id.web)
        webView.loadUrl("file:///android_asset/register.html")
        return rootView
    }
}