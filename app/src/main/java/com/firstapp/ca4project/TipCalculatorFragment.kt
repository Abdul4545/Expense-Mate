package com.firstapp.ca4project

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import kotlin.math.floor


private const val TAG = "TipCalculatorFragment"
private const val INITIAL_TIP_PERCENT = 12

class TipCalculatorFragment : Fragment() {

    private lateinit var etBaseAmount: EditText
    private lateinit var seekBarTip: SeekBar
    private lateinit var tvTipPercentLabel: TextView
    private lateinit var tvTipAmount: TextView
    private lateinit var tvTotalAmount: TextView
    private  lateinit var tvTipDescription: TextView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tip_calculator, container, false)

        etBaseAmount = view.findViewById(R.id.etBaseAmount)
        seekBarTip = view.findViewById(R.id.seekBarTip)
        tvTipPercentLabel = view.findViewById(R.id.tvTipPercentLabel)
        tvTipAmount = view.findViewById(R.id.tvTipAmount)
        tvTotalAmount = view.findViewById(R.id.tvTotalAmount)
        tvTipDescription = view.findViewById(R.id.tvTipDescription)

        seekBarTip.progress = INITIAL_TIP_PERCENT
        tvTipPercentLabel.text = "$INITIAL_TIP_PERCENT%"
        updateTipDescription(INITIAL_TIP_PERCENT)

        seekBarTip.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvTipPercentLabel.text = "$progress%"
                computeTipAndTotal()
                updateTipDescription(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) { }
            override fun onStopTrackingTouch(seekBar: SeekBar?) { }
        })

        etBaseAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int,after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

            override fun afterTextChanged(s: Editable?) {
                computeTipAndTotal()
            }
        })

        return view
    }

    private fun updateTipDescription(tipPercent: Int) {

        val tipDescription = when (tipPercent) {
            in 0..9 -> "Poor"
            in 10..19 -> "Acceptable"
            in 20..29 -> "Good"
            in 30..34 -> "Great"

            else -> "Amazing"
        }
        tvTipDescription.text = tipDescription

    }

    private fun computeTipAndTotal() {

        if(etBaseAmount.text.isEmpty()) {
            tvTipAmount.text = ""
            tvTotalAmount.text = ""
            return
        }

        // 1. Get the value of the base and tip percent
        val baseAmount = etBaseAmount.text.toString().toDouble()
        val tipPercent = seekBarTip.progress

        // 2. Compute the tip and total amount
        val tipAmount = (baseAmount * tipPercent) / 100;
        val totalAmount = baseAmount + tipAmount;

        // 3. Update the UI
        tvTipAmount.text =  "%.1f".format(floor(tipAmount))
        tvTotalAmount.text = "%.1f".format(floor(totalAmount))
    }
}