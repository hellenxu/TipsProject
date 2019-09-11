package six.ca.droiddailyproject.issues.webview

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.DisplayMetrics
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.webkit.WebView
import android.widget.ScrollView
import six.ca.droiddailyproject.R

/**
 * @author hellenxu
 * @date 2019-09-05
 * Copyright 2019 Six. All rights reserved.
 */
class FragmentWebView: Fragment(), ViewTreeObserver.OnGlobalLayoutListener {
    private lateinit var webView: WebView
    private lateinit var scrollView: ScrollView
    private var clickY = 0
    private var clickX = 0
    private lateinit var inputMethodManager: InputMethodManager

    override fun onGlobalLayout() {
        println("xxl-onGlobalLayout00")
        val dm = DisplayMetrics()
        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(dm)
        val screenHeight = dm.heightPixels

        val visibleRect = Rect()
        activity?.window?.decorView?.getWindowVisibleDisplayFrame(visibleRect)
        val visibleHeight = visibleRect.bottom - visibleRect.top

        val heightOffset = screenHeight - visibleHeight
        if (heightOffset > 0.2 * screenHeight
            && inputMethodManager.isActive
            && !inVisibleRect(visibleRect, clickX, clickY)) {
            // move extra 60 pixels upwards to make sure the whole view is visible
            scrollView.smoothScrollBy(0, clickY - visibleRect.bottom + 60)
        }
    }

    private fun inVisibleRect(rect: Rect, x: Int, y: Int): Boolean {
        return rect.contains(x, y) && y > rect.bottom
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.frag_wv, container, false)
        webView = rootView.findViewById(R.id.web)
        webView.loadUrl("file:///android_asset/register.html")
        scrollView = rootView.findViewById(R.id.sv)

        webView.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP && inputMethodManager.isActive) {
                println("xxl-event: x=${event.rawX}; y=${event.rawY}")
                clickY = event.rawY.toInt()
                println("xxl-clickY: $clickY")
            }
            return@setOnTouchListener false
        }

        inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.decorView?.viewTreeObserver?.addOnGlobalLayoutListener(this)
    }

    override fun onDestroyView() {
        activity?.window?.decorView?.viewTreeObserver?.removeOnGlobalLayoutListener(this)
        super.onDestroyView()
    }
}