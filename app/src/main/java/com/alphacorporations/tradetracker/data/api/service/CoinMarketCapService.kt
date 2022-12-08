package com.alphacorporations.tradetracker.data.api.service

import com.alphacorporations.tradetracker.data.model.HistoricalListing
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Julien Jennequin on 08/12/2022 15:37
 * Project : TradeTracker
 **/
internal interface CoinMarketCapService {

    @GET("/v1/cryptocurrency/listings/historical")
    fun getHistoricalListings(): Call<HistoricalListing>
}