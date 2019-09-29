package six.ca.custom.json

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * @author hellenxu
 * @date 2019-09-29
 * Copyright 2019 Six. All rights reserved.
 */
object NetClient {

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(getMoshiConverter())
            .build()
    }

    private fun getMoshiConverter(): MoshiConverterFactory {
        return MoshiConverterFactory.create(
            Moshi.Builder().add(Wrap.ADAPTER_FACTORY)
                .add(KotlinJsonAdapterFactory()).build()
        )
    }

    const val BASE_URL = "http://mocky.io"
}