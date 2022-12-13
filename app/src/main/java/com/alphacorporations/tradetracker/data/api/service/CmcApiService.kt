package com.alphacorporations.tradetracker.data.api.service

import com.alphacorporations.tradetracker.data.model.HistoricalListing
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Julien Jennequin on 10/12/2022 16:47
 * Project : TradeTracker
 **/
internal interface CmcApiService {
    @GET("/v1/cryptocurrency/listings/historical")
    fun getHistoricalListings(): Response<HistoricalListing>
}