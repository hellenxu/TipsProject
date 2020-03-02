package six.ca.droiddailyproject.fragment

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author hellenxu
 * @date 2020-03-01
 * Copyright 2020 Six. All rights reserved.
 */

class InfoManager private constructor() {
    @JvmField var sharedInt = 0
    lateinit var sharedInfo: Info

    companion object {
        @JvmStatic val instance: InfoManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { InfoManager() }
    }

    fun propertyInitialized(): Boolean {
        return this::sharedInfo.isInitialized
    }

    @Parcelize
    class Info(val userId: String): Parcelable
}