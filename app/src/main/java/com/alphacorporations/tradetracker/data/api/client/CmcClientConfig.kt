package com.alphacorporations.tradetracker.data.api.client

import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit


/**
 * Created by Julien Jennequin on 10/12/2022 16:44
 * Project : TradeTracker
 **/
class CmcClientConfig {
    val rootUrl: String = "https://pro-api.coinmarketcap.com/"
    val okHttpConfig: OkHttpClient.Builder.() -> OkHttpClient.Builder = {
        retryOnConnectionFailure(false)
            .addInterceptor { chain ->
                val requestBuilder: Request.Builder = chain.request().newBuilder()
                requestBuilder.header("Content-Type", "application/json")
                chain.proceed(requestBuilder.build())
            }
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
    }
}