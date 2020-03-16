package six.ca.droiddailyproject.fragment

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author hellenxu
 * @date 2020-03-15
 * Copyright 2020 Six. All rights reserved.
 */
class SingleLiveEvent<T>: MutableLiveData<T>() {

    private val mPending = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            println("xxl-multiple observers registered but only one will be notified of changes")
        }

        super.observe(owner, Observer {
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        })
    }

    @MainThread
    override fun setValue(value: T) {
        mPending.set(true)
        super.setValue(value)
    }
}