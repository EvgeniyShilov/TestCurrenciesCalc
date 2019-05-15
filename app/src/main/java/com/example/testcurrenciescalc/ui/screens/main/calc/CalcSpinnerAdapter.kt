package com.example.testcurrenciescalc.ui.screens.main.calc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.testcurrenciescalc.R
import com.example.testcurrenciescalc.data.models.Rate

class CalcSpinnerAdapter : BaseAdapter() {

    private var rates: List<Rate>? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(parent.context)
            .inflate(R.layout.item_currency_chooser, parent, false)
        (view as TextView).text = getItem(position).currencyCode
        return view
    }

    override fun getItem(position: Int) = rates!![position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = rates?.size ?: 0

    fun update(rates: List<Rate>?) {
        this.rates = rates
        notifyDataSetChanged()
    }
}
