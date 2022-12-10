package com.alphacorporations.tradetracker.utils.module

import com.alphacorporations.tradetracker.data.api.service.CoinMarketCapApiService
import com.alphacorporations.tradetracker.domain.repository.CoinMarketCapRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Julien Jennequin on 10/12/2022 15:47
 * Project : TradeTracker
 **/
@Module
@InstallIn(SingletonComponent::class)
class CmcApiModule {
    private val BASE_URL = "https://pro-api.coinmarketcap.com/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): CoinMarketCapApiService =
        retrofit.create(CoinMarketCapApiService::class.java)

    @Singleton
    @Provides
    fun providesRepository(cmcApiService: CoinMarketCapApiService) =
        CoinMarketCapRepositoryImpl(cmcApiService)
}