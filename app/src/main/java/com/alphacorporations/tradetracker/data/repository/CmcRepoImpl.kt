package com.alphacorporations.tradetracker.data.repository

import com.alphacorporations.tradetracker.data.model.HistoricalListing
import com.alphacorporations.tradetracker.data.source.local.dao.TradeDao
import com.alphacorporations.tradetracker.data.source.local.model.TradeEntity
import com.alphacorporations.tradetracker.data.source.remote.CmcApiService
import retrofit2.Response

/**
 * Created by Julien Jennequin on 10/12/2022 16:50
 * Project : TradeTracker
 **/

class CmcRepoImpl(private val networkDataSource: CmcApiService, private val tradeDao: TradeDao) {

    suspend fun getAllTrade(): List<TradeEntity> {
        val response = tradeDao.getAll()

        return response.reversed()
    }

    suspend fun getHistoricalListings(): Response<HistoricalListing> {
        return networkDataSource.getHistoricalListings()
    }


}