package ru.movchinnikov.patterns.structural.adapter

import kotlin.math.pow

class SquarePeg(val width: Double) {

    fun getSquare() = width.pow(2)
}