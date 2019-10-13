package com.example.calculator

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val calculator = Calculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttons.setOnClickListener {
            btnClick(it.id)
        }
    }

    private fun btnClick(btnId: Int) {
        when (btnId) {
            R.id.clearBtn -> clearEditText()
            R.id.equalsBtn -> calculator.calculate()
            R.id.plusBtn,
            R.id.minusBtn,
            R.id.multyBtn,
            R.id.divideBtn -> setOperation(findViewById<TextView>(btnId).text[0])
            else -> setNumber(findViewById<TextView>(btnId).text[0].toInt())
        }
    }

    private fun clearEditText() {
        input.setText("0")
        calculator.first = null
        calculator.second = null
        calculator.operation = null
    }

    private fun setNumber(num: Int) {
        if (calculator.operation == null) {
            if (calculator.first == null)
                calculator.first = num
            else
                calculator.first = calculator.first!! + num
        } else {
            if (calculator.second == null)
                calculator.second = num
            else
                calculator.second = calculator.second!! + num
        }
        updateEditText()
    }

    private fun setOperation(operationChar: Char) {
        calculator.operation = operationChar
    }

    private fun updateEditText() {
        input.setText("${calculator.first} ${calculator.operation} ${calculator.second}")
    }
}
