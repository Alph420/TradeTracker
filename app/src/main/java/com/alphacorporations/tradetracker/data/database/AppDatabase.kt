package com.alphacorporations.tradetracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alphacorporations.tradetracker.data.database.dao.TradeDao
import com.alphacorporations.tradetracker.data.database.model.TradeEntity

@Database(entities = [TradeEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tradeDao(): TradeDao

}