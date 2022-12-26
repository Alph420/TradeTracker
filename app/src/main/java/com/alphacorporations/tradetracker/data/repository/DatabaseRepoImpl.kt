package com.alphacorporations.tradetracker.data.repository

import com.alphacorporations.tradetracker.data.source.local.dao.TradeDao
import com.alphacorporations.tradetracker.data.source.local.model.TradeEntity
import com.alphacorporations.tradetracker.domain.repository.DatabaseRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseRepoImpl @Inject constructor(private val tradeDao: TradeDao) :
    DatabaseRepo {

    override suspend fun getAllTrade(): List<TradeEntity> {
        var response = tradeDao.getAll()

        return response.reversed()
    }

    override suspend fun insert(trade: TradeEntity) {
        tradeDao.saveTrade(trade)
    }


}