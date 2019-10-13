package com.example.calculator

import java.io.IOException
import kotlin.test.*

class CalculatorTest {

    lateinit var calc: Calculator

    @BeforeTest
    fun setUp() {
        calc = Calculator()
    }

    @Test
    fun calculate() {
        calc.first = 1
        calc.second = 2
        calc.operation = '+'
        assertEquals(3, calc.calculate())
    }

    @Test
    fun calculateMinus() {
        calc.first = 7
        calc.second = 2
        calc.operation = '-'
        assertEquals(5, calc.calculate())
    }

    @Test
    fun calculateMulty() {
        calc.first = 3
        calc.second = 2
        calc.operation = '*'
        assertEquals(6, calc.calculate())
    }

    @Test
    fun calculateDivine() {
        calc.first = 8
        calc.second = 2
        calc.operation = '/'
        assertEquals(4, calc.calculate())
    }

    @Test
    fun calculateDivineZero() {
        calc.first = 1
        calc.second = 0
        calc.operation = '/'
        assertFailsWith(ArithmeticException::class) {
            calc.calculate()
        }
    }

    @Test
    fun calculateUnknownOperation() {
        calc.first = 1
        calc.second = 2
        calc.operation = '$'
        assertFailsWith(IOException::class) {
            calc.calculate()
        }
    }

}