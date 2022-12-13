package com.alphacorporations.tradetracker.data.api.service

import com.alphacorporations.tradetracker.data.api.client.CmcClientConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Created by Julien Jennequin on 10/12/2022 16:49
 * Project : TradeTracker
 **/
internal class CmcApiServiceImpl(private val cmcClientConfig: CmcClientConfig) :
    CmcApiService by Retrofit.Builder()
        .baseUrl(cmcClientConfig.rootUrl)
        .client(OkHttpClient.Builder().(cmcClientConfig.okHttpConfig)().build())
        .build()
        .create(CmcApiService::class.java)