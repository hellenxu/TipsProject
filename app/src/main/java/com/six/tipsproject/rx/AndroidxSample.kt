package com.six.tipsproject.rx

import android.app.Activity
import android.os.Bundle
import com.jakewharton.rxbinding2.view.RxView
import com.six.tipsproject.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function3
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

        accessMultiApiZip()
    }

    private fun initViews() {
        RxView.clicks(doubleBtn)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe {
                    println("xxl-click double click btn: ${System.currentTimeMillis()}")
                }
                .disposedBy(compositeDisposable)
    }

//    Merge results of multiple apis but subscribe method will be called more than one time.
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

// Zip will combine all results and then call subscribe method after that.
// It can be used in loading page info by using multiple apis and then show results at the same time.
    private fun accessMultiApiZip() {
        val apiBanner = Observable.create<String> {
            Thread.sleep(1000)
            it.onNext("result of loading banner")
        }

        val apiBody = Observable.create<String> {
            Thread.sleep(1500)
            it.onNext("result of loading body")
        }

        val apiFooter = Observable.create<String> {
            Thread.sleep(2500)
            it.onNext("resulto of loading footer")
        }

        Observable.zip(apiBanner, apiBody, apiFooter, Function3<String, String, String, Array<String>> { title, body, footer ->
            arrayOf(title, body, footer)
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    buildCard(it)
                }
                .disposedBy(compositeDisposable)

    }

    private fun buildCard(result: Array<String>){
        if(result.size != 3) {
            return
        }

        tvTitle.text = result[0]
        tvBody.text = result[1]
        tvFooter.text = result[2]
    }
}

fun Disposable.disposedBy(manager: CompositeDisposable) {
    manager.add(this)
}