package com.alphacorporations.tradetracker.domain.repository

import com.alphacorporations.tradetracker.data.api.client.CmcClientConfig
import com.alphacorporations.tradetracker.data.api.service.CmcApiServiceImpl
import com.alphacorporations.tradetracker.data.model.HistoricalListing
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Julien Jennequin on 10/12/2022 16:50
 * Project : TradeTracker
 **/
@Singleton
class CmcRepoImpl @Inject constructor(cmcClientConfig: CmcClientConfig) : CmcRepo {

    private val cmcApi = CmcApiServiceImpl(cmcClientConfig)

    override suspend fun getHistoricalListings(): Response<HistoricalListing> =
        cmcApi.getHistoricalListings()
}