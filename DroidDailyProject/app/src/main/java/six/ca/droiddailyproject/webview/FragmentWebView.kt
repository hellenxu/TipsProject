package six.ca.droiddailyproject.webview

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.webkit.WebView
import android.widget.LinearLayout
import android.widget.ScrollView
import kotlinx.android.synthetic.main.frag_wv.*
import six.ca.droiddailyproject.R

/**
 * @author hellenxu
 * @date 2019-09-05
 * Copyright 2019 Six. All rights reserved.
 */
class FragmentWebView: Fragment(), ViewTreeObserver.OnGlobalLayoutListener {
    private var contentView: View? = null
    private var preHeight = 0
    private var originalHeight = 0
    private var keyboardHeight = 0

    override fun onGlobalLayout() {
        val currentHeight = contentView?.height?:0
        if (currentHeight == 0 )
            return

        var hasChange = false
        if (preHeight == 0) {
            preHeight = currentHeight
            originalHeight = currentHeight
        } else {
            if (preHeight != currentHeight) {
                hasChange = true
                preHeight = currentHeight
            } else {
                hasChange = false
            }
        }

        if (hasChange) {
            keyboardHeight = originalHeight - currentHeight
        }
    }

    private lateinit var webView: WebView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.frag_wv, container, false)
        webView = rootView.findViewById(R.id.web)
        webView.loadUrl("file:///android_asset/register.html")
        val paddingView = rootView.findViewById<View>(R.id.paddingView)
        val scrollView = rootView.findViewById<ScrollView>(R.id.sv)

        val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        var layoutParams: LinearLayout.LayoutParams

        webView.isScrollContainer = false
        webView.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP && inputMethodManager.isActive) {
//                layoutParams = LinearLayout.LayoutParams(-1, 0)
                scrollView.smoothScrollTo(event.rawX.toInt(), event.rawY.toInt())
                println("xxl-event: x=${event.rawX}; y=${event.rawY}")
                println("xxl-keyboardHeight: $keyboardHeight")
            } else {
//                layoutParams = LinearLayout.LayoutParams(-1, 0)
            }
//            paddingView.layoutParams = layoutParams
            return@setOnTouchListener false
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentView = activity?.findViewById(R.id.content)
    }
}