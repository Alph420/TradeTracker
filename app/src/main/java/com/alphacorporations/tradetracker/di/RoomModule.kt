package com.alphacorporations.tradetracker.di

import android.content.Context
import androidx.room.Room
import com.alphacorporations.tradetracker.data.source.local.AppDatabase
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
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "trade_tracker_database"
        ).build() // The reason we can construct a database for the repo


    @Provides
    @Singleton
    fun provideTradeDao(appDatabase: AppDatabase) = appDatabase.tradeDao()

}