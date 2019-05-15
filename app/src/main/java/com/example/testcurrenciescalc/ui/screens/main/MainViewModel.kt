package com.example.testcurrenciescalc.ui.screens.main

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.testcurrenciescalc.core.Event
import com.example.testcurrenciescalc.ui.screens.BaseViewModel

abstract class MainViewModel(application: Application) : BaseViewModel(application) {

    abstract fun loadingFinishedLiveData() : LiveData<Event<Nothing>>

    abstract fun messageLiveData(): LiveData<Event<String>>

    abstract fun refresherSwiped()
}
