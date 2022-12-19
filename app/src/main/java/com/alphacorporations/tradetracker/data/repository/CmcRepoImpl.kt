package com.alphacorporations.tradetracker.data.repository

import com.alphacorporations.tradetracker.data.model.HistoricalListing
import com.alphacorporations.tradetracker.data.source.local.AppDatabase
import com.alphacorporations.tradetracker.data.source.remote.CmcApiService
import retrofit2.Response
import javax.inject.Singleton

/**
 * Created by Julien Jennequin on 10/12/2022 16:50
 * Project : TradeTracker
 **/
@Singleton
class CmcRepoImpl(
    private val database: AppDatabase,
    private val cmcApi: CmcApiService
) : CmcRepo {

    override suspend fun getHistoricalListings(): Response<HistoricalListing> =
        cmcApi.getHistoricalListings()
}