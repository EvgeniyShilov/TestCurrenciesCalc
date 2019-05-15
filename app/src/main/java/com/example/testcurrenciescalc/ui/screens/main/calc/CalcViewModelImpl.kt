package com.example.testcurrenciescalc.ui.screens.main.calc

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.testcurrenciescalc.R
import com.example.testcurrenciescalc.TestCurrenciesCalcApp
import com.example.testcurrenciescalc.data.models.Rate
import com.example.testcurrenciescalc.domain.RateRepository
import io.reactivex.schedulers.Schedulers

class CalcViewModelImpl(
    application: Application,
    rateRepository: RateRepository
) : CalcViewModel(application) {

    private val ratesLiveData = MutableLiveData<List<Rate>>()
    private val outputValueLiveData = MutableLiveData<String>()

    private var inputRate: Rate? = null
    private var outputRate: Rate? = null
    private var inputValue: Double? = null

    init {
        compositeDisposable.add(
            rateRepository.ratesObservable()
                .observeOn(Schedulers.computation())
                .subscribe { ratesLiveData.postValue(it) }
        )
    }

    override fun ratesLiveData() = ratesLiveData

    override fun outputValueLiveData() = outputValueLiveData

    override fun firstCurrencyChanged(position: Int) {
        inputRate = ratesLiveData.value!![position]
        recalculate()
    }

    override fun secondCurrencyChanged(position: Int) {
        outputRate = ratesLiveData.value!![position]
        recalculate()
    }

    override fun inputValueChanged(value: String) {
        inputValue = try {
            value.toDouble()
        } catch (e: NumberFormatException) {
            null
        }
        recalculate()
    }

    private fun recalculate() {
        val inputRate = inputRate
        val outputRate = outputRate
        val inputValue = inputValue
        outputValueLiveData.value = if (inputRate == null || outputRate == null || inputValue == null)
            getApplication<TestCurrenciesCalcApp>().getString(R.string.error_nan)
        else (inputValue * outputRate.unitValue * inputRate.medianRate /
                (outputRate.medianRate * inputRate.unitValue)).toString()
    }
}
