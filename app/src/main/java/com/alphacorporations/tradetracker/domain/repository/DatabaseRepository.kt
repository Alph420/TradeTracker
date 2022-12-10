package com.alphacorporations.tradetracker.domain.repository

import com.alphacorporations.tradetracker.data.database.model.TradeEntity

/**
 * Created by Julien Jennequin on 06/12/2022 15:00
 * Project : TradeTracker
 **/
interface DatabaseRepository {
    suspend fun insert(trade: TradeEntity)
}