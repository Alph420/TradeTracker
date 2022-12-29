package com.alphacorporations.tradetracker.data.source.remote

import com.alphacorporations.tradetracker.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
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

    var header = Interceptor { chain ->
        val original: Request = chain.request()

        val request: Request = original.newBuilder()
            .header("X-CMC_PRO_API_KEY", BuildConfig.CoinMarketCapApiKey)
            .method(original.method, original.body)
            .build()

        chain.proceed(request)
    }

    private val httpClient: OkHttpClient by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(interceptor = loggingInterceptor)
            .addInterceptor(header).build()
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