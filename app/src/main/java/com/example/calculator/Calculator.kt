package com.example.calculator

import java.io.IOException

class Calculator {
    var first: Int? = null
    var operation: Char? = null
    var second: Int? = null

    fun calculate(): Int {
        if (first == null) return 0
        if (second == null) return first!!
        return when (operation) {
            '+' -> first!! + second!!
            '-' -> first!! - second!!
            '*' -> first!! * second!!
            '/' -> first!! / second!!
            null -> first!!
            else -> throw IOException()
        }
    }

}