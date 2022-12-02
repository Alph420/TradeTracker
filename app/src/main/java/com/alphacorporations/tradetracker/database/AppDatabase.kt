package com.alphacorporations.tradetracker.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alphacorporations.tradetracker.BaseActivity
import com.alphacorporations.tradetracker.database.dao.TradeDao
import com.alphacorporations.tradetracker.database.model.TradeEntity

@Database(entities = [TradeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tradeDao(): TradeDao

    val db = Room.databaseBuilder(
        BaseActivity().applicationContext,
        AppDatabase::class.java, "database-name"
    ).build()
}