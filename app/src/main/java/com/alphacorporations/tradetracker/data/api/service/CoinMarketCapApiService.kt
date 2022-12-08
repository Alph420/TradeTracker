package com.alphacorporations.tradetracker.data.api.service

import com.alphacorporations.tradetracker.data.api.client.CoinMarketCapApiClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Created by Julien Jennequin on 08/12/2022 15:36
 * Project : TradeTracker
 **/
class CoinMarketCapApiService(private val config: CoinMarketCapApiClient) :
    CoinMarketCapService by Retrofit.Builder()
        .baseUrl(config.rootUrl)
        .client(OkHttpClient.Builder().(config.okHttpConfig)().build())
        .build()
        .create(CoinMarketCapService::class.java)
