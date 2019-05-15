package com.example.testcurrenciescalc.ui.screens.main.rates

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testcurrenciescalc.data.models.Rate
import com.example.testcurrenciescalc.domain.RateRepository
import io.reactivex.schedulers.Schedulers

class RatesViewModelImpl(
    application: Application,
    rateRepository: RateRepository
) : RatesViewModel(application) {

    private val ratesLiveData = MutableLiveData<List<Rate>>()

    init {
        compositeDisposable.add(
            rateRepository.ratesObservable()
                .observeOn(Schedulers.computation())
                .subscribe { ratesLiveData.postValue(it) }
        )
    }

    override fun ratesLiveData(): LiveData<List<Rate>> = ratesLiveData
}
