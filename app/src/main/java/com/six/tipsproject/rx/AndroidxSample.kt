package com.six.tipsproject.rx

import android.app.Activity
import android.os.Bundle
import com.jakewharton.rxbinding2.view.RxView
import com.six.tipsproject.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.act_rx.*
import java.util.concurrent.TimeUnit

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-11-01.
 */
class AndroidxSample: Activity() {
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.act_rx)

        initViews()

        accessMultiApi()
    }

    private fun initViews() {
        RxView.clicks(doubleBtn)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe {
                    println("xxl-click double click btn: ${System.currentTimeMillis()}")
                }
                .disposedBy(compositeDisposable)
    }

    private fun accessMultiApi() {
        val apiOne = Observable.create<String> {
            Thread.sleep(2000)
            it.onNext("access api one")
        }

        val apiTwo = Observable.create<String> {
            Thread.sleep(3000)
            it.onNext("access api two")
        }

        Observable.merge(apiOne, apiTwo)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    println("xxl-result fromp api: $it")
                    tvResult.text = String.format("%s %s", tvResult.text, it)
                }
                .disposedBy(compositeDisposable)
    }
}

fun Disposable.disposedBy(manager: CompositeDisposable) {
    manager.add(this)
}