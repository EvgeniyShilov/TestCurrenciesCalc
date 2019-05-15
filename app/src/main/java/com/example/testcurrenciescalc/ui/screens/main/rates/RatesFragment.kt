package com.example.testcurrenciescalc.ui.screens.main.rates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testcurrenciescalc.R
import kotlinx.android.synthetic.main.fragment_rates.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RatesFragment : Fragment() {

    companion object {
        fun newFragment() = RatesFragment()
    }

    private val model: RatesViewModel by viewModel()

    private lateinit var adapter: RatesListAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = RatesListAdapter()
        layoutManager = LinearLayoutManager(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_rates, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvList.layoutManager = layoutManager
        rvList.adapter = adapter
        model.ratesLiveData().observe(viewLifecycleOwner, Observer(adapter::update))
    }
}
