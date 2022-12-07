package com.alphacorporations.tradetracker.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.alphacorporations.tradetracker.database.model.TradeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TradeDao {
    @Query("SELECT * FROM trade")
    fun getAll(): List<TradeEntity>

    @Insert
    fun saveTrade(trade: TradeEntity): Long

    @Delete
    fun delete(trade: TradeEntity)

}