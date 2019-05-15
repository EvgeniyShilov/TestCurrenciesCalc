package com.example.testcurrenciescalc.ui.screens.main.rates

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.testcurrenciescalc.data.models.Rate
import com.example.testcurrenciescalc.ui.screens.BaseViewModel

abstract class RatesViewModel(application: Application) : BaseViewModel(application) {

    abstract fun ratesLiveData(): LiveData<List<Rate>>
}
