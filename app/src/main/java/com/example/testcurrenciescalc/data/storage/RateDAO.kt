package com.example.testcurrenciescalc.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.testcurrenciescalc.data.models.Rate
import io.reactivex.Observable

@Dao
abstract class RateDAO {

    @Query("SELECT * FROM rate")
    abstract fun ratesObservable(): Observable<List<Rate>>

    @Transaction
    open fun updateRates(rates: List<Rate>) {
        deleteRates()
        insertRates(rates)
    }

    @Insert
    protected abstract fun insertRates(rates: List<Rate>)

    @Query("DELETE FROM rate")
    protected abstract fun deleteRates()
}
