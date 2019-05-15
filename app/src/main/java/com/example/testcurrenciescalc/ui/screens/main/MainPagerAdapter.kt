package com.example.testcurrenciescalc.ui.screens.main

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.testcurrenciescalc.ui.screens.main.calc.CalcFragment
import com.example.testcurrenciescalc.ui.screens.main.rates.RatesFragment

class MainPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    override fun getItem(position: Int) =
        when (position) {
            0 -> CalcFragment.newFragment()
            1 -> RatesFragment.newFragment()
            else -> throw RuntimeException()
        }

    override fun getCount() = 2
}
