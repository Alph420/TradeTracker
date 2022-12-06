package com.alphacorporations.tradetracker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alphacorporations.tradetracker.database.dao.TradeDao
import com.alphacorporations.tradetracker.database.model.TradeEntity

@Database(entities = [TradeEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tradeDao(): TradeDao

}