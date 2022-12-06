package com.alphacorporations.tradetracker.utils.module

import android.content.Context
import androidx.room.Room
import com.alphacorporations.tradetracker.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Julien Jennequin on 06/12/2022 13:04
 * Project : TradeTracker
 **/
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "trade_tracker_database"
        ).build() // The reason we can construct a database for the repo


    @Provides
    @Singleton
    fun provideTradeDao(appDatabase: AppDatabase) = appDatabase.tradeDao()
}