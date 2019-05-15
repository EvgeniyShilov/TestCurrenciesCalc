package com.example.testcurrenciescalc.data.net

import com.example.testcurrenciescalc.data.models.Rate
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RateAPI {

    @GET("rates/daily")
    fun getDailyRates(@Query("date") date: String): Single<List<Rate>>
}
