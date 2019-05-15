package com.example.testcurrenciescalc.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testcurrenciescalc.data.models.Rate

@Database(entities = [Rate::class], version = 1)
abstract class DB : RoomDatabase() {

    companion object {
        const val NAME = "db"
    }

    abstract fun rateDAO(): RateDAO
}
