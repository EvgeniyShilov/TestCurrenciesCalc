package com.example.testcurrenciescalc.domain

import com.example.testcurrenciescalc.data.net.RESTInterface
import com.example.testcurrenciescalc.data.storage.RateDAO
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class RateRepositoryImpl(private val rateDAO: RateDAO, private val restInterface: RESTInterface) : RateRepository {

    private val ratesObservable = rateDAO.ratesObservable()

    override fun ratesObservable() = ratesObservable

    override fun requestRates(): Completable =
        restInterface.getDailyCodes()
            .observeOn(Schedulers.io())
            .doOnSuccess { rateDAO.updateRates(it) }
            .ignoreElement()
}
