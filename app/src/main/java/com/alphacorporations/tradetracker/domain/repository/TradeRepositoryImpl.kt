package com.alphacorporations.tradetracker.domain.repository

import com.alphacorporations.tradetracker.database.dao.TradeDao
import com.alphacorporations.tradetracker.database.model.TradeEntity
import com.alphacorporations.tradetracker.domain.repository.TradeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TradeRepositoryImpl @Inject constructor(private val tradeDao: TradeDao) : TradeRepository {
    val trades: Flow<List<TradeEntity>> = tradeDao.getAll()

    override suspend fun insert(trade: TradeEntity) {
        tradeDao.saveTrade(trade)
    }


}