package com.alphacorporations.tradetracker.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.alphacorporations.tradetracker.database.model.TradeEntity

@Dao
interface TradeDao {
    @Query("SELECT * FROM trade")
    fun getAll(): List<TradeEntity>

    @Insert
    fun saveTrade(): TradeEntity

    @Delete
    fun delete(trade: TradeEntity)

}