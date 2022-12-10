package com.alphacorporations.tradetracker.domain.repository

import com.alphacorporations.tradetracker.data.api.service.CoinMarketCapApiService
import com.alphacorporations.tradetracker.data.model.HistoricalListing
import retrofit2.Call
import javax.inject.Inject

/**
 * Created by Julien Jennequin on 10/12/2022 15:13
 * Project : TradeTracker
 **/
class CoinMarketCapRepositoryImpl @Inject constructor(private val cmcApi: CoinMarketCapApiService) :
    CoinMarketCapRepository {

    override fun getHistoricalListings(): Call<HistoricalListing> = cmcApi.getHistoricalListings()

}