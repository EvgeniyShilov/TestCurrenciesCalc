package com.example.testcurrenciescalc.ui.screens.main.calc

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.testcurrenciescalc.R
import com.example.testcurrenciescalc.data.models.Rate
import kotlinx.android.synthetic.main.fragment_calc.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalcFragment : Fragment(), AdapterView.OnItemSelectedListener, TextWatcher {

    companion object {
        fun newFragment() = CalcFragment()
    }

    private val model: CalcViewModel by viewModel()

    private lateinit var firstAdapter: CalcSpinnerAdapter
    private lateinit var secondAdapter: CalcSpinnerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firstAdapter = CalcSpinnerAdapter()
        secondAdapter = CalcSpinnerAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_calc, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sInputCurrency.adapter = firstAdapter
        sOutputCurrency.adapter = secondAdapter
        sInputCurrency.onItemSelectedListener = this
        sOutputCurrency.onItemSelectedListener = this
        etInputValue.addTextChangedListener(this)
        model.ratesLiveData().observe(viewLifecycleOwner, Observer(this::updateAdapters))
        model.outputValueLiveData().observe(viewLifecycleOwner, Observer(tvOutputValue::setText))
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        when (parent) {
            sInputCurrency -> model.firstCurrencyChanged(position)
            sOutputCurrency -> model.secondCurrencyChanged(position)
        }
    }

    override fun afterTextChanged(s: Editable) = model.inputValueChanged(s.toString())

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = Unit

    override fun onNothingSelected(parent: AdapterView<*>) = Unit

    private fun updateAdapters(rates: List<Rate>?) {
        firstAdapter.update(rates)
        secondAdapter.update(rates)
    }
}
