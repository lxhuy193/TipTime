package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
//
    lateinit var binding: ActivityMainBinding
//
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    //
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    //
        binding.btnCalculate.setOnClickListener {
            calculateTip()
        }
    }

    fun calculateTip(){
        val cost = binding.etCost.text.toString().toDouble()
        val selectedId = binding.rbtngTipOption.checkedRadioButtonId
        val tipPercent = when (selectedId){
            R.id.rbtn_10 -> 0.1
            R.id.rbtn_15 -> 0.15
            else -> 0.2
        }
        var tip = tipPercent * cost
        val switchBtn = binding.swbtnRoundup.isChecked
        if (switchBtn){
            tip = ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tvTotal.text = getString(R.string.tip_amount, formattedTip)

        val total = tip + cost
        val formattedBill = NumberFormat.getCurrencyInstance().format(total)
        binding.tvTotalbill.text = getString(R.string.bill_amount, formattedBill)

    }
}