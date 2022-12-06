package com.alphacorporations.tradetracker.utils.module

import com.alphacorporations.tradetracker.domain.repository.TradeRepositoryImpl
import com.alphacorporations.tradetracker.domain.repository.TradeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * Created by Julien Jennequin on 06/12/2022 15:38
 * Project : TradeTracker
 **/
@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providesTradeRepository(impl: TradeRepositoryImpl): TradeRepository
}