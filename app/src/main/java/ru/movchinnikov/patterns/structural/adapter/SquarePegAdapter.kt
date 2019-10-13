package ru.movchinnikov.patterns.structural.adapter

import kotlin.math.pow
import kotlin.math.sqrt


class SquarePegAdapter(private val peg: SquarePeg) : RoundPeg() {

    override fun getRadius(): Double {
        // Рассчитываем минимальный радиус, в который пролезет этот колышек.
        return sqrt((peg.width / 2).pow(2.0) * 2)
    }
}