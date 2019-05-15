package com.example.testcurrenciescalc.ui.screens.main.rates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testcurrenciescalc.R
import com.example.testcurrenciescalc.data.models.Rate
import kotlinx.android.synthetic.main.item_rates_list.view.*

class RatesListAdapter : RecyclerView.Adapter<RatesListAdapter.RatesItemViewHolder>() {

    private var rates: List<Rate>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RatesItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rates_list, parent, false))

    override fun getItemCount() = rates?.size ?: 0

    override fun onBindViewHolder(holder: RatesItemViewHolder, position: Int) = holder.bind(rates!![position])

    fun update(rates: List<Rate>?) {
        this.rates = rates
        notifyDataSetChanged()
    }

    class RatesItemViewHolder(root: View) : RecyclerView.ViewHolder(root) {

        fun bind(rate: Rate) {
            itemView.tvCode.text = rate.currencyCode
            itemView.tvBuyingRate.text = itemView.context.getString(R.string.pattern_rate, rate.buyingRate, rate.unitValue)
            itemView.tvMedianRate.text = itemView.context.getString(R.string.pattern_rate, rate.medianRate, rate.unitValue)
            itemView.tvSellingRate.text = itemView.context.getString(R.string.pattern_rate, rate.sellingRate, rate.unitValue)
        }
    }
}
