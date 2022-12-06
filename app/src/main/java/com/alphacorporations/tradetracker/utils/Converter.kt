package com.alphacorporations.tradetracker.utils

import com.alphacorporations.tradetracker.database.model.TradeEntity
import com.alphacorporations.tradetracker.domain.model.BiaisEnum
import com.alphacorporations.tradetracker.domain.model.Trade
import kotlinx.coroutines.flow.Flow

/**
 * Created by Julien Jennequin on 06/12/2022 14:02
 * Project : TradeTracker
 **/
object Converter {

    suspend fun fromTradeEntityToTrade(trades: Flow<List<TradeEntity>>) {
        return trades.collect { list ->
            list.map { trade ->
                Trade(
                    trade.date,
                    if (trade.biais == 'L') BiaisEnum.Bull else BiaisEnum.Bear,
                    trade.ratio,
                    trade.description,
                    trade.lotSize,
                    trade.pe,
                    trade.sl,
                    trade.tp
                )
            }
        }
    }

    fun fromTradeToTradeEntity(trade: Trade): TradeEntity {
        return TradeEntity(
            trade.date,
            if (trade.biais == BiaisEnum.Bear) 'S' else 'L',
            trade.ratio,
            trade.description,
            trade.lotSize,
            trade.pe,
            trade.sl,
            trade.tp
        )
    }
}