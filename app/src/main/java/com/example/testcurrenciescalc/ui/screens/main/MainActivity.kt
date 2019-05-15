package com.example.testcurrenciescalc.ui.screens.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.testcurrenciescalc.R
import com.example.testcurrenciescalc.core.Event
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val model: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vpContainer.adapter = MainPagerAdapter(supportFragmentManager)
        srlRefresher.setOnRefreshListener { model.refresherSwiped() }
        bnvNavigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener(this::menuItemSelected))
        model.loadingFinishedLiveData().observe(this, Observer(this::stopLoadingAnimation))
        model.messageLiveData().observe(this, Observer(this::showMessage))
    }

    private fun menuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.item_calc -> vpContainer.setCurrentItem(0, true)
            R.id.item_rates -> vpContainer.setCurrentItem(1, true)
            else -> return false
        }
        return true
    }

    private fun stopLoadingAnimation(event: Event<Nothing>?) {
        event?.let {
            if (event.handleIfNotHandled()) srlRefresher.isRefreshing = false
        }
    }

    private fun showMessage(event: Event<String>?) {
        event?.getContentIfNotHandled()?.let { Snackbar.make(llRoot, it, Snackbar.LENGTH_LONG).show() }
    }
}
