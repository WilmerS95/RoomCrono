package com.wilmer.roomcrono.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wilmer.roomcrono.model.cronos.CronoModel

@Database(entities = [CronoModel::class], version = 1, exportSchema = false)
abstract class CronosDataBase : RoomDatabase() {
    abstract fun cronoDao(): CronosDataBaseDao
}