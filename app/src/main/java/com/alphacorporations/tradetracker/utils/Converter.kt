package com.alphacorporations.tradetracker.utils

import com.alphacorporations.tradetracker.data.source.local.model.TradeEntity
import com.alphacorporations.tradetracker.domain.model.BiaisEnum
import com.alphacorporations.tradetracker.domain.model.Trade

/**
 * Created by Julien Jennequin on 06/12/2022 14:02
 * Project : TradeTracker
 **/
object Converter {

    fun fromTradeEntityToTrade(trades: List<TradeEntity>): List<Trade> {
        return trades.map { trade ->
            Trade(
                trade.date,
                trade.pair,
                if (trade.biais == 'L') BiaisEnum.Bull else BiaisEnum.Bear,
                trade.ratio,
                trade.description,
                trade.lotSize,
                trade.pe,
                trade.sl,
                trade.tp,
                trade.leverage
            )
        }
    }

    fun fromTradeToTradeEntity(trade: Trade): TradeEntity {
        return TradeEntity(
            trade.date,
            trade.pair,
            if (trade.biais == BiaisEnum.Bear) 'S' else 'L',
            trade.ratio,
            trade.description,
            trade.lotSize,
            trade.pe,
            trade.sl,
            trade.tp,
            trade.leverage
        )
    }
}