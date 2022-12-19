package com.alphacorporations.tradetracker.data.repository

import com.alphacorporations.tradetracker.data.source.local.dao.TradeDao
import com.alphacorporations.tradetracker.data.source.local.model.TradeEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseRepoImpl @Inject constructor(private val tradeDao: TradeDao) :
    DatabaseRepo {

    val trades: List<TradeEntity> get() = tradeDao.getAll()

    override suspend fun insert(trade: TradeEntity) {
        tradeDao.saveTrade(trade)
    }


}