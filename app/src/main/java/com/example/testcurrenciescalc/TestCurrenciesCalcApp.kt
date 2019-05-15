package com.example.testcurrenciescalc

import android.app.Application
import androidx.room.Room
import com.example.testcurrenciescalc.core.createRetrofit
import com.example.testcurrenciescalc.data.net.RESTInterface
import com.example.testcurrenciescalc.data.net.RESTInterfaceImpl
import com.example.testcurrenciescalc.data.storage.DB
import com.example.testcurrenciescalc.domain.RateRepository
import com.example.testcurrenciescalc.domain.RateRepositoryImpl
import com.example.testcurrenciescalc.ui.screens.main.MainViewModel
import com.example.testcurrenciescalc.ui.screens.main.MainViewModelImpl
import com.example.testcurrenciescalc.ui.screens.main.calc.CalcViewModel
import com.example.testcurrenciescalc.ui.screens.main.calc.CalcViewModelImpl
import com.example.testcurrenciescalc.ui.screens.main.rates.RatesViewModel
import com.example.testcurrenciescalc.ui.screens.main.rates.RatesViewModelImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class TestCurrenciesCalcApp : Application() {

    private val coreModule = module {
        single { createRetrofit() }
    }
    private val dataModule = module {
        single<RESTInterface> { RESTInterfaceImpl(get()) }
        single { Room.databaseBuilder(get(), DB::class.java, DB.NAME).build() }
        single { get<DB>().rateDAO() }
    }
    private val domainModule = module {
        single<RateRepository> { RateRepositoryImpl(get(), get()) }
    }
    private val uiModule = module {
        viewModel<MainViewModel> { MainViewModelImpl(get(), get()) }
        viewModel<RatesViewModel> { RatesViewModelImpl(get(), get()) }
        viewModel<CalcViewModel> { CalcViewModelImpl(get(), get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TestCurrenciesCalcApp)
            modules(coreModule, dataModule, domainModule, uiModule)
        }
    }
}
