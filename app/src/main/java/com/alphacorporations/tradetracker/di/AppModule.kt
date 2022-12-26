package com.alphacorporations.tradetracker.di

import android.content.Context
import com.alphacorporations.tradetracker.BaseActivity
import com.alphacorporations.tradetracker.data.source.remote.CmcApiService
import com.alphacorporations.tradetracker.data.source.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Julien Jennequin on 26/12/2022 13:06
 * Project : TradeTracker
 **/
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseActivity {
        return app as BaseActivity
    }


    @Singleton
    @Provides
    fun service(): CmcApiService {
        return RetrofitClient.service
    }
}