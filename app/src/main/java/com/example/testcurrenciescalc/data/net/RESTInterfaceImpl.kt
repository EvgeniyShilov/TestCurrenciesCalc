package com.example.testcurrenciescalc.data.net

import com.example.testcurrenciescalc.data.models.Rate
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*

class RESTInterfaceImpl(retrofit: Retrofit) : RESTInterface {

    companion object {
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    }

    private val api = retrofit.create(RateAPI::class.java)

    override fun getDailyCodes(): Single<List<Rate>> =
        api.getDailyRates(dateFormat.format(Date()))
            .subscribeOn(Schedulers.newThread())
}
