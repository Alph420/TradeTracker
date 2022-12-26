package com.alphacorporations.tradetracker.domain.repository

import com.alphacorporations.tradetracker.data.source.local.model.TradeEntity

/**
 * Created by Julien Jennequin on 06/12/2022 15:00
 * Project : TradeTracker
 **/
interface DatabaseRepo {
    suspend fun getAllTrade(): List<TradeEntity>
    suspend fun insert(trade: TradeEntity)
}