package com.example.testcurrenciescalc.ui.screens.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.testcurrenciescalc.R
import com.example.testcurrenciescalc.core.Event
import com.example.testcurrenciescalc.domain.RateRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

class MainViewModelImpl(
    application: Application,
    private val rateRepository: RateRepository
) : MainViewModel(application) {

    private val loadingFinishedLiveData = MutableLiveData<Event<Nothing>>()
    private val messageLiveData = MutableLiveData<Event<String>>()
    private val undefinedError = application.getString(R.string.error_undefined)

    init {
        requestRates()
    }

    override fun refresherSwiped() = requestRates()

    override fun loadingFinishedLiveData() = loadingFinishedLiveData

    override fun messageLiveData() = messageLiveData

    private fun requestRates() {
        compositeDisposable.add(
            rateRepository.requestRates()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Action(this::handleSuccess), Consumer(this::handleError))
        )
    }

    private fun handleSuccess() {
        loadingFinishedLiveData.value = Event()
    }

    private fun handleError(throwable: Throwable) {
        loadingFinishedLiveData.value = Event()
        messageLiveData.value = Event(throwable.localizedMessage ?: undefinedError)
    }
}
