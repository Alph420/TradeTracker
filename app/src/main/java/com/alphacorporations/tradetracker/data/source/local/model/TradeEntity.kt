package com.alphacorporations.tradetracker.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "trade")
data class TradeEntity(
    @PrimaryKey val date: Long,
    @ColumnInfo(name = "pair") val pair: String,
    @ColumnInfo(name = "biais") val biais: Char,
    @ColumnInfo(name = "ratio") val ratio: Float,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "lot_size") val lotSize: Float,
    @ColumnInfo(name = "pe") val pe: Float,
    @ColumnInfo(name = "sl") val sl: Float,
    @ColumnInfo(name = "tp") val tp: Float,
    @ColumnInfo(name = "leverage") val leverage: Float


)