package com.alphacorporations.tradetracker.domain.repository

import com.alphacorporations.tradetracker.data.model.HistoricalListing
import retrofit2.Call

/**
 * Created by Julien Jennequin on 10/12/2022 15:13
 * Project : TradeTracker
 **/
interface CoinMarketCapRepository {
    fun getHistoricalListings(): Call<HistoricalListing>

}