package com.alphacorporations.tradetracker.data.source.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Julien Jennequin on 26/12/2022 12:56
 * Project : TradeTracker
 **/
object RetrofitClient {

    private val gson: Gson by lazy {
        GsonBuilder().setLenient().create()
    }


    val httpClient: OkHttpClient by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(interceptor = loggingInterceptor).build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://pro-api.coinmarketcap.com/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val service: CmcApiService by lazy { retrofit.create(CmcApiService::class.java) }

}