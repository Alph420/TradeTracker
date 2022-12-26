package com.alphacorporations.tradetracker.di

import com.alphacorporations.tradetracker.data.repository.CmcRepoImpl
import com.alphacorporations.tradetracker.data.source.local.dao.TradeDao
import com.alphacorporations.tradetracker.data.source.remote.CmcApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by Julien Jennequin on 26/12/2022 11:19
 * Project : TradeTracker
 **/
@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideCmcRepo(cmcApiService: CmcApiService, tradeDao: TradeDao): CmcRepoImpl {
        return CmcRepoImpl(cmcApiService, tradeDao)
    }
}