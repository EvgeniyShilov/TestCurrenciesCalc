package com.example.testcurrenciescalc.domain

import com.example.testcurrenciescalc.data.models.Rate
import io.reactivex.Completable
import io.reactivex.Observable

interface RateRepository {

    fun ratesObservable(): Observable<List<Rate>>

    fun requestRates(): Completable
}
