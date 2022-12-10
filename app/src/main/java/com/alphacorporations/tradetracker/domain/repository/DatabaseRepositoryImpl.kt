package com.alphacorporations.tradetracker.domain.repository

import com.alphacorporations.tradetracker.data.database.dao.TradeDao
import com.alphacorporations.tradetracker.data.database.model.TradeEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseRepositoryImpl @Inject constructor(private val tradeDao: TradeDao) :
    DatabaseRepository {

    val trades: List<TradeEntity> get() = tradeDao.getAll()

    override suspend fun insert(trade: TradeEntity) {
        tradeDao.saveTrade(trade)
    }


}