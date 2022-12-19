package com.alphacorporations.tradetracker.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alphacorporations.tradetracker.data.source.local.dao.TradeDao
import com.alphacorporations.tradetracker.data.source.local.model.TradeEntity

@Database(entities = [TradeEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tradeDao(): TradeDao

}