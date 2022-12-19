package com.alphacorporations.tradetracker.data.repository

import com.alphacorporations.tradetracker.data.model.HistoricalListing
import retrofit2.Response

/**
 * Created by Julien Jennequin on 10/12/2022 15:13
 * Project : TradeTracker
 **/
interface CmcRepo {
    suspend fun getHistoricalListings(): Response<HistoricalListing>

}