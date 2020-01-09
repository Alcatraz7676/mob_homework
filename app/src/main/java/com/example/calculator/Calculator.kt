package com.example.calculator

import java.io.IOException
import java.math.BigDecimal

class Calculator {
    var first: BigDecimal? = null
    var operation: Char? = null
    var second: BigDecimal? = null

    fun calculate() {
        if (first == null) {
            first = 0.toBigDecimal()
            operation = null
            second = null
            return
        }
        if (second == null) {
            operation = null
            return
        }
        first = when (operation) {
            '+' -> {
                first!!.plus(second!!)
            }
            '-' -> {
                first!!.minus(second!!)
            }
            '*' -> {
                first!!.multiply(second!!)
            }
            '/' -> {
                first!!.divide(second!!)
            }
            else -> throw IOException()
        }
        operation = null
        second = null
    }

}