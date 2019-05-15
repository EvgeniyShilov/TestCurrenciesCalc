package com.example.testcurrenciescalc.ui.screens.main.calc

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.testcurrenciescalc.data.models.Rate
import com.example.testcurrenciescalc.ui.screens.BaseViewModel

abstract class CalcViewModel(application: Application) : BaseViewModel(application) {

    abstract fun ratesLiveData(): LiveData<List<Rate>>

    abstract fun outputValueLiveData(): LiveData<String>

    abstract fun firstCurrencyChanged(position: Int)

    abstract fun secondCurrencyChanged(position: Int)

    abstract fun inputValueChanged(value: String)
}
