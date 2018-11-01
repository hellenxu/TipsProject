package com.six.tipsproject.rx

import android.app.Activity
import android.os.Bundle
import com.jakewharton.rxbinding2.view.RxView
import com.six.tipsproject.R
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.act_rx.*
import java.util.concurrent.TimeUnit

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-11-01.
 */
class AndroidxSample: Activity() {
    private lateinit var clickDisposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.act_rx)

        initViews()
    }

    private fun initViews() {
        clickDisposable = RxView.clicks(doubleBtn)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe {
                    println("xxl-click double click btn: ${System.currentTimeMillis()}")
                }
    }

    override fun onStop() {
        super.onStop()
        clickDisposable.dispose()
    }
}