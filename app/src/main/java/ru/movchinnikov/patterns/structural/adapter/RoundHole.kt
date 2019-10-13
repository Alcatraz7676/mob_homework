package ru.movchinnikov.patterns.structural.adapter

class RoundHole(private val radius: Double) {

    fun fits(peg: RoundPeg) = radius >= peg.getRadius()
}