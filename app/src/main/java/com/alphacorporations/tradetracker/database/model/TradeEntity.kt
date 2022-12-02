package com.alphacorporations.tradetracker.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "trade")
data class TradeEntity(
    @PrimaryKey val date: String,
    @ColumnInfo(name = "biais") val biais: Char,
    @ColumnInfo(name = "ratio") val ratio: Float,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "lot_size") val lotSize: Float,
    @ColumnInfo(name = "pe") val pe: Float,
    @ColumnInfo(name = "sl") val sl: Float,
    @ColumnInfo(name = "tp") val tp: Float
)