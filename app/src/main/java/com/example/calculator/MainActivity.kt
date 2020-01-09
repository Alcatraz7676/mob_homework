package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    private val calculator = Calculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttons.setAllOnClickListener { id ->
            btnClick(id)
        }
    }

    private fun btnClick(btnId: Int) {
        when (btnId) {
            R.id.clearBtn -> clearEditText()
            R.id.equalsBtn -> {
                calculator.calculate()
                updateEditText()
            }
            R.id.plusBtn,
            R.id.minusBtn,
            R.id.multyBtn,
            R.id.divideBtn -> setOperation(findViewById<TextView>(btnId).text[0])
            else -> setNumber((findViewById<TextView>(btnId).text[0].toInt() - 48).toBigDecimal())
        }
    }

    private fun clearEditText() {
        input.setText("0")
        calculator.first = null
        calculator.second = null
        calculator.operation = null
    }

    private fun setNumber(num: BigDecimal) {
        if (calculator.operation == null) {
            if (calculator.first == null)
                calculator.first = num
            else
                calculator.first = calculator.first!!.multiply(10.toBigDecimal()) + num
        } else {
            if (calculator.second == null)
                calculator.second = num
            else
                calculator.second = calculator.second!!.multiply(10.toBigDecimal()) + num
        }
        updateEditText()
    }

    private fun setOperation(operationChar: Char) {
        calculator.operation = operationChar
        updateEditText()
    }

    private fun updateEditText() {
        if (calculator.operation == null) {
            input.setText("${calculator.first ?: ""}")
        } else {
            input.setText("${calculator.second ?: ""} ${calculator.operation} ${calculator.first ?: ""}")
        }
    }
}
